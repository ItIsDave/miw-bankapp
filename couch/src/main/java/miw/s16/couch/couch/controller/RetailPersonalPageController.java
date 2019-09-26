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
        RetailUser retailUser = retailUserDao.findByUserName(userName).get(0);
        List<BankAccount> loggedInBankAccounts  = retailUser.getBankAccounts();
        model.addAttribute("userName", userName);
        model.addAttribute("allBankAccounts", loggedInBankAccounts);
        return "personal_page";
    }

    //coding by PH & AV
    @PostMapping(value = "newAccountRequest")
    public String newAccountRequestHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        //RetailUser retailUser1 = (RetailUser) session.getAttribute("retailUser");
        RetailUser retailUser = retailUserDao.findByUserName(userName).get(0);
        //nieuwe IBAN wordt aangemaakt, aan retailuser gekoppeld en in DB opgeslagen
        BankAccount newBankAccount = new BankAccount();
        retailUser.addBankAccount(newBankAccount);
        bankAccountDao.save(newBankAccount);
        retailUserDao.save(retailUser);
        //collect all bankaccounts in 1 list
        List<BankAccount> bankAccountsList = retailUser.getBankAccounts();
        model.addAttribute("userName", userName);
        model.addAttribute("allBankAccounts", bankAccountsList);
        return "personal_page";
    }
}


