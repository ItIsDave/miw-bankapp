package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;
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
public class TransactionController {


    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    TransactionService transactionService;

    BankAccount accountTest = new BankAccount("NL10COUC0423456793", 5000.00);

    BankAccount bankaccount = new BankAccount("NLXXCOUC0123456789", 1000);


    @PostMapping(value="transactionConfirmation")
    public String transactionHandler(@ModelAttribute User user, Transaction transaction, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("transaction", transaction);
        model.addAttribute("date_time", transaction.getTransactionDate().toString());
        String feedback = transactionService.TransactionCalculationTest(accountTest, bankaccount, transaction.getAmount());
        model.addAttribute("feedback", feedback);
        model.addAttribute("bankaccountFrom", transaction.getFromAccount());
        model.addAttribute("userName", userName);
        System.out.println("Voordat transaction is gevuld is transaction: " +
                transaction);
        return "successful_entry";
    }


}
