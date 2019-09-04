package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewUserController {

    @GetMapping(value = "newRetailUser")  //van select type -> new retail user
    public String newRetailUserHandler(Model model){
        model.addAttribute("RetailUser", new RetailUser());
        return "new_retail_user";
    }

    @PostMapping(value ="retailUserHandler")
    public String retailUserHandler(@ModelAttribute("RetailUser") RetailUser RetailUser){
        //WIP
        return "new_retail_user_success";
    }
}

