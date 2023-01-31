package carsharing;


import carsharing.db.CarSharingDB;

public class Main {

    public static void main(String[] args) {
        String fileName = "carsharing";
        if (args.length >= 2 && args[0].equals("-databaseFileName")) fileName = args[1];

        CarSharingDB db = new CarSharingDB(fileName);
        db.createTables();

        UI ui = new UI(db);
        ui.start();
    }
}