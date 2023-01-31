import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern pattern = Pattern.compile("password[ :]*([a-zA-Z0-9]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        int counter = 0;
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            counter++;
        }
        if (counter == 0) {
            System.out.println("No passwords found.");
        }
    }
}