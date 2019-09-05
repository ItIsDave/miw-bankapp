package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    BankAccount accountTo = new BankAccount();


    @PostMapping(value="transactionConfirmation")
    public String transactionHandler(@ModelAttribute User user, Transaction transaction, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");
        // bank account from
        BankAccount bankAccountFrom = retailUser1.getBankAccounts().get(0);
        model.addAttribute("transaction", transaction);
        model.addAttribute("date_time", transaction.getTransactionDate().toString());
        String feedback = transactionService.TransactionCalculation(transaction.getToAccount(), bankAccountFrom, transaction.getAmount());
        model.addAttribute("feedback", feedback);
        // model.addAttribute("bankAccountFrom", transaction.getFromAccount());
        model.addAttribute("userName", userName);
        System.out.println("Voordat transaction is gevuld is transaction: " +
                transaction);
        return "successful_entry";
    }

}
