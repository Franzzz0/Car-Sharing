import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String regex = "([0-1]?\\d?\\d|2[0-5][0-5])\\.([0-1]?\\d?\\d|2[0-5][0-5])" +
                "\\.([0-1]?\\d?\\d|2[0-5][0-5])\\.([0-1]?\\d?\\d|2[0-5][0-5])";
        String input = scanner.nextLine();
        String output = input.matches(regex) ? "YES" : "NO";
        System.out.println(output);
    }
}