package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class NewUserController {

//
//    @GetMapping(value = "newRetailUser")  //van select type -> new retail user
//    public String newRetailUserHandler(Model model){
//        model.addAttribute("RetailUser", new RetailUser());
//        return "new_retail_user";
//    }


@Autowired
    RetailUserDao retailUserDao;

@Autowired
    BankAccountDao bankAccountDao;

   @GetMapping(value = "newRetailUser")  //van select type -> new retail user
    public String newRetailUserHandler(Model model, User user){
        model.addAttribute("RetailUser", new RetailUser());
        model.addAttribute("User", new User());
        return "new_retail_user";
    }

    @PostMapping(value ="retailUserHandler")
    public String retailUserHandler(@Valid @ModelAttribute("RetailUser") RetailUser retailUser, BindingResult bindingResult){
       //WIP
        if(bindingResult.hasErrors()){
            return "new_retail_user";
        } else {
        BankAccount bankAccount = new BankAccount();        //bankaccount wordt gegenereerd
        retailUser.addBankAccount(bankAccount);             //ba gekoppeld aan user
        bankAccountDao.save(bankAccount);                   //ba opgeslagen in DB
        retailUserDao.save(retailUser);                     //user opgeslagen in DB
        System.out.println("User ID: " + retailUser.getUserId());
        return "new_retail_user_success";
    }
   }
}

