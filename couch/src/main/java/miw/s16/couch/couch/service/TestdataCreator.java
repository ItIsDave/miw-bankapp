package miw.s16.couch.couch.service;

import miw.s16.couch.couch.model.BankAccount;
import miw.s16.couch.couch.model.RetailUser;
import miw.s16.couch.couch.model.dao.BankAccountDao;
import miw.s16.couch.couch.model.dao.RetailUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public abstract class TestdataCreator {

    @Autowired
    static RetailUserDao retailUserDao;

    @Autowired
    static BankAccountDao bankAccountDao;

    final static int BESTAND_GROOTTE = 816;  //is het aantal gegenereerde retailUsers in CSVbestand
    private static List<String> retailUserList;
    @NotEmpty
    private static String userName;
    @NotEmpty
    private static String userPassword;
    @NotNull
    @Min(value = 100000000)
    @Max(value = 999999999)
    private static int bsn;
    @NotEmpty
    private static String firstName;
    private static String middleName;  //dit attribuut is bedoeld voor het deel van de naam dat heet het tussenvoegsel
    @NotEmpty
    private static String lastName;
    @NotEmpty
    private static String streetName;
    @NotNull
    @Positive
    private static int houseNumber;
    private static String extension;
    @NotEmpty
    private static String zipcode;
    @NotEmpty
    private static String city;
    @NotEmpty
    @Size(min= 9, max=10)
    private static String phoneNumber;
    @NotEmpty
    //omdat de date als string wordt ingegeven kan ik geen date validatie toepasssen
    private static String dateOfBirth;
    @NotEmpty
    @Email
    private static String email;

    public static void makeRetailUserList() {
        Scanner reader;
        retailUserList = new ArrayList<>();
        try {
            File retailCSVFile = new File("resources/testdata.csv");
            reader = new Scanner(retailCSVFile);
            //variant waarbij de bestand grootte niet bekend:
            while (reader.hasNextLine()) {
                retailUserList.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException noFile) {
            System.out.println("Het csvTestBestand is niet gevonden.");
        }
    }

    public static void retailUserListSplitAndSave() {
        for (int index = 0; index < BESTAND_GROOTTE; index++) {
            String[] recordSplit = retailUserList.get(index).split(",");  //er zijn 14 kolommen
            // per attribuut van User en retailUserClient de strings toekennen
            userName = recordSplit[0];
            userPassword = recordSplit[1];
            firstName = recordSplit[2];
            middleName = recordSplit[3];
            lastName = recordSplit[4];
            streetName = recordSplit[5];
            String houseNumberString = recordSplit[6];
                houseNumber = Integer.parseInt(houseNumberString);
            extension = recordSplit[7];
            zipcode = recordSplit[8];
            city = recordSplit[9];
            phoneNumber = recordSplit[10];
            dateOfBirth = recordSplit[11];
            email = recordSplit[12];
            String bsnString = recordSplit[13];
                bsn = Integer.parseInt(bsnString);
            RetailUser retailUser = new RetailUser(userName,userPassword,bsn,firstName,middleName,lastName,streetName,houseNumber,
                    extension,zipcode,city,phoneNumber,dateOfBirth,email,"");
            retailUserDao.save(retailUser);
        }
    }

    public static void makeBankAccountsTestData() {
        //maak testdata
        for (int index = 0; index <BESTAND_GROOTTE-1 ; index++) {
            final int MAX_INIT_BALANCE = 100000; //honderduizend
            Double balance =  Math.round(Math.random() * MAX_INIT_BALANCE) / 100.0;  //bedrag met 2 decimalen
            BankAccount bankAccount = new BankAccount(balance);
            System.out.println("nieuw bankaccount gemaakt.");
            bankAccountDao.save(bankAccount);
        }
    }
}
