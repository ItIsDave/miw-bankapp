package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;

import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import miw.s16.couch.couch.service.HibernateLab;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//coding by PH & AV

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
    BankAccountDao bankAccountDao;


    @GetMapping
    public String indexHandler(Model model) {
        lab.dbinit();
        User user = new User();
        RetailUser retailUser = new RetailUser();
        model.addAttribute("user", user);
        model.addAttribute("retailUser", retailUser);
        return "index";
    }

    // user log in & user validation and direction to personal page
    @PostMapping(value = "overview")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        List<RetailUser> loggedInRetailUser = retailUserDao.findByUserName(user.getUserName());
        BankAccount loggedInBankAccount = bankAccountDao.findByBankAccountId(user.getBankaccountId());
        if (loginOk) {
            HttpSession session = request.getSession(true);
            // for login session
            session.setAttribute("userName", user.getUserName());
            //session.setAttribute("userId", user.getUserId());
            if (loggedInRetailUser.get(0) != null) {
            model.addAttribute("userName", loggedInRetailUser.get(0).getUserName());
            model.addAttribute("bankAccount", loggedInRetailUser.get(0).getBankAccounts().get(0));
            model.addAttribute( "balance", loggedInBankAccount.getBalance());
            } else {
                model.addAttribute("userName", user.getUserName());
                model.addAttribute("bankAccount", "NL10COUC0523456797");

            }
            return "personal_page";
        }
        return "login_failed";
    }

    // user returns to personal page
    @GetMapping(value = "overview")
    public String overviewHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String userName = (String) session.getAttribute("userName");
        session.setAttribute("userName", userName);
        model.addAttribute("userName", userName);
        return "personal_page";
    }


    @GetMapping(value = "newUser")
    public String newUserHandler() {
        return "new_user_select_type";
    }
}
