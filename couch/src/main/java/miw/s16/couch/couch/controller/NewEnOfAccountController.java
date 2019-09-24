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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addUserToAccountHandler(@Valid BankAccount bankAccount, BindingResult bindingResult){
        System.out.println("POST: bank acc id is " + bankAccount.getBankAccountId());
        System.out.println("POST: bank iban is " + bankAccount.getIBAN());
        System.out.println("POST: bank koppelcode is " + bankAccount.getKoppelcode());
        if(bindingResult.hasErrors()){
            return "new_enof_account";
        } else {
            System.out.println("iban is "+ bankAccount.getIBAN());
            System.out.println("koppelcode is "+ bankAccount.getKoppelcode());
            bankAccountDao.save(bankAccount);
            return "personal_page";
        }
    }
}

