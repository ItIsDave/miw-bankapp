package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

public class BankAccountDetailsController {

    @PostMapping(value="bankAccountDetailsHandler")
    public String bankAccountDetailsHandler() {
        return "bank_account_details";
    }
}
