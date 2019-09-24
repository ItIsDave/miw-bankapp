package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CompanyPageController {

    @Autowired
    PasswordValidator validator;

    // user login
    @PostMapping(value="zakelijk-klant")
    public String smeLoginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);

    }

}
