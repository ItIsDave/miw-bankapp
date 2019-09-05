package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.HibernateLab;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//coding by PH & AV

@Controller
public class LoginController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    HibernateLab lab;

    @Autowired
    RetailUserDao retailUserDao;


    @GetMapping
    public String indexHandler(Model model){
       lab.dbinit();
       User user = new User();
        model.addAttribute("user", user);
        return "home";
    }


    @PostMapping(value="loginHandler")
    public String loginHandler(@ModelAttribute User user, Model model){
      boolean loginOk = validator.validateMemberPassword(user);
        List<RetailUser> loggedInRetailUser = retailUserDao.findByUserName(user.getUserName());
        if (loginOk) {
            model.addAttribute("userName", loggedInRetailUser.get(0).getUserName());
            model.addAttribute("bankaccount", loggedInRetailUser.get(0).getRetailRekeningen().get(0));
           return "personal_page";
       }
       return"login_failed";
    }

    @GetMapping(value = "newUser")
    public String newUserHandler(){
        return "new_user_select_type";
    }
}
