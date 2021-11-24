import java.util.Arrays;


/*
Create a project (as in Lab 1) and write a function with tests that determines whether a given set
can be partitioned into two subsets that the sum of elements in both subsets are the same.

{1, 1, 3, 4, 7} = ?
{2, 3, 4, 6}  = ?

What is the time complexity of your solution?
Is there any better solution?

Examples:
arr[] = {1, 5, 11, 5} => Output: true as {1, 5, 5} and {11}
arr[] = {1, 5, 3} => Output: false
*/
public class PartitionTask {

    public static boolean findSubsequence(int[] arr) {
        //If the elements are two, and they are different return false
        if (arr.length == 2 && arr[0] != arr[1]) {
            return false;
        } else if (arr.length == 2) {
            return true;
        }

        //The halfSum should be the sum of subsets
        int halfSum = 0;
        for (int j : arr) {
            halfSum += j;
        }

        if (halfSum % 2 == 1) {
            return false;
        }

        Arrays.sort(arr);

        halfSum /= 2;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (halfSum - arr[i] >= 0){
                halfSum -= arr[i];
            }


            if (halfSum == 0 ){
                return true;
            }
        }

        return false;
    }
}
