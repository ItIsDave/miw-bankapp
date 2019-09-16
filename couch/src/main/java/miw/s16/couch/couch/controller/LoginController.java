package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;

import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import miw.s16.couch.couch.service.HibernateLab;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    HibernateLab lab;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    UserDao userDao;

    @GetMapping
    public String indexHandler(Model model) {
        lab.dbinit();
        User user = new User();
        RetailUser retailUser = new RetailUser();
        model.addAttribute("user", user);
        model.addAttribute("retailUser", retailUser);
        return "index";
    }
    // coding PH & AV
    // user log in & user validation and direction to personal page
    @PostMapping(value = "overview")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        List<RetailUser> loggedInRetailUser = retailUserDao.findByUserName(user.getUserName());
        //ophalen & opslaan alle bankaccounts die bij deze user horen
        List <BankAccount> loggedInBankAccounts = loggedInRetailUser.get(0).getBankAccounts();

        if (loginOk) {
            HttpSession session = request.getSession(true);
            // -- for login session ---
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("retailUser", loggedInRetailUser.get(0));
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("userName", loggedInRetailUser.get(0).getUserName());
            model.addAttribute("allBankAccounts", loggedInBankAccounts);
            return "personal_page";
        }
        return "login_failed";
    }

    // user returns to personal page (coding by AT)
    @GetMapping(value = "overview")
    public String overviewHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        RetailUser retailUser1  = (RetailUser) session.getAttribute("retailUser");

        List <BankAccount> loggedInBankAccounts = retailUser1.getBankAccounts();
        String bankAccount = retailUser1.getBankAccounts().get(0).getIBAN();
        session.setAttribute("userName", userName);
        session.setAttribute("bankAccount", bankAccount);
        model.addAttribute("userName", userName);
        model.addAttribute("bankAccount", bankAccount);
        model.addAttribute("allBankAccounts", loggedInBankAccounts);
        return "personal_page";
    }


    @GetMapping(value = "newUser")
    public String newUserHandler() {
        return "new_user_select_type";
    }
}
