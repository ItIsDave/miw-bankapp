package miw.s16.couch.couch.controller;


import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class RetailPersonalPageController<retailUser> {


    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    TransactionService transactionService;

    @Autowired
    BankAccountDao bankAccountDao;

    // user returns to personal page (coding by AT)
    @GetMapping(value = "overview")
    public String overviewHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");

        List <BankAccount> loggedInBankAccounts = retailUser1.getBankAccounts();
//        String bankAccount = retailUser1.getBankAccounts().get(0).getIBAN();
        session.setAttribute("userName", userName);
//        session.setAttribute("bankAccount", bankAccount);
        model.addAttribute("userName", userName);
//        model.addAttribute("bankAccount", bankAccount);
        model.addAttribute("allBankAccounts", loggedInBankAccounts);
        return "personal_page";
    }

    //coding by PH & AV
    @PostMapping(value = "newAccountRequest")
    public String newAccountRequestHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1 = (RetailUser) session.getAttribute("retailUser");
        //nieuwe IBAN wordt aangemaakt, aan retailuser gekoppeld en in DB opgeslagen
        BankAccount newBankAccount = new BankAccount();
        retailUser1.addBankAccount(newBankAccount);
        bankAccountDao.save(newBankAccount);
        retailUserDao.save(retailUser1);
        //collect all bankaccounts in 1 list
        List<BankAccount> bankAccountsList = retailUser1.getBankAccounts();
        model.addAttribute("userName", userName);
        model.addAttribute("allBankAccounts", bankAccountsList);
        return "personal_page";
    }
}


