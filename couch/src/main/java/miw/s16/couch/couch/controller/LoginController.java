package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;

import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import miw.s16.couch.couch.service.HibernateLab;
import miw.s16.couch.couch.service.PasswordValidator;
import miw.s16.couch.couch.service.TestdataCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    TestdataCreator testData;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    UserDao userDao;

    @GetMapping
    public String indexHandler(Model model) {
        lab.dbinit();
        User user = new User();
        RetailUser retailUser = new RetailUser();
        SMEUser smeUser = new SMEUser();
        BankUser bankUser = new BankUser();
        model.addAttribute("retailUser", retailUser);
        model.addAttribute("smeUser", smeUser);
        model.addAttribute("user", user);
        model.addAttribute("bankUser", bankUser);
        return "index";
    }

    // user log in & user validation and direction to personal page
    @PostMapping(value = "overview")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);

//adding full name for naming (tenaamstelling) purposes, and some code cleaning - BvB
        if (loginOk) {
            HttpSession session = request.getSession(true);
            String userName = user.getUserName();//BvB
            List<RetailUser> loggedInRetailUsers = retailUserDao.findByUserName(userName);
            //ophalen & opslaan alle bankaccounts die bij deze user horen
            RetailUser loggedInRetailUser = loggedInRetailUsers.get(0);
            List<BankAccount> loggedInBankAccounts = loggedInRetailUser.getBankAccounts();
            // -- for login session ---
            session.setAttribute("userName", userName);
            session.setAttribute("retailUser", loggedInRetailUser);
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("userName", loggedInRetailUser.getUserName());
            model.addAttribute("retailUserFullName", loggedInRetailUser.getFullName());
            model.addAttribute("allBankAccounts", loggedInBankAccounts);
            return "personal_page";
        }
        return "login_failed";
    }


    @GetMapping(value ="zakelijk-login")
    public String newCompanyHandler(@ModelAttribute User user, Model model) {
        SMEUser smeUser = new SMEUser();
        model.addAttribute("user", user);
        model.addAttribute("smeUser", smeUser);
        return "company_login";
    }


    @GetMapping(value = "newUser")
    public String newUserHandler() {
        return "new_user_select_type";
    }
}
