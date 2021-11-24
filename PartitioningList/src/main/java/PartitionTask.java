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

    //Big(O): n*3 * log(n)
    public static boolean findSubsequence(int[] arr) {
        if (arr.length == 2 && arr[0] != arr[1]) {
            //If the elements are two, and they are different return false
            return false;
        } else if (arr.length == 2) {
            //If the elements are two and they are equal return true
            return true;
        }

        //Find the totalSum of the elements
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }

        //If the sum is not an even number than they cannot be split
        if (sum % 2 == 1) {
            return false;
        }

        //The halfSum should be the sum of subsets
        int halfSum = sum % 2;

        //Sort the array so we can subtract from the halfSum
        Arrays.sort(arr);


        //If the halfSum reaches 0 then we have two subsets with equal sums and return 0
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
