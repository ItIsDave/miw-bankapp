package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
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

    BankAccount accountTo = new BankAccount();


    @PostMapping(value="transactionConfirmation")
    public String transactionHandler(@ModelAttribute User user, Transaction transaction, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");
        // bank account from
        BankAccount bankAccountFrom = retailUser1.getBankAccounts().get(0);
        // this transaction is not a loan
        Loan loan = new Loan();
        model.addAttribute("date_time", transaction.getTransactionDate().toString());model.addAttribute("transaction", transaction);
       String feedback = transactionService.TransactionCalculation(transaction.getToAccount(), bankAccountFrom,
                transaction.getAmount(), transaction.getTransactionDate(), transaction.getDescription(), transaction.getPin(), transaction.getLoanId());
        model.addAttribute("feedback", feedback);
        // model.addAttribute("bankAccountFrom", transaction.getFromAccount());
        model.addAttribute("userName", userName);
        return "transaction_feedback";
    }

}
