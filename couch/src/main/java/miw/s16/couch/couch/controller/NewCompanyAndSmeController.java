package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.BankUserDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class NewCompanyAndSmeController implements WebMvcConfigurer {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    BankUserDao bankUserDao;

    private List<String> companyForm = new ArrayList<>();
    private List<String> sectors = new ArrayList<>();
    private List<String> roles = new ArrayList<>();
    private SMEUser newSMEuser = new SMEUser();
    private BankAccount bankAccount = new BankAccount();
    private Company newCompany = new Company();
    // for testing
    private BankUser bankUser = new BankUser();



    @GetMapping(value = "couch-zakelijk")  //van select type -> new zakelijk user
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
        model.addAttribute("company",company);
        return "new_company";
    }

    @PostMapping(value = "couch-zakelijk")
    public String newSMEUserHandler(@Valid @ModelAttribute("company") @RequestBody Company company, BindingResult bindingResult, Model model) {
        if (roles.size() == 0 ){
            Collections.addAll(roles, "Eigenaar", "Medewerker", "Admin"); }
        if (bindingResult.hasErrors()) {
            model.addAttribute("companyForm", companyForm);
            model.addAttribute("sectors", sectors);
            model.addAttribute("company",company);
            return "new_company";
        } else {
            bankAccountDao.save(bankAccount);
            // To do -- enter validation of unique kvk nummer
            // for testing
            bankUser.setRole("account manager");
            company.setAccountManager(bankUser);
            company.addCompanyAccount(bankAccount);
            company.setPinCode(1234);
            company.addCompanyEmployee(newSMEuser);
            companyDao.save(company);
            newCompany = company;
            model.addAttribute("roles", roles);
            model.addAttribute("role", "");
            model.addAttribute("smeUser", newSMEuser);
            model.addAttribute("companySector", "");
            return "new_SMEUser";
        }
    }

    @PostMapping(value = "newSMEUser")
    public String newSMEUserHandler(@ModelAttribute("smeUser") @RequestBody SMEUser smeUser) {
//        if (bindingResult.hasErrors()) {
//            return "new_SMEUser";
//        } else {
            // To do -- check if user already exists as a client
            RetailUser existingRetailUser = new RetailUser();
            smeUser.setCompany(newCompany);
            smeUserDao.save(smeUser);
            return "new_SMEUser_success";
        //}
    }
}


