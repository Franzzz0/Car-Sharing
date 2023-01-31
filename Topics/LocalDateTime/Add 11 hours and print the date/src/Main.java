import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDateTime.parse(scanner.nextLine()).plusHours(11).toLocalDate();
        System.out.println(date);
    }
}