package miw.s16.couch.couch.controller;


import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PageController<retailUser> {


    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    TransactionService transactionService;


    BankAccount accountTest = new BankAccount("NL10COUC0423456793", 5000.00);


    private BankAccount bankaccount = new BankAccount("NLXXCOUC0123456789"//,"retail"
            , 50);

    Transaction transaction = new Transaction();

    @PostMapping(value = "transactionRequest")
    public String pageHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        int userId = (int) session.getAttribute("userId");
        System.out.println("datum - tijd is: " + transaction.getTransactionDate().toString());
        model.addAttribute("transaction", transaction);
        model.addAttribute("date_time", transaction.getTransactionDate().toString());
        transaction.setFromAccount(bankaccount.getIban());
        model.addAttribute("bankaccountFrom", transaction.getFromAccount());
        //if (principal != null) {
           model.addAttribute("userName", userName);
            System.out.println("Voordat transaction is gevuld is transaction: " +
                    transaction);
           return "transaction";
        //}
       // return "login failed";
    }
}
