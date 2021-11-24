public class TargetFinderProblem {

    public static int[] findIndexes(int[] numbers, int target){
        int[] output = new int[2];

        boolean outputFound = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i == j) {
                    continue;
                }

                int firstNum = numbers[i];
                int secondNum = numbers[j];

                if (firstNum + secondNum == target) {
                    output[0] = i;
                    output[1] = j;
                    outputFound = true;
                    break;
                }
            }

            if (outputFound) {
                break;
            }
        }

        return output;
    }
}
