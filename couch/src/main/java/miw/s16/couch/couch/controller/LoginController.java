package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.UserDao;
import miw.s16.couch.couch.model.entity.BankAccount;
import miw.s16.couch.couch.service.HibernateLab;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    HibernateLab lab;


    @GetMapping
    public String indexHandler(Model model){
       lab.dbinit();
       User user = new User();
        model.addAttribute("user", user);
        return "home";
    }


    @PostMapping(value="loginHandler")
    public String loginHandler(@ModelAttribute User user, Model model){
        model.addAttribute("userName", user.getUserName());
      boolean loginOk = validator.validateMemberPassword(user);
        if (loginOk) {
            model.addAttribute("userName", user.getUserName());
           return "personal_page";
       }
       return"login failed";
    }

}
