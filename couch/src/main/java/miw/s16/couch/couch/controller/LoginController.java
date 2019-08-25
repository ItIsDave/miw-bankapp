package miw.s16.couch.couch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value="aap", method= RequestMethod.GET)
    public String appHandler(Model model){
        model.addAttribute("welkom", "Welkom!");
        return "noot";
    }

    @RequestMapping(value ="welkom", method = RequestMethod.GET)
    public String welkomHandler(@RequestParam String naam, Model model) {
        model.addAttribute("naam", naam);
        return "login_template";
    }
}



