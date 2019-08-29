package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @RequestMapping(value="couch", method= RequestMethod.GET)
    public String appHandler(Model model){
        model.addAttribute("welkom", "Welkom bij Couch Bank");
        return "welcome_template";
    }

    @GetMapping(value="login")
    public String indexHandler(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value = "loginHandler")
    public String loginHandler(@ModelAttribute User user, Model model){
        System.out.println("Ingelogd lid: " + user.getUserName() + ", " + user.getUserPassword());

        // test user for login functionality
        User testUser = new User("Test", "1234", 140);

//    Member m = MemberDao.getMemberByName(member.getName());
        if (testUser != null && testUser.getUserPassword().equals(user.getUserPassword())) {
            List<String> eventList = new ArrayList<>();
            eventList.add("Najaarsborrel");
            eventList.add("Beginnerscursus");
            eventList.add("Ballen clinic");
            eventList.add("Jaarvergadering");
            eventList.add("Feestavond");
            model.addAttribute("name", testUser.getUserName());
            model.addAttribute("allEvents", eventList);
            return "personal_page";
        }
        return "login_failed";
    }

}



