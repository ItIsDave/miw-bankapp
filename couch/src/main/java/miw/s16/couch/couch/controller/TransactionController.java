package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.entity.BankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TransactionController {


    @PostMapping(value="transactionHandler")
    public String transactionHandler(@ModelAttribute Transaction transaction,Model model) {
        System.out.println("De transactie is ingevuld: " + transaction.toString());
        return "successful_entry";
    }


}
