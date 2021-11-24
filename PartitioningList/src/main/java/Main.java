import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int[] ints = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            boolean isSubsequence = PartitionTask.findSubsequence(ints);
            System.out.println(isSubsequence);
        }
    }
}
