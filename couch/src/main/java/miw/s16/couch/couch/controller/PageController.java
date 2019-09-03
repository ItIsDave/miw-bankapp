package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.entity.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    //user for testing
    private User testUser = new User("Test", "1234", 140);
    private BankAccount bankaccount = new BankAccount("NL"//,"retail"
            , 5);

    @PostMapping(value = "pageHandler")
    public String pageHandler(@ModelAttribute User user, Model model) {
        System.out.println("It works!");
        if (testUser != null) {
           model.addAttribute("name", testUser.getUserName());
           model.addAttribute("bankaccount", bankaccount.getIBAN());
           return "transaction";
        }
        return "login failed";
    }
}
