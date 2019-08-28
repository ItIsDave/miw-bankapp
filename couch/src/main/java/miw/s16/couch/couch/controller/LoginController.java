package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.entity.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping
    public String indexHandler(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping(value="loginHandler")
    public String loginHandler(@ModelAttribute User user, Model model){
        System.out.println("Ingelogd lid: " + user.getUserName() + ", " + user.getUserPassword());

        // test user for login functionality
        User testUser = new User("Test", "1234", 140);
        BankUser bankuser = new BankUser("userBankuser", "pwBankUser", 23, "hoofdMKB");
        SMEUser smeuser = new SMEUser("userSmeuser", "pwSmeUser", 23, 23, "Aliebalie", "baliemedewerker");
        RetailUser retailuser = new RetailUser("userRetailUser", "pwRetailUser",23,
                23, "Alie", "", "Balie",
                "retailstraat", 23, "",
                "23", "Retail", 23,
                23, "oeps", "eigenaar");
        BankAccount bankaccount = new BankAccount("NL"//,"retail"
                 , 5);

        if (testUser != null && testUser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", testUser.getUserName());
            model.addAttribute("bankaccount", bankaccount.getIBAN());
            return "personal_page";
        }
        if (bankuser != null && bankuser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", bankuser.getUserName());
            return "personal_page";
        }
        if (smeuser != null && smeuser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", smeuser.getUserName());
            return "personal_page";
        }
        if (retailuser != null && retailuser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", retailuser.getUserName());
            return "personal_page";
        }
        return"login failed";
    }

}
