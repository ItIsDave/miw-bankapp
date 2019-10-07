package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class NewRetailUserController implements WebMvcConfigurer {


    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    //van select type -> new retail user
   @GetMapping(value = "newRetailUser")
    public String newRetailUserHandler(Model model, RetailUser retailUser){
       model.addAttribute("retailUser", retailUser);
        return "new_retail_user";
    }

    //afhandeling invoer form nieuwe retailuser
    @PostMapping(value ="newRetailUser")
    public String retailUserHandler(@Valid RetailUser retailUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "new_retail_user";
        } else {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setAccountType("Particulier");
            retailUser.addBankAccount(bankAccount);
            retailUser.setRole("Retail");
            bankAccountDao.save(bankAccount);
            retailUserDao.save(retailUser);
        return "new_retail_user_success";
    }
   }

}

