package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class CompanyPageController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    BankAccountDao bankAccountDao;

    // zakelijk klant login
    @RequestMapping(value = "zakelijk-klant")
    public String smeLoginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        HttpSession session = request.getSession(true);
        List<SMEUser> loggedInUser = smeUserDao.findByUserName(user.getUserName());
        if (loginOk) {
            Company company = loggedInUser.get(0).getCompany();
            String companyData = company.getChamberOfCommerceId() + " " + company.getCompanyName() + "access : " + loggedInUser.get(0).getRoleEmployee();
            session.setAttribute("companyKvK", loggedInUser.get(0).getCompany().getChamberOfCommerceId());
            session.setAttribute("userName", loggedInUser.get(0).getUserName());
            session.setAttribute("fullNames",companyData);
            model.addAttribute("userName", loggedInUser.get(0).getUserName());
            model.addAttribute("role", loggedInUser.get(0).getRoleEmployee());
            model.addAttribute("companyName", loggedInUser.get(0).getCompany().getCompanyName());
            model.addAttribute("companyAccounts", loggedInUser.get(0).getCompany().getCompanyAccounts().get(0));
            model.addAttribute("allBankAccounts", loggedInUser.get(0).getCompany().getCompanyAccounts());
            return "sme_page";
        }
        return "login_failed";
    }

    @PostMapping(value = "newCompanyAccountRequest")
    public String newCompanyAccountRequestHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        int kvkNr = (int) session.getAttribute("companyKvK");
        Company currentCompany = companyDao.findBychamberOfCommerceId(kvkNr);
        SMEUser loggedInUser = smeUserDao.findByUserName(userName).get(0);
        BankAccount newBankAccount = new BankAccount();
        currentCompany.addCompanyAccount(newBankAccount);
        bankAccountDao.save(newBankAccount);
        companyDao.save(currentCompany);
        List<BankAccount> bankAccountsList = currentCompany.getCompanyAccounts();
        model.addAttribute("userName", userName);
        model.addAttribute("role", loggedInUser.getRoleEmployee());
        model.addAttribute("companyName", loggedInUser.getCompany().getCompanyName());
        model.addAttribute("companyAccounts", loggedInUser.getCompany().getCompanyAccounts().get(0));
        model.addAttribute("allBankAccounts", bankAccountsList);
        return "sme_page";
    }

    // for company account
    @GetMapping(value = "/companyAccountDetails")
    public String companyAccountDetailsHandler(@RequestParam("id") int bankAccountId, Model model, HttpServletRequest request){
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        //chosen Iban incl balance & transactions collected from DB
        BankAccount clickedBankAccount = bankAccountDao.findByBankAccountId(bankAccountId);
        List <Transaction> transactionList = clickedBankAccount.getTransactions();
        List <Transaction> transactionToList = clickedBankAccount.getTransactionsTo();
        for (Transaction t:transactionToList) { transactionList.add(t); }
        Collections.sort(transactionList);
        Collections.reverse(transactionList);
        model.addAttribute("iban", clickedBankAccount.getIBAN());
        model.addAttribute("balance", clickedBankAccount.twoDecimalBalance(clickedBankAccount.getBalance()));
        model.addAttribute("allTransactions", transactionList);
        model.addAttribute("fullNames", session.getAttribute("fullNames"));
        session.setAttribute("clickedBankAccount",clickedBankAccount.getIBAN());
        session.setAttribute("bankAccountId", clickedBankAccount.getBankAccountId());
        return "company_account_details";
    }
}
