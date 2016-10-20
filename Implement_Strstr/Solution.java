package Implement_Strstr;

/**
 * Created by henrywan16 on 10/19/16.
 */
public class Solution {
    public int strStr(String haystack, String needle) {
        int i, j;
        for (i = 0; ; i++) {
            for (j = 0; ; j++) {
                // matched and return the position of the first letter.
                if (j == needle.length()) {
                    return i;
                }

                // go to the end of the haystack
                else if ((i + j) == haystack.length()) {
                    return -1;
                }

                // don't match end;
                else if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }

                else {
                    ;
                }
            }
        }
        //return -1;
    }
}
