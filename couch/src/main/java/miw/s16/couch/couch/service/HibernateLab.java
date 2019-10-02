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
         // user info for checking if DB is empty
        if (userDao.findByUserPassword("1").size() == 0) {
            User johnDoe = new User("John Doe", "1");
            userDao.save(johnDoe);

            // Creating Bank account data
            BankAccount account1 = new BankAccount("NL10COUC0123456790", 80.00);
            BankAccount account2 = new BankAccount("NL10COUC0223456791", 10.00);
            BankAccount account3 = new BankAccount("NL10COUC0323456792", 50.00);
            BankAccount account4 = new BankAccount("NL10COUC0423456793", 80.00);
            BankAccount account5 = new BankAccount("NL10COUC0523456794", 100.00);
            BankAccount account6 = new BankAccount("NL10COUC0523456795", 13000);

            // Creating Retail user data
            RetailUser bart = new RetailUser("Bart", "1234", 987654321, "Bart",
                    "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");
            RetailUser charlotte = new RetailUser("Charlotte", "1234", 987654322,
                    "Charlotte", "de", "Witte", "Keizersgracht", 40,
                    "A", "1017DS", "Amsterdam", "690000001", "25-10-1999", "cdv@gmail.com", "Retail");
            RetailUser karin = new RetailUser("Karin", "1234", 987654325,
                    "Karin", "van", "Dijk", "Prinsengracht", 200,
                    "A", "1017KS", "Amsterdam", "690000801", "25-10-1990", "kvd@gmail.com", "Retail");
            RetailUser jan = new RetailUser("Jan", "1234", 987654326,
                    "Jan", "", "Bakken", "Herengracht", 100,
                    "C", "1018AC", "Amsterdam", "6901230801", "25-10-1989", "jbakker@gmail.com", "Retail");
            RetailUser boudewijn = new RetailUser("Boudewijn", "1234", 987654321, "Boudewijn",
                    "", "Simpson", "Kalverstraat", 25, "B", "1011AB", "Amsterdam", "690000000", "25-10-1900", "bart@hva.nl", "Retail");

            // confirmation that DB is running
            System.out.println("Creating schema");
            charlotte.addBankAccount(account2);
            bart.addBankAccount(account1);
            jan.addBankAccount(account5);
            boudewijn.addBankAccount(account6);

            bankAccountDao.save(account1);
            bankAccountDao.save(account2);
            bankAccountDao.save(account3);
            bankAccountDao.save(account4);
            bankAccountDao.save(account5);
            bankAccountDao.save(account6);

            // saving to the db
            retailUserDao.save(bart);
            retailUserDao.save(karin);
            retailUserDao.save(charlotte);
            retailUserDao.save(jan);
            retailUserDao.save(boudewijn);

            //  Data for Company & employees ----
            BankAccount accountTest = new BankAccount("NL10COUC0523455661", 150000);
            accountTest.setAccountType("Zakelijk");
            BankUser accountManagerTest = new BankUser("Jan Zucchini", "1234", "Account Manager");
            List<SMEUser> employees = new ArrayList<>();
            SMEUser smeUserTest = new SMEUser("FranT", "1234", "CEO",20000000 );
            SMEUser smeUserTest2 = new SMEUser("FrankP", "1234", "Medewerker", 20000001);
            SMEUser smeUserTest3 = new SMEUser("JohnA", "1234", "Admin", 20000002);
            SMEUser existingRetailUser = new SMEUser("Char", "1234", "Medewerker", 987654325);
            employees.add(smeUserTest);
            employees.add(smeUserTest2);
            employees.add(smeUserTest3);
            employees.add(existingRetailUser);

            bankAccountDao.save(accountTest);
            bankUserDao.save(accountManagerTest);
            List<BankAccount> bankAccountListTest = new ArrayList<>();
            bankAccountListTest.add(accountTest);

            Company companyTest = new Company(28763198, "Amaphon", "B.V", "ICT", "Safariweg", 100, "1012AA",
                    "Amsterdam", "6904134321", 1234, true, employees, bankAccountListTest,
                    accountManagerTest, "amaphon@nn.nl");

            companyDao.save(companyTest);
            smeUserTest.setCompany(companyTest);
            smeUserTest2.setCompany(companyTest);
            smeUserTest3.setCompany(companyTest);
            existingRetailUser.setCompany(companyTest);
            smeUserDao.save(smeUserTest);
            smeUserDao.save(smeUserTest2);
            smeUserDao.save(smeUserTest3);
            smeUserDao.save(existingRetailUser);


            //   testData.makeRetailUserList();                          //AMS: haalt retail data op uit CSV file
         //   testData.retailUserListSplitAddBankaccountAndSave();    //AMS: verwerken testdata

            //bankmedewerkers voor HoofdMKB en HoofdParticulieren

            BankUser bankUser1 = new BankUser("piet", "pietgeheim", "Hoofd MKB");
            BankUser bankUser2 = new BankUser("marie", "mariegeheim", "Hoofd Particulieren");

            bankUserDao.save(bankUser1);
            bankUserDao.save(bankUser2);


            //testdata 11 keer SMEUser, Bankaccount, BankUser, Company - door Arnout toegevoegd
            for (int index = 10; index < 21; index++) {
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
            System.out.println("dbinit klaar.");
        }
    }
}
