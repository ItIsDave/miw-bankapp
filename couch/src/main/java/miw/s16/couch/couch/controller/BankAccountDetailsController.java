package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class BankAccountDetailsController {

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    TransactionService transactionService;

    @Autowired
    BankAccountDao bankAccountDao;


    //coding by PH & AV
    @GetMapping(value = "/bankAccountDetails")
    public String bankAccountDetailsHandler(@RequestParam("id") int bankAccountId, Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        //chosen Iban incl balance & transactions collected from DB
        BankAccount clickedBankAccount = bankAccountDao.findByBankAccountId(bankAccountId);
        List <Transaction> transactionList = clickedBankAccount.getTransactions();
        List <Transaction> transactionToList = clickedBankAccount.getTransactionsTo();
        for (Transaction t:transactionToList) { transactionList.add(t); }
        Collections.sort(transactionList);
        Collections.reverse(transactionList);
        model.addAttribute("userName", userName);
        model.addAttribute("iban", clickedBankAccount.getIBAN());
        model.addAttribute("balance", clickedBankAccount.getBalance());
        model.addAttribute("allTransactions", transactionList);
        session.setAttribute("clickedBankAccount", clickedBankAccount);
        session.setAttribute("bankAccountId", clickedBankAccount.getBankAccountId());
        return "bank_account_details";
    }

    @PostMapping(value = "transactionRequest")
    public String pageHandler(@ModelAttribute User user, Model model, HttpServletRequest request, @RequestParam("id") String ibanId) {
        Transaction transaction = new Transaction();
        // log in session
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");
        int userId = (int) session.getAttribute("userId");
        BankAccount bankAccountFrom = bankAccountDao.findByIban(ibanId);

        transaction.setBankAccount(bankAccountFrom);
        transaction.setFromAccount(bankAccountFrom.getIBAN());
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
}
