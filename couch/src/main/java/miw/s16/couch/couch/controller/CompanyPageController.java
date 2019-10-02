package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.*;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.CompanyDao;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    // user login
    @PostMapping(value = "zakelijk-klant")
    public String smeLoginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        HttpSession session = request.getSession(true);
        List<SMEUser> loggedInUser = smeUserDao.findByUserName(user.getUserName());
        if (loginOk) {
            session.setAttribute("companyKvK", loggedInUser.get(0).getCompany().getChamberOfCommerceId());
            session.setAttribute("userName", loggedInUser.get(0).getUserName());
            model.addAttribute("userName", loggedInUser.get(0).getUserName());
            model.addAttribute("role", loggedInUser.get(0).getRoleEmployee());
            model.addAttribute("companyName", loggedInUser.get(0).getCompany().getCompanyName());
            model.addAttribute("companyAccounts", loggedInUser.get(0).getCompany().getCompanyAccounts().get(0));
            model.addAttribute("allBankAccounts", loggedInUser.get(0).getCompany().getCompanyAccounts());
            return "sme_page";
        }
        return "login_failed";
    }

    // to do  --- display account manager

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
}
