import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PartitionTaskTest {

    @Test
    public void Test1(){
        assertTrue(PartitionTask.findSubsequence(new int[]{1, 1, 3, 4, 7}));
    }

    @Test
    public void Test2(){
        assertFalse(PartitionTask.findSubsequence(new int[]{2,3,4,6}));
    }

    @Test
    public void Test3(){
        assertTrue(PartitionTask.findSubsequence(new int[]{1,5,11,5}));
    }

    @Test
    public void Test4(){
        assertFalse(PartitionTask.findSubsequence(new int[]{1, 5, 3}));
    }

    @Test
    public void Test5(){
        assertTrue(PartitionTask.findSubsequence(new int[]{7,1,3,5,9,10,1}));
    }

    @Test
    public void Test6(){
        assertTrue(PartitionTask.findSubsequence(new int[]{6,6,2,10}));
    }

    @Test
    public void Test7(){
        assertFalse(PartitionTask.findSubsequence(new int[]{6,6,1,10}));
    }
}
