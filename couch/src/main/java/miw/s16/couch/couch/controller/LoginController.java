package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.entity.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping(value="couch", method= RequestMethod.GET)
    public String appHandler(Model model){
        model.addAttribute("welkom", "Welkom bij Couch Bank");
        return "welcome_template";
    }

    @GetMapping(value="login")
    public String indexHandler(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value="loginHandler")
    public String loginHandler(@ModelAttribute User user, Model model){
        System.out.println("Ingelogd lid: " + user.getUserName() + ", " + user.getUserPassword());

        // test user for login functionality
        User testUser = new User("Test", "1234", 140);
        BankAccount bankaccount = new BankAccount("NL", "retail", 5);

        if (testUser != null && testUser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", testUser.getUserName());
            model.addAttribute("bankaccount", bankaccount.getIBAN());
            return "personal_page";
        }
        return"login failed";
    }

}



