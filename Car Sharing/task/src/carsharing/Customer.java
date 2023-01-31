package carsharing;

public class Customer extends TableUnit {

    public Customer(int id, String name, int carID) {
        super(id, name, carID);
    }

    public static String getCreateTableSQL() {
        return "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                "(ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(40) NOT NULL UNIQUE," +
                "RENTED_CAR_ID INTEGER," +
                "CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID)" +
                " REFERENCES CAR(ID));";
    }

    public static String getAddSQL(String name) {
        return String.format("INSERT INTO CUSTOMER (NAME)" +
                " VALUES ('%s');", name);
    }
}
