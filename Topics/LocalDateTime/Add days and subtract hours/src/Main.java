import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        System.out.println
                (LocalDateTime.parse(parts[0]).plusDays(Long.parseLong(parts[1])).minusHours(Long.parseLong(parts[2])));
    }
}