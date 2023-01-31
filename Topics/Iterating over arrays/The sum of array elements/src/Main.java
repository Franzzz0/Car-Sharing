import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String[] elements = scanner.nextLine().split(" ");
        int sum = 0;
        for (String e : elements) {
            sum += Integer.parseInt(e);
        }
        System.out.println(sum);
    }
}