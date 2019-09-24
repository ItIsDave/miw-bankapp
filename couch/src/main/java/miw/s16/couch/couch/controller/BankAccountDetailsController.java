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
import org.springframework.web.bind.annotation.*;

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
        session.setAttribute("clickedIBAN", clickedBankAccount.getIBAN());
        List <Transaction> transactionList = clickedBankAccount.getTransactions();
        List <Transaction> transactionToList = clickedBankAccount.getTransactionsTo();
        for (Transaction t:transactionToList) { transactionList.add(t); }
        Collections.sort(transactionList);
        Collections.reverse(transactionList);
        model.addAttribute("userName", userName);
        model.addAttribute("iban", clickedBankAccount.getIBAN());
        model.addAttribute("balance", clickedBankAccount.getBalance());
        model.addAttribute("allTransactions", transactionList);
        return "bank_account_details";
    }

}
