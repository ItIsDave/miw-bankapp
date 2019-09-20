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


    @GetMapping(value = "couch-zakelijk")  //van select type -> new zakelijk user
    public String newSMEUserHandler(Model model, SMEUser smeUser, Company company) {
        model.addAttribute("SMEUser", smeUser);
        Collections.addAll(companyForm, "B.V.", "Eenmanszaak", "Vereniging of Stichting", "V.O.F", "Andere ondernemingsvorm");
        model.addAttribute("companyForm", companyForm);
        model.addAttribute("company", company);
        return "new_SMEUser";
    }

    @PostMapping(value = "couch-zakelijk")
    public String newSMEUserHandler(SMEUser smeUser, Company company, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "new_SMEUser";
//        } else {
        BankAccount bankAccount = new BankAccount();
        bankAccountDao.save(bankAccount);
        company.addCompanyAccount(bankAccount);
        company.addCompanyEmployee(smeUser);

        // getting the info from the form
        smeUser.setCompany(company);
        // get the number of employees and loop
//
//        companyDao.save(company);
//        smeUserDao.save(smeUser);

        return "new_SMEUser_success";
    }
}

