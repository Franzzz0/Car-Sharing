package carsharing.db;

import carsharing.*;

import java.util.ArrayList;
import java.util.List;

public class CarSharingDB {
    private final H2jdbc database;
    private List<TableUnit> companyList;
    private List<TableUnit> carList;
    private List<TableUnit> customerList;

    public CarSharingDB(String filename) {
        this.database = new H2jdbc(filename);
        this.companyList = new ArrayList<>();
        this.carList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public void createTables() {
        this.database.executeTask(Company.getCreateTableSQL());
        this.database.executeTask(Car.getCreateTableSQL());
        this.database.executeTask(Customer.getCreateTableSQL());
        this.updateDatabase();
    }

    public void add(String sql) {
        this.database.executeTask(sql);
        this.updateDatabase();
    }

    public List<TableUnit> getCompanyList() {
        return companyList;
    }

    public List<TableUnit> getCarList(int companyID) {
        return getUnitsList(String.format("CAR WHERE COMPANY_ID = %d", companyID));
    }

    public List<TableUnit> getCustomerList() {
        return customerList;
    }

    public boolean hasRentedCar(int id) {
        return this.customerList.get(id - 1).getReferenceID() != 0;
    }

    public boolean isRented(int carID) {
        for (TableUnit customer : this.customerList) {
            if (customer.getReferenceID() == carID) {
                return true;
            }
        }
        return false;
    }

    public void getRentedCar(int id) {
        int rentedCarId = this.customerList.get(id - 1).getReferenceID();
        TableUnit car = this.carList.get(rentedCarId - 1);
        System.out.println("Your rented car:");
        System.out.println(car.getName());
        System.out.println("Company:");
        System.out.println(this.companyList.get(car.getReferenceID() - 1).getName());
    }

    public void rentCar(int customerID, int carID) {
        this.database.executeTask(String.format("UPDATE CUSTOMER " +
                "SET RENTED_CAR_ID = %d " +
                "WHERE ID = %d;", carID, customerID));
        this.updateDatabase();
    }

    public void returnCar(int id) {
        this.database.executeTask(String.format("UPDATE CUSTOMER " +
                "SET RENTED_CAR_ID = NULL " +
                "WHERE ID = %d;", id));
        this.updateDatabase();
    }

    public void dropTable(String name) {
        this.database.executeTask(String.format("DROP TABLE IF EXISTS %s", name));
    }

    private void updateDatabase() {
        this.companyList = getUnitsList("COMPANY");
        this.carList = getUnitsList("CAR");
        this.customerList = getUnitsList("CUSTOMER");
    }

    private List<TableUnit> getUnitsList(String sql) {
        return this.database.getList(sql);
    }
}
