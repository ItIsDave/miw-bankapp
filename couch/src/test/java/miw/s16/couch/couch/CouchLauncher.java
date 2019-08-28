package miw.s16.couch.couch;

import miw.s16.couch.couch.model.entity.BankAccount;

public class CouchLauncher {

    public static void main(String[] args) {
        BankAccount bankaccount = new BankAccount("NL", "retail", 5);
        System.out.println(bankaccount);

    }
}
