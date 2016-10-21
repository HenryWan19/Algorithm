package Reverse_Words_String;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by henrywan16 on 10/21/16.
 */
public class TestSolution {
    @Test
    public void reverseWords() throws Exception {
        Solution solution = new Solution();
        String str = " a   b ";
        String result = solution.reverseWords(str);
        assertEquals(true, result.equals("b a"));
    }

}