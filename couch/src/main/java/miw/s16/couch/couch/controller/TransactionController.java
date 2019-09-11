package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class TransactionController implements WebMvcConfigurer {


    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    TransactionService transactionService;

    BankAccount accountTo = new BankAccount();


    // if user chooses to make a new transaction
    @GetMapping(value = "transactionRequest")
    public String pageHandlerGet(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // log in session
        Transaction transaction = new Transaction();
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");
        int userId = (int) session.getAttribute("userId");
        BankAccount bankAccountFrom = retailUser1.getBankAccounts().get(0);

        transaction.setBankAccount(accountTo);
        transaction.setFromAccount(accountTo.getIBAN());
        System.out.println("datum - tijd is: " + transaction.getTransactionDate().toString());
        model.addAttribute("transaction", transaction);
        model.addAttribute("date_time", transaction.getTransactionDate().toString());
        model.addAttribute("bankAccountFrom", bankAccountFrom.getIBAN());
        model.addAttribute("bankAccountTo", transaction.getToAccount());
        model.addAttribute("userName", userName);
        model.addAttribute("user", user);
        model.addAttribute("balance", bankAccountFrom.getBalance());
        return "transaction";
    }


    @PostMapping(value="transactionConfirmation")
    public String transactionHandler(@Valid @ModelAttribute(value="transaction") Transaction transaction, Model model, HttpServletRequest request, BindingResult bindingResult) {
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");
        // bank account from
        BankAccount bankAccountFrom = retailUser1.getBankAccounts().get(0);
        double balance = bankAccountFrom.getBalance();
        if(bindingResult.hasErrors()){
            return "transaction";
        }
        model.addAttribute("date_time", transaction.getTransactionDate().toString());model.addAttribute("transaction", transaction);
       String feedback = transactionService.TransactionCalculation(transaction.getToAccount(), bankAccountFrom,
                transaction.getAmount(), transaction.getTransactionDate(), transaction.getDescription(), transaction.getPin());
        model.addAttribute("feedback", feedback);
        model.addAttribute("balance", balance);
        // model.addAttribute("bankAccountFrom", transaction.getFromAccount());
        model.addAttribute("userName", userName);
        return "transaction_feedback";
    }

}
