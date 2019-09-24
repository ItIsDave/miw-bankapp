package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.SMEUserDao;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CompanyPageController {

    @Autowired
    PasswordValidator validator;

    @Autowired
    SMEUserDao smeUserDao;

    // user login
    @PostMapping(value="zakelijk-klant")
    public String smeLoginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        List<SMEUser> loggedInUser = smeUserDao.findByUserName(user.getUserName());
        if (loginOk) {
            model.addAttribute("userName", loggedInUser.get(0).getUserName());
            model.addAttribute("role", loggedInUser.get(0).getRoleEmployee());
            model.addAttribute("companyName", loggedInUser.get(0).getCompany().getCompanyName());
            model.addAttribute("companyAccounts", loggedInUser.get(0).getCompany().getCompanyAccounts().get(0));
            return "sme_page";
        }
        return "login_failed";
    }
}
