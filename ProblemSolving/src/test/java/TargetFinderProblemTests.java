import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TargetFinderProblemTests {

    @Test
    public void TestCase1() {
        var numbers = new int[] {-2, 3, 7, 4, 8};
        int target = 6;
        System.out.println("Given; {-2, 3, 7, 4, 8} and returned value should be [3, 0]");
        int[] result = TargetFinderProblem.findIndexes(numbers, target);
        System.out.println(result[0] + " and " + result[1] );
        assertTrue(result[0] == 0 || result[0] == 4 );
        assertTrue(result[1] == 4 || result[1] == 0 );
    }
}
