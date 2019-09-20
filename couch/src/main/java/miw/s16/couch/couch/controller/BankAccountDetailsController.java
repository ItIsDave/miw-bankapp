package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.Transaction;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BankAccountDetailsController implements WebMvcConfigurer {

    @Autowired
    BankAccountDao bankAccountDao;

    @PostMapping(value="bankAccountDetailsHandler")
    public String bankAccountDetailsHandler() {
        return "bank_account_details";
    }

    @GetMapping(value = "transactionRequest")
    public String pageHandler(@RequestParam("id") int bankAccountId, Model model, HttpServletRequest request) {
//    public String pageHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        Transaction transaction = new Transaction();
        // log in session
        HttpSession session = request.getSession (true);
        String userName = (String) session.getAttribute("userName");
        User user  = (User) session.getAttribute("user");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");
        int userId = (int) session.getAttribute("userId");
        BankAccount bankAccountFrom = bankAccountDao.findByBankAccountId(bankAccountId);//retailUser1.getBankAccounts().get(0);

        transaction.setBankAccount(bankAccountFrom);
        transaction.setFromAccount(bankAccountFrom.getIBAN());
 //       System.out.println("datum - tijd is: " + transaction.getTransactionDate().toString());
        model.addAttribute("transaction", transaction);
        model.addAttribute("date_time", transaction.getTransactionDate().toString());
        model.addAttribute("bankAccountFrom", bankAccountFrom.getIBAN());
        model.addAttribute("bankAccountTo", transaction.getToAccount());
        model.addAttribute("userName", userName);
        model.addAttribute("user", user);
        model.addAttribute("balance", bankAccountFrom.getBalance());
        model.addAttribute("bankAccountId", bankAccountId);
        return "transaction";
    }

}
