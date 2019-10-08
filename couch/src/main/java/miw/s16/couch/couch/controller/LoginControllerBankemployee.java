package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.User;
import miw.s16.couch.couch.model.dao.BankUserDao;
import miw.s16.couch.couch.model.dao.UserDao;
import miw.s16.couch.couch.service.BalanceTopTen;
import miw.s16.couch.couch.service.PasswordValidator;
import miw.s16.couch.couch.service.TypeOfUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginControllerBankemployee {

    @Autowired
    TypeOfUserValidator validatorType;

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
        User user2 = new User();
        model.addAttribute("user2", user2);
        return "index_bankemployee";
    }

    @PostMapping(value = "overview_bankemployee")
    public String loginHandler(@ModelAttribute User user, Model model, HttpSession session) {
//added by BvB, copied from LoginController
        if (!validatorType.validateBankUser(user)) {
            return "login_failed_bankemployee";
        }
        boolean loginOk = validator.validateMemberPassword(user);
        List<BankUser> loggedInBankUser = bankUserDao.findByUserName(user.getUserName());
        if (loginOk) {
            // -- for login session ---
            session.setAttribute("user", user);  //vereenvoudigd naar user
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

    @GetMapping(value = "logout")
    public String logoutAnyUser(Model model, HttpSession httpSession) {
        //geen annotatie voor HttpSession zetten.
    //model.addAttribute("user", (User) httpSession.getAttribute("user") );  //het is een user object, omdat een user object is meegegeven in de pagina x
    System.out.println("logout retailUser or bankUser");
    httpSession.invalidate();  // invalidates the current session and unbinds any objects that were previously bound to it.
    //met behulp van de html pagina "wie_ben_ik" icm de handler verliesMij en de pagina wie_ben_ik_nu getest dat dit werkt
    return "logged_out";
    }

    //deze handler was nodig om te testen of .invalidate() werkt
   /* @GetMapping(value = "verlies_mij")
    public String verliesMij(Model model, HttpSession httpSession) {
        model.addAttribute("user", (User) httpSession.getAttribute("user") );  //waarschijnlijk foutvermelding naar deze regel
        System.out.println("nu kwijt?");
        return "wie_ben_ik_nu";
    }*/


}