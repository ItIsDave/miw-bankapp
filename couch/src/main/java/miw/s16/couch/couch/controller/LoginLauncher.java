package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.BankUser;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.SMEUser;
import miw.s16.couch.couch.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginLauncher {

    public static void main(String[] args) {
//        BankUser bankuser = new BankUser("userBankuser", "pwBankUser", 23, "hoofdMKB");
        SMEUser smeuser = new SMEUser("userSmeuser", "pwSmeUser", 23, 23,
                "Aliebalie", "baliemedewerker");
//        RetailUser retailuser = new RetailUser("userRetailUser", "pwRetailUser", 23,
//                23, "Alie", "", "Balie",
//                "retailstraat", 23, "",
//                "23", "Retail", 23,
//                23, "oeps", "eigenaar");

        LoginController lc = new LoginController();
        System.out.println(smeuser);

    }
}

