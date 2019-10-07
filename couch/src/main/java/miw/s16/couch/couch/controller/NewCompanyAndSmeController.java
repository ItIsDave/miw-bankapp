package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.dao.*;
import org.apache.tomcat.util.digester.ArrayStack;
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
    private List<BankUser> accountManagers = new ArrayList<>();


    @GetMapping(value = "couch-zakelijk")
    public String newCompanyHandler(Model model, Company company) {
        if (companyForm.size() == 0 && sectors.size() == 0) {
            Collections.addAll(companyForm, "B.V.", "Eenmanszaak", "Vereniging of Stichting", "V.O.F", "Andere ondernemingsvorm");
            // branch-informatie van KvK
            Collections.addAll(sectors, "ICT", "Advies en Consultancy", "Bouw, installatie en infra", "Energie",
                    "Financiële dienstverlening", "Water en afval", "Industrie", "Groothandel", "Agrosector", "Horeca", "Onderwijs en training",
                    "Persoonlijke dienstverlening en not-for-profit", "Gezondheidszorg", "Vervoer, post en opslag");
        }
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("sectors", sectors);
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
            // assign random account manager
            accountManagers = bankUserDao.findAllByRole("Account Manager");
            int random = (int )(Math.random() * accountManagers.size() + 1);
            company.setAccountManager(accountManagers.get(random));
            company.addCompanyAccount(bankAccount);
            company.setPinCode(1234);
            companyDao.save(company);
            // saving company in session
            session.setAttribute("companyName", company.getCompanyName());
            model.addAttribute("company", company);
            model.addAttribute("roles", roles);
            model.addAttribute("smeUser", smeUser);
            return "new_SMEUser";
        }
    }

    // create a new SME User
    @PostMapping(value = "newSMEUser")
    public String newSMEUserHandler(@Valid @ModelAttribute("smeUser") @RequestBody SMEUser smeUser, BindingResult bindingResult, Model model, Company company, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roles);
            return "new_SMEUser";
        } else {
            HttpSession session = request.getSession(true);
            String companyName = (String) session.getAttribute("companyName");
            company = companyDao.findByCompanyName(companyName).get(0);
            int bsn = smeUser.getBsn();
            //check is user already has a company
            if (smeUserDao.findByBsn(bsn) != null) {
                return "account_overflow"; }
            smeUser.setCompany(company);
            smeUserDao.save(smeUser);
            return "new_SMEUser_success";
        }
    }
}


