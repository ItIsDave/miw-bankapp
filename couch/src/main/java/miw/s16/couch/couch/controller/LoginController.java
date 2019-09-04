package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
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
       RetailUser retailUser = new RetailUser();
       model.addAttribute("user", user);
       model.addAttribute("retailUser", retailUser);
       return "home";
    }


    // depending on user type, user goes to specific overview page
    @PostMapping(value="loginHandler")
    public String loginHandler(@ModelAttribute RetailUser retailUser, Model model){
//        model.addAttribute("userName", user.getUserName());
//        model.addAttribute("bankAccount", retailUser.getBankAccount());
//      boolean loginOk = validator.validateMemberPassword(user);
//        if (loginOk) {
            model.addAttribute("userName", retailUser.getUserName());
           // model.addAttribute("bankAccount", retailUser.getBankAccount());
           return "personal_page";
//       }
//       return"login_failed";
    }

}
