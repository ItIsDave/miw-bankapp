package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Company;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class NewCompanyAndSmeController {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    CompanyDao companyDao;

    private List<String> companyForm = new ArrayList<>();
    private List<String> companySector = new ArrayList<>();


    @GetMapping(value = "couch-zakelijk")  //van select type -> new zakelijk user
    public String newCompanyHandler(Model model) {
        SMEUser smeUser = new SMEUser();
        Company company = new Company();
        Collections.addAll(companyForm, "B.V.", "Eenmanszaak", "Vereniging of Stichting", "V.O.F", "Andere ondernemingsvorm");
        // branch-informatie van KvK
        Collections.addAll(companySector, "ICT", "Advies en Consultancy", "Bouw, installatie en infra", "Energie",
                "Financiële dienstverlening", "Water en afval", "Industrie", "Groothandel", "Agrosector", "Horeca", "Onderwijs en training",
        "Persoonlijke dienstverlening en not-for-profit", "Gezondheidszorg", "Vervoer, post en opslag");
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("companySector", companySector);
        model.addAttribute("smeUser", smeUser);
        model.addAttribute("company",company);
        return "new_company";
    }

    @PostMapping(value = "newCompany")
    public String newCompanyHandler(SMEUser smeUser, @Valid Company company, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new_company";
        } else {
            BankAccount bankAccount = new BankAccount();
            Company newCompany = new Company();
            bankAccountDao.save(bankAccount);
            newCompany.addCompanyAccount(bankAccount);
            newCompany.setCompanyName(company.getCompanyName());
            newCompany.setChamberOfCommerceId(company.getChamberOfCommerceId());
            newCompany.setSector("companySector");
            // for testing
            newCompany.setCompanyLegalName(company.getCompanyName() + "B.V");
            newCompany.setPinCode(1234);
            companyDao.save(newCompany);
            model.addAttribute("company", newCompany);
            model.addAttribute("smeUser", smeUser);
        }
        return "new_SMEUser";
    }
}

