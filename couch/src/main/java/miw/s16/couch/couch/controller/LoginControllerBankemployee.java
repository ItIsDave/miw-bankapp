package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.BankUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import miw.s16.couch.couch.service.BalanceTopTen;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginControllerBankemployee {

    @Autowired
    PasswordValidator validator;

    @Autowired
    BankUserDao bankUserDao;

    @Autowired
    UserDao userDao;

    @Autowired
    BalanceTopTen balanceTopTen;




    @PostMapping(value = "overview_bankemployee")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        List<BankUser> loggedInBankUser = bankUserDao.findByUserName(user.getUserName());
        //ophalen & opslaan alle bankaccounts die bij deze user horen
        //List <BankAccount> loggedInBankAccounts = loggedInRetailUser.get(0).getBankAccounts();

        if (loginOk) {
            HttpSession session = request.getSession(true);
            // -- for login session ---
            session.setAttribute("userName", user.getUserName());
            //session.setAttribute("retailUser", loggedInRetailUser.get(0));
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("userName", loggedInBankUser.get(0).getUserName());
            model.addAttribute("role", loggedInBankUser.get(0).getRole());
            //model.addAttribute("allEvents", eventList); hier komt arrayList van strings
            ArrayList<String> top10IBANList = balanceTopTen.balanceTopTen_IBAN();
            model.addAttribute("top10_IBAN_List",top10IBANList);
            model.addAttribute("listSize", top10IBANList.size());
            //model.addAttribute("allBankAccounts", loggedInBankAccounts);
            return "personal_page_bankemployee";
        }
        return "login_failed_bankemployee";
    }

}