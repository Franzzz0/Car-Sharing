import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(LocalDate.parse(scanner.nextLine()).plusDays(14));
        // put your code here
    }
}