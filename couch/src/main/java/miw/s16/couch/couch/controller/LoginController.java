package miw.s16.couch.couch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    JdbcTemplate jdbc;

    /** for testing of database & data entry connection:
     * Uncomment code below, run server, go to localhost:8080/insert
     * and then check your local mysql database
     * @author adamantia
     */

    @RequestMapping("/insert")
    public String insertData(){
        // in our database userId has to autoincrement, this is for testing only
        jdbc.execute("insert into user(userName,userPassword, idUser)values('userTest','1234', 10)");
        return"successful_entry";
    }

    //    @RequestMapping(value ="welkom", method = RequestMethod.GET)
//    public String welkomHandler(@RequestParam String naam, Model model) {
//        model.addAttribute("naam", naam);
//        return "login_template";
//    }
}



