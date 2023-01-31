import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDateTime localDateTime = LocalDateTime.parse(scanner.nextLine());
        String[] input = scanner.nextLine().split(" ");
        System.out.println(
                localDateTime.minusHours(Integer.parseInt(input[0])).plusMinutes(Integer.parseInt(input[1]))
        );
    }
}