package miw.s16.couch.couch.service;


import miw.s16.couch.couch.model.*;

import miw.s16.couch.couch.model.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.util.*;

import static java.lang.Boolean.FALSE;


@Service
public class HibernateLab {


    @Autowired
    UserDao userDao;

    @Autowired
    RetailUserDao retailUserDao;

    @Autowired
    BankAccountDao bankAccountDao;

    @Autowired
    TransactionDao transactionDao;

    @Autowired
    TestdataCreator testData;

    @Autowired
    BankUserDao bankUserDao;

    @Autowired
    SMEUserDao smeUserDao;

    @Autowired
    CompanyDao companyDao;


    public HibernateLab() {
        super();
    }

    public void dbinit() {

        // to ensure no data duplication
       if (userDao.findByUserPassword("1").size() == 0) {
            User johnDoe = new User("John Doe", "1");
            // user info for checking if DB is empty
          /*  User johnDoe = new User("John Doe", "1");


            // Creating Bank account data
            BankAccount account1 = new BankAccount("NL10COUC0123456790", 80.00);
            BankAccount account2 = new BankAccount("NL10COUC0223456791", 10.00);
            BankAccount account3 = new BankAccount("NL10COUC0323456792", 50.00);
            BankAccount account4 = new BankAccount("NL10COUC0423456793", 80.00);
            BankAccount account5 = new BankAccount("NL10COUC0523456794", 100.00);

            // Creating Retail user data
            RetailUser bart = new RetailUser("Bart", "1234",  987654321, "Bart",
                    "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");
            RetailUser charlotte = new RetailUser("Charlotte", "1234", 987654322,
                    "Charlotte",  "de", "Witte", "Keizersgracht", 40,
                    "A", "1017DS", "Amsterdam", "690000001", "25-10-1999", "cdv@gmail.com", "Retail");
            RetailUser karin = new RetailUser("Karin", "1234", 987654325,
                    "Karin",  "van", "Dijk", "Prinsengracht", 200,
                    "A", "1017KS", "Amsterdam", "690000801", "25-10-1990", "kvd@gmail.com", "Retail");
            RetailUser jan = new RetailUser("Jan", "1234", 987654326,
                    "Jan",  "", "Bakken", "Herengracht", 100,
                    "C", "1018AC", "Amsterdam", "6901230801", "25-10-1989", "jbakker@gmail.com", "Retail");


            //Creating transaction data
            Date d = new Date();
            RetailUser boudewijn = new RetailUser("Boudewijn", "1234",  987654321, "Boudewijn",
                    "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");

            // the to and from are strings. For the test we are going to connect each transaction with one of the retail users bank account
            Transaction t1 = new Transaction(100.0, d, account1, account2, "verjaardag");
            Transaction t2 = new Transaction(10.0, d, account1, account3, " ");
            Transaction t3 = new Transaction(500.0, d, account4, account1, " ");
            Transaction t4 = new Transaction(1500.0, d, account5, account4, " ");

            // confirmation that DB is running
            System.out.println("Creating schema");
            charlotte.addBankAccount(account2);
            bart.addBankAccount(account1);
            jan.addBankAccount(account5);


            // generate Iban method not ready yet
            BankAccount account6 = new BankAccount("NL10COUC0523456795", 13000);

            boudewijn.addBankAccount(account6);

            bankAccountDao.save(account1);
            bankAccountDao.save(account2);
            bankAccountDao.save(account3);
            bankAccountDao.save(account4);
            bankAccountDao.save(account5);
            bankAccountDao.save(account6);

            transactionDao.save(t1);
            transactionDao.save(t2);
            transactionDao.save(t3);
            transactionDao.save(t4);

            // saving to the db
            userDao.save(johnDoe);
            userDao.save(adamantia);
            userDao.save(alet);
            userDao.save(arnout);
            userDao.save(david);
            userDao.save(patrick);

            retailUserDao.save(bart);
            retailUserDao.save(karin);
            retailUserDao.save(charlotte);
            retailUserDao.save(jan);
            retailUserDao.save(boudewijn);*/

            userDao.save(johnDoe);
            testData.makeRetailUserList();                          //AMS: haalt retail data op uit CSV file
            testData.retailUserListSplitAddBankaccountAndSave();    //AMS: verwerken testdata

            //bankmedewerkers voor HoofdMKB en HoofdParticulieren
           RetailUser boudewijn = new RetailUser("Boudewijn", "1234",  987654321, "Boudewijn",
                   "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");

           retailUserDao.save(boudewijn);
           BankAccount account6 = new BankAccount("NL10COUC0523456795", 13000);

           boudewijn.addBankAccount(account6);
           bankAccountDao.save(account6);


            BankUser bankUser1 = new BankUser("piet", "pietgeheim", "Hoofd MKB");
            BankUser bankUser2 = new BankUser("marie", "mariegeheim", "Hoofd Particulieren");

            bankUserDao.save(bankUser1);
            bankUserDao.save(bankUser2);


//           SMEUser smeUser1 = new SMEUser("goldman", "1234", "Eigenaar", 10000020);
//           smeUserDao.save(smeUser1);
//
//
//
//
//
//            Company testCOmpany = new Company(10876546,"BLAH", "B.V", "ICT", "Wegstraat", 15, "1024AM", "Amsterdam", "786453627", 1245, true, "blah@gmail.com");
//           BankAccount account10 = new BankAccount("NL10COUC0523456665", 10000.00);
//
//            testCOmpany.addCompanyAccount(account10);
//           bankAccountDao.save(account10);
//            companyDao.save(testCOmpany);
//
//        smeUser1.setCompany(testCOmpany);
//        smeUserDao.save(smeUser1);



           //testdata 11 keer SMEUser, Bankaccount, BankUser, Company - door Arnout toegevoegd
           for (int index = 10; index < 21 ; index++) {
               String userNameAccountManager = "AccountMan" + index;
               String userPwAccountManager = "PwAccountMan" + index;
               BankUser accountManager1 = new BankUser(userNameAccountManager, userPwAccountManager, "Account Manager");
               bankUserDao.save(accountManager1);
               //maak een bankaccount met saldo en IBAN, save
               final int MAX_INIT_BALANCE = 10000000;
               Double balance = Math.round(Math.random() * MAX_INIT_BALANCE) / 100.0;  //bedrag met 2 decimalen
               BankAccount bankAccount1 = new BankAccount(balance);
               bankAccount1.setAccountType("Zakelijk");
               bankAccountDao.save(bankAccount1);
               //maak smeUser, smeUserList, bankAccountList, company,
               String directeurUserName = "Directeur" + index;
               SMEUser smeUser1 = new SMEUser(directeurUserName, "directeur", "CEO");
               // bsn moet unique zijn
               smeUser1.setBsn(10000000 + index);
               //2 lijsten om company te kunnen vullen
               List<SMEUser> smeUserList1 = new ArrayList<>();
               smeUserList1.add(smeUser1);
               List<BankAccount> bankAccountList1 = new ArrayList<>();
               bankAccountList1.add(bankAccount1);
               int NumberKvK = 10000000 + index;
               Company company1 = new Company(NumberKvK, "Bedrijf1", "Eenmanszaak", "Verzekeringen", "Marten Meesweg", 1, "3012AA",
                       "Rotterdam", "0104134321", 1234, FALSE, smeUserList1, bankAccountList1,
                       accountManager1, "b.verzekeraar@nn.nl");  //company moet worden gevuld met list<SMEUser>: smeUserList1
               company1.setSector("ICT");
               companyDao.save(company1);
               smeUser1.setCompany(company1);    //dit koppelt deze company aan de smeUser
               smeUserDao.save(smeUser1);
           }  //einde vd loop

        }

        System.out.println("dbinit klaar.");




    }

}
