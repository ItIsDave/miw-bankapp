package miw.s16.couch.couch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {
     @RequestMapping(value = "/userName", method = RequestMethod.GET)
    @ResponseBody
     public String currentUserName(Principal principal){
         return principal.getName();
     }

}
