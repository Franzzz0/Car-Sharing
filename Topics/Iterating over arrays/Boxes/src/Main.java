import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] box1 = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();
        int[] box2 = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).sorted().toArray();

        if (canFitIn(box1, box2)) {
            System.out.println("Box 1 < Box 2");
        } else if (canFitIn(box2, box1)) {
            System.out.println("Box 1 > Box 2");
        } else {
            System.out.println("Incompatible");
        }

        // write your code here
    }

    public static boolean canFitIn(int[] box, int[] box2) {
        for (int i = 0; i < box.length; i++) {
            if (box[i] >= box2[i]) {
                return false;
            }
        }
        return true;
    }
}