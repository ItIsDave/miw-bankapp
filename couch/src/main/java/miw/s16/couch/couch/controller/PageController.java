package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.Transaction;
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
    private BankAccount bankaccount = new BankAccount("NLXXCOUC0123456789"//,"retail"
            , 50);

    Transaction transaction = new Transaction();

    @PostMapping(value = "pageHandler")
    public String pageHandler(@ModelAttribute User user, Model model) {
        System.out.println("It works!");
        System.out.println("datum - tijd is: " + transaction.getTransactionDate().toString());
        model.addAttribute("transaction", transaction);
        model.addAttribute("date_time", transaction.getTransactionDate().toString());
        transaction.setFrom(bankaccount.getIBAN());
        model.addAttribute("bankaccountFrom", transaction.getFrom());
        if (testUser != null) {
           model.addAttribute("name", testUser.getUserName());
            System.out.println("Voordat transaction is gevuld is transaction: " +
                    transaction);
           return "transaction";
        }
        return "login failed";
    }
}
