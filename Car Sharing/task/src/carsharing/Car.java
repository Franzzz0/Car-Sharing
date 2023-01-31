package carsharing;

public class Car extends TableUnit {

    public Car(int id, String name, int companyID) {
        super(id, name, companyID);
    }

    public static String getCreateTableSQL() {
        return "CREATE TABLE IF NOT EXISTS CAR " +
                "(ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(40) NOT NULL UNIQUE," +
                "COMPANY_ID INTEGER NOT NULL," +
                "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID)" +
                " REFERENCES COMPANY(ID));";
    }

    public static String getAddSQL(String name, int companyID) {
        return String.format("INSERT INTO CAR (NAME, COMPANY_ID)" +
                " VALUES ('%s', %d);", name, companyID);
    }
}
