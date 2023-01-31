package carsharing;

import carsharing.db.CarSharingDB;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final CarSharingDB dataBase;
    private final Scanner scanner;

    public UI(CarSharingDB dataBase) {
        this.dataBase = dataBase;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n1. Log in as a manager\n2. Log in as a customer\n3. Create a customer\n0. Exit");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 0 -> {
                    return;
                }
                case 1 -> managerMenu();
                case 2 -> customerList();
                case 3 -> createCustomer();
                default -> System.out.println("unknown command");
            }
        }
    }

    private void managerMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. Company list\n2. Create a company\n0. Back");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1 -> companyList(0);
                case 2 -> createCompany();
                case 0 -> {
                    return;
                }
                default -> System.out.println("unknown command");
            }
        }
    }

    private void companyMenu(TableUnit company) {
        while (true) {
            System.out.println();
            System.out.println(company.getName() + " company:");
            System.out.println("1. Car list\n2. Create a car\n0. Back");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1 -> carList(company.getId());
                case 2 -> createCar(company.getId());
                case 0 -> {
                    return;
                }
                default -> System.out.println("unknown command");
            }
        }
    }

    private void customerMenu(TableUnit customer) {
        while (true) {
            System.out.println();
            System.out.println("1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 0 -> {
                    return;
                }
                case 1 -> rentCar(customer.getId());
                case 2 -> returnCar(customer.getId());
                case 3 -> printRentedCar(customer.getId());
                default -> System.out.println("unknown command");
            }
        }
    }

    private void rentCar(int id) {
        System.out.println();
        if (this.dataBase.hasRentedCar(id)) {
            System.out.println("You've already rented a car!");
            return;
        }
        companyList(id);
    }

    private void printRentedCar(int id) {
        System.out.println();
        if (this.dataBase.hasRentedCar(id)) {
            dataBase.getRentedCar(id);
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    private void returnCar(int id) {
        System.out.println();
        if (this.dataBase.hasRentedCar(id)) {
            this.dataBase.returnCar(id);
            System.out.println("You've returned a rented car!");
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    private void chooseCar(int customerID, int companyID) {
        List<TableUnit> carList = dataBase.getCarList(companyID);
        TableUnit company = dataBase.getCompanyList().get(companyID - 1);
        System.out.println();
        carList.removeIf(car -> dataBase.isRented(car.getId()));
        if (carList.isEmpty()) {
            System.out.printf("No available cars in the %s company.%n", company.getName());
            return;
        }
        System.out.println("Choose a car:");
        for (int i = 1; i <= carList.size(); i++) {
            System.out.printf("%d. %s%n", i, carList.get(i - 1).getName());
        }
        System.out.println("0. Back");
        while (true) {
            int input;
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number.");
                continue;
            }
            if (input == 0) {
                break;
            }
            if (input < 0 || input > carList.size()) {
                System.out.println("Wrong number!");
                continue;
            }
            dataBase.rentCar(customerID, carList.get(input - 1).getId());
            System.out.printf("You rented '%s'%n", carList.get(input - 1).getName());
            break;
        }
    }

    private void createCustomer() {
        System.out.println();
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        dataBase.add(Customer.getAddSQL(name));
        System.out.println("The customer was added!");
    }

    private void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        dataBase.add(Company.getAddSQL(name));
        System.out.println("The company was created!");
    }

    private void createCar(int companyID) {
        System.out.println();
        System.out.println("Enter the car name:");
        String name = scanner.nextLine();
        dataBase.add(Car.getAddSQL(name, companyID));
        System.out.println("The car was added!");
    }

    private void companyList(int customerID) {    // 0 for company menu
        List<TableUnit> companyList = dataBase.getCompanyList();
        System.out.println();
        if (companyList.isEmpty()) {
            System.out.println("The company list is empty!");
            return;
        }
        System.out.println("Choose a company:");
        for (TableUnit company : companyList) {
            System.out.println(company);
        }
        System.out.println("0. Back");
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number.");
                continue;
            }
            if (input == 0) {
                break;
            }
            if ((input - 1) < 0 || input > companyList.size()) {
                System.out.println("Wrong number!");
                continue;
            }
            if (customerID == 0) {
                companyMenu(companyList.get(input - 1));
            } else {
                chooseCar(customerID, input);
            }
            break;
        }
    }

    private void carList(int id) {
        List<TableUnit> carList = dataBase.getCarList(id);
        System.out.println();
        if (carList.isEmpty()) {
            System.out.println("The car list is empty!");
            return;
        }
        System.out.println("Car list:");
        for (int i = 1; i <= carList.size(); i++) {
            System.out.printf("%d. %s%n", i, carList.get(i - 1).getName());
        }
    }

    private void customerList() {
        List<TableUnit> customerList = dataBase.getCustomerList();
        System.out.println();
        if (customerList.isEmpty()) {
            System.out.println("The customer list is empty!");
            return;
        }
        System.out.println("Customer list:");
        for (TableUnit customer : customerList) {
            System.out.println(customer);
        }
        System.out.println("0. Back");
        while (true) {
            int input;
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("You should enter a number.");
                continue;
            }
            if (input == 0) {
                break;
            }
            if ((input - 1) < 0 || input > customerList.size()) {
                System.out.println("Wrong number!");
                continue;
            }
            customerMenu(customerList.get(input - 1));
            break;
        }
    }
}
