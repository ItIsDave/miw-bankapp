package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController<retailUser> {


    @Autowired
    RetailUserDao retailUserDao;


    //user for testing
    private User testUser = new User("Test", "1234");
    private BankAccount bankAccount = new BankAccount("NL"//,"retail"
            , 5);

    @PostMapping(value = "pageHandler")
    public String pageHandler(@ModelAttribute User user, Model model) {
        if (testUser != null) {
           model.addAttribute("name", testUser.getUserName());
           model.addAttribute("bankaccount", bankAccount.getIBAN());
           return "transaction";
        }
        return "login failed";
    }
}
