package miw.s16.couch.couch.controller;

import miw.s16.couch.couch.model.Transaction;

import java.util.Date;

public class TransactionLauncher {
    public static void main(String[] args) {
        Date d = new Date();
        Transaction t = new Transaction("verjaardag",50.00,d,"NL82 INGB 0004 2181 41","NL45 ABNA 0976 0833 96",0,false);
        System.out.println(t);
    }
}
