package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.dao.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class NewCompanyAndSmeController implements WebMvcConfigurer {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    BankUserDao bankUserDao;

    private List<String> companyForm = new ArrayList<>();
    private List<String> sectors = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    @GetMapping(value = "couch-zakelijk")
    public String newCompanyHandler(Model model) {
        Company company = new Company();
        if (companyForm.size() == 0 && sectors.size() == 0) {
            Collections.addAll(companyForm, "B.V.", "Eenmanszaak", "Vereniging of Stichting", "V.O.F", "Andere ondernemingsvorm");
            // branch-informatie van KvK
            Collections.addAll(sectors, "ICT", "Advies en Consultancy", "Bouw, installatie en infra", "Energie",
                    "Financiële dienstverlening", "Water en afval", "Industrie", "Groothandel", "Agrosector", "Horeca", "Onderwijs en training",
                    "Persoonlijke dienstverlening en not-for-profit", "Gezondheidszorg", "Vervoer, post en opslag");
        }
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("sectors", sectors);
        model.addAttribute("company", company);
        return "new_company";
    }

    // create a new company
    @PostMapping(value = "couch-zakelijk")
    public String newSMEUserHandler(@Valid @ModelAttribute("company") @RequestBody Company company,
                                    BindingResult bindingResult, SMEUser smeUser, Model model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (roles.size() == 0) {
            Collections.addAll(roles, "Eigenaar", "Medewerker", "Admin");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("companyForm", companyForm);
            model.addAttribute("sectors", sectors);
            model.addAttribute("company", company);
            return "new_company";
        } else {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountType("Zakelijk");
            bankAccountDao.save(bankAccount);
            company.addCompanyAccount(bankAccount);
            company.setPinCode(1234);
            companyDao.save(company);
            // saving company in session
            session.setAttribute("companyName", company.getCompanyName());
            model.addAttribute("company", company);
            model.addAttribute("roles", roles);
           // model.addAttribute("role", "");
            model.addAttribute("smeUser", smeUser);
            //model.addAttribute("companySector", "");
            return "new_SMEUser";
        }
    }

    // create a new SME User
    @PostMapping(value = "newSMEUser")
    public String newSMEUserHandler(@Valid @ModelAttribute("smeUser") @RequestBody SMEUser smeUser, BindingResult bindingResult, Model model, Company company, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roles);
            return "new_SMEUser";
//
        } else {
//            // Check if user already exists as a client
//            List<RetailUser> existingUsers = new ArrayList<>();
//            existingUsers = retailUserDao.findByBsn(smeUser.getBsn());
//            if (existingUsers != null) {
//                RetailUser existingUser = existingUsers.get(0); // bsn should be unique -- to check
//                List<BankAccount> accounts = retailUserDao.findBankAccountsByUserName(existingUser.getUserName());
//
//                // && check if user already has another business account
//                for (BankAccount account : accounts)
//                    if (account.getAccountType().equals("Zakelijk")) {
//                        return "account_overflow";
//                    }
//                // if its the first zakelijk account, add it to the list of their existing accounts
//                // work in progress ---
//                existingUser.addBankAccount(bankAccount);
//
            HttpSession session = request.getSession(true);
            String companyName = (String) session.getAttribute("companyName");
            company = companyDao.findByCompanyName(companyName).get(0);
            smeUser.setCompany(company);
            smeUserDao.save(smeUser);
            // test if works for killing session
//            session.invalidate();
            return "new_SMEUser_success";
//
        }
    }
}


