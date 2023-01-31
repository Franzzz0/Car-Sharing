import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");
        Arrays.stream(parts).mapToInt(Integer::parseInt).forEach(n -> System.out.print((n - 1) + " "));
    }
}