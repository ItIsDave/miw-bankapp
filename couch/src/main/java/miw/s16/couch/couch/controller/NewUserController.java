package miw.s16.couch.couch.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class NewUserController {

    @GetMapping(value = "newUser")
    public String newUserHandler() {
        return "new_user_selection";
    }
}
