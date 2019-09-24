package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;

import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import miw.s16.couch.couch.service.HibernateLab;
import miw.s16.couch.couch.service.PasswordValidator;
import miw.s16.couch.couch.service.TestdataCreator;
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
    TestdataCreator testData;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    UserDao userDao;

    @GetMapping
    public String indexHandler(Model model) {
        lab.dbinit();
        User user = new User();
//        RetailUser retailUser = new RetailUser(); not needed (found while working on Bank User login controller which was a copy) - BvB
        model.addAttribute("user", user);
//        model.addAttribute("retailUser", retailUser);
        return "index";
    }

    // user log in & user validation and direction to personal page
    @PostMapping(value = "overview")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        String userName = user.getUserName();
        List<RetailUser> loggedInRetailUser = retailUserDao.findByUserName(userName);
        //ophalen & opslaan alle bankaccounts die bij deze user horen
        RetailUser retailUser = loggedInRetailUser.get(0);//BvB
        List <BankAccount> loggedInBankAccounts = retailUser.getBankAccounts();//loggedInRetailUser.get(0).getBankAccounts();

        if (loginOk) {
            HttpSession session = request.getSession(true);
            // -- for login session ---
            session.setAttribute("userName", userName);//user.getUserName();
            session.setAttribute("retailUser", retailUser);//loggedInRetailUser.get(0)); - BvB
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("userName", retailUser.getUserName());//loggedInRetailUser.get(0).getUserName()); - BvB
            model.addAttribute( "retailUserFullName", retailUser.getFullName());//BvB
            model.addAttribute("allBankAccounts", loggedInBankAccounts);
            return "personal_page";
        }
        return "login_failed";
    }

    @GetMapping(value = "newUser")
    public String newUserHandler() {
        return "new_user_select_type";
    }
}
