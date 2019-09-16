package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class NewSMEUserController {

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    private List<String> companyForm = new ArrayList<>();


    @GetMapping(value = "couch-zakelijk")  //van select type -> new zakelijk user
    public String newSMEUserHandler(Model model, SMEUser smeUser){
        model.addAttribute("SMEUser", smeUser);
        Collections.addAll(companyForm, "B.V.","Eenmanszaak","Vereniging of Stichting","V.O.F","Andere ondernemingsvorm");
        model.addAttribute("companyForm", companyForm);
        return "new_SMEUser";
    }

    @PostMapping(value ="couch-zakelijk")
    public String newSMEUserHandler(@Valid SMEUser company, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "new_SMEUser";
        } else {
            BankAccount bankAccount = new BankAccount();
            company.addBankAccount(bankAccount);
            bankAccountDao.save(bankAccount);
            smeUserDao.save(company);
            return "new_SMEUser_success";
        }
    }

}
