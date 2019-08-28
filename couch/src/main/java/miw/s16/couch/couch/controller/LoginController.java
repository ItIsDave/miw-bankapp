package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @Autowired
    JdbcTemplate jdbc;

    /** for testing of database & data entry connection:
     * Uncomment code below, run server, go to localhost:8080/insert
     * and then check your local mysql database
     * @author adamantia
     */

//    @RequestMapping("/insert")
//    public String insertData(){
//        // in our database userId has to autoincrement, this is for testing only
//        jdbc.execute("insert into user(userName,userPassword, idUser)values('userTest','1234', 10)");
//        return"successful_entry";
//    }

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

    @PostMapping(value="loginHandler")
    public String loginHandler(@ModelAttribute User user, Model model){
        System.out.println("Ingelogd lid: " + user.getUserName() + ", " + user.getUserPassword());

        // test user for login functionality
        User testUser = new User("Test", "1234", 140);
        BankUser bankuser = new BankUser("userBankuser", "pwBankUser", 23, "hoofdMKB");
        SMEUser smeuser = new SMEUser("userSmeuser", "pwSmeUser", 23, 23, "Aliebalie", "baliemedewerker");
        RetailUser retailuser = new RetailUser("userRetailUser", "pwRetailUser",23,
        23, "Alie", "", "Balie",
                "retailstraat", 23, "",
                "23", "Retail", 23,
        23, "oeps", "eigenaar");

        if (testUser != null && testUser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", testUser.getUserName());
            return "personal_page";
        }
        if (bankuser != null && bankuser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", bankuser.getUserName());
            return "personal_page";
        }
        if (smeuser != null && smeuser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", smeuser.getUserName());
            return "personal_page";
        }
        if (retailuser != null && retailuser.getUserPassword().equals(user.getUserPassword())) {
            model.addAttribute("name", retailuser.getUserName());
            return "personal_page";
        }
        return"login failed";

    }

}



