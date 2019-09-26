package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
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

@Controller
public class NewEnOfAccountController {

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @GetMapping(value = "new_enof_account")
    public String newEnOfAccountHandler(Model model, HttpServletRequest request) {
        // log in session
        HttpSession session = request.getSession(true);
        BankAccount bankAccount = (BankAccount) session.getAttribute("clickedBankAccount");
        System.out.println("GET: bank acc id is " + bankAccount.getBankAccountId());
        System.out.println("GET: bank iban is " + bankAccount.getIBAN());
        System.out.println("GET: bank koppelcode is " + bankAccount.getKoppelcode());
        model.addAttribute("bankAccount", bankAccount);
        return "new_enof_account";
    }

    @PostMapping(value = "new_enof_account")
    public String addUserToAccountHandler(@Valid BankAccount bankAccount, BindingResult bindingResult, HttpServletRequest request){
        System.out.println("POST: bank acc id is " + bankAccount.getBankAccountId());
        System.out.println("POST: bank iban is " + bankAccount.getIBAN());
        System.out.println("POST: bank koppelcode is " + bankAccount.getKoppelcode());
        if(bindingResult.hasErrors()){
            return "new_enof_account";
        } else {
            // log in session
            HttpSession session = request.getSession(true);
            BankAccount sessionBankAccount = (BankAccount) session.getAttribute("clickedBankAccount");
            sessionBankAccount.setKoppelcode(bankAccount.getKoppelcode());
            System.out.println("POST: session bank id " + sessionBankAccount.getBankAccountId());
            System.out.println("POST: session IBAN " + sessionBankAccount.getIBAN());
            System.out.println("POST: session bank koppelcode " + sessionBankAccount.getKoppelcode());
            System.out.println("iban is "+ bankAccount.getIBAN());
            bankAccountDao.save(sessionBankAccount);
            return "new_enof_account_success";
        }
    }

    @GetMapping(value="new_enof_account_success")
    public String newEnOfAccountSuccessHandler(){
        return "new_enof_account_success";
    }
}

