package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class NewEnOfAccountController {

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    //Omzetten rekening in en/of rekening
    @GetMapping(value = "new_enof_account")
    public String newEnOfAccountHandler(Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        BankAccount bankAccount = (BankAccount) session.getAttribute("clickedBankAccount");
        model.addAttribute("bankAccount", bankAccount);
        return "new_enof_account";
    }

    @PostMapping(value = "new_enof_account")
    public String addUserToAccountHandler(@Valid BankAccount bankAccount, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "new_enof_account";
        } else {
            // log in session
            HttpSession session = request.getSession(true);
            BankAccount sessionBankAccount = (BankAccount) session.getAttribute("clickedBankAccount");
            sessionBankAccount.setKoppelcode(bankAccount.getKoppelcode());
            bankAccountDao.save(sessionBankAccount);
            return "new_enof_account_success";
        }
    }

    @GetMapping(value="new_enof_account_success")
    public String newEnOfAccountSuccessHandler(){
        return "new_enof_account_success";
    }


    //Invoeren koppelcode en koppelen aan rekening
    @GetMapping(value="Koppelcode_invoeren")
    public String enOfKoppelingHandler(Model model){
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIBAN(null);
        model.addAttribute("bankAccount", bankAccount);
        return "fillout_koppelcode";
    }

    @PostMapping(value = "Koppelcode_invoeren")
    public String koppelcodeValidationHandler(@Valid BankAccount bankAccount,  BindingResult bindingResult, Model model, HttpServletRequest request){
        // log in session
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        RetailUser loggedInUser = retailUserDao.findByUserName(userName).get(0);
        if(bindingResult.hasErrors()){
            return "fillout_koppelcode";
        }
        BankAccount enofAccount = bankAccountDao.findByIban(bankAccount.getIBAN());
        if (!bankAccount.getKoppelcode().equals(enofAccount.getKoppelcode()) ){
            return "fillout_koppelcode";
        }
        enofAccount.addRetailUser(loggedInUser);
        loggedInUser.addBankAccount(enofAccount);
        bankAccountDao.save(enofAccount);
        retailUserDao.save(loggedInUser);
        List<BankAccount> loggedInBankAccounts  = loggedInUser.getBankAccounts();
        model.addAttribute("userName", userName);
        model.addAttribute("allBankAccounts", loggedInBankAccounts);
        return "personal_page";
    }
}

