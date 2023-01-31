import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[size];

        numbers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int input = scanner.nextInt();

        int sum = Arrays.stream(numbers)
                .filter(number -> number > input)
                .sum();

        System.out.println(sum);
        // put your code here
    }
}