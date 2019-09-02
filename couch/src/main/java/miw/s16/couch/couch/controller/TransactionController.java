package miw.s16.couch.couch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TransactionController {


    @PostMapping(value="transactionHandler")
    public String transactionHandler() {
        return "successful_entry";
    }


}
