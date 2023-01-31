package carsharing;

public class Company extends TableUnit {
    public Company(int id, String name) {
        super(id, name);
    }

    public static String getCreateTableSQL() {
        return "CREATE TABLE IF NOT EXISTS COMPANY " +
                "(ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(40) NOT NULL UNIQUE)";
    }

    public static String getAddSQL(String name) {
        return String.format("INSERT INTO COMPANY (NAME)" +
                " VALUES ('%s');", name);
    }
}
