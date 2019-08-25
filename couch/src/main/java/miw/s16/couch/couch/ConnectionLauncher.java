package miw.s16.couch.couch;


import miw.s16.couch.couch.model.dao.DBaccess;

public class ConnectionLauncher {

    private DBaccess db;
    private static final String DASHED_LINE = "------------------------";

    public ConnectionLauncher() {
        super();
        db = new DBaccess();

    }

    public void run() {

        try {
            db.openConnection();
            System.out.println("Connection open");
            System.out.println(DASHED_LINE);
        } catch (Exception e) {
            System.out.println("\nEr is iets fout gegaan\n");
            e.printStackTrace();
        }

        db.closeConnection();
    }
}
