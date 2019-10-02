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

    @GetMapping(value = "bankemployee")
    public String indexHandler(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index_bankemployee";
    }

    @PostMapping(value = "overview_bankemployee")
    public String loginHandler(@ModelAttribute User user, Model model, HttpServletRequest request) {
        boolean loginOk = validator.validateMemberPassword(user);
        List<BankUser> loggedInBankUser = bankUserDao.findByUserName(user.getUserName());
        if (loginOk) {
            HttpSession session = request.getSession(true);
            // -- for login session ---
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("userName", loggedInBankUser.get(0).getUserName());
            String bankUserRole = loggedInBankUser.get(0).getRole();
            model.addAttribute("bankUserRole", loggedInBankUser.get(0).getRole());

            //nadat de pagina is aangepast, 3 booleans kunnen weg
            boolean roleBooleanHoofdParticulieren = (loggedInBankUser.get(0).getRole().equals("Hoofd Particulieren"));
            model.addAttribute("roleBooleanHP", roleBooleanHoofdParticulieren);
            boolean roleBooleanHoofdMKB = (loggedInBankUser.get(0).getRole().equals("Hoofd MKB"));
            model.addAttribute("roleBooleanHMKB", roleBooleanHoofdMKB);
            boolean roleBooleanAccountManager = (loggedInBankUser.get(0).getRole().equals("Account Manager"));
            model.addAttribute("roleBooleanAM", roleBooleanAccountManager);
            ArrayList<String> top10ClientList = balanceTopTen.balanceTopTen_Client(bankUserRole);
            model.addAttribute("top10_Client_List",top10ClientList);
            model.addAttribute("listSize", top10ClientList.size());
            return "personal_page_bankemployee";
        }
        return "login_failed_bankemployee";
    }
}