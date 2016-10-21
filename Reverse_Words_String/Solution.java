package Reverse_Words_String;

/**
 * Created by henrywan16 on 10/21/16.
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 */
public class Solution {

    public String reverseWords(String s) {
        String result = "";
        String[] temp = s.trim().split("\\s+");
        int n = temp.length;
        int i = 0, j = (n - 1);
        while (i < j) {
            String str = temp[i];
            temp[i] = temp[j];
            temp[j] = str;
            i++;
            j--;
        }
        for (i = 0; i < n; i++)
        {
            if (i == (n - 1)) {
                result += temp[i];
            }
            else {
                result += temp[i] + " ";
            }
        }
        System.out.println(result);
        return result;
    }
}
