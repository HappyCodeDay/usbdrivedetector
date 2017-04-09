package Contests.LeetCode.Leet557_Reverse_Words_in_a_String_III;
//https://leetcode.com/contest/leetcode-weekly-contest-27/problems/reverse-words-in-a-string-iii/

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by raliclo on 07/04/2017.
 */
public class Leet557_Solved {
    public static void main(String[] args) throws IOException {

        String input = "Let's take LeetCode contest";

        System.out.println(reverseWord("abcde"));
        System.out.println(reverseWords(input));

    }

    public static String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        String[] data = s.split(" ");
        Arrays.stream(data).forEach(
                (word) -> {
                    ans.append(reverseWord(word));
                    ans.append(" ");
                }
        );
        ans.deleteCharAt(ans.length());
        return ans.toString();
    }

    public static String reverseWord(String s) {
        StringBuilder ans = new StringBuilder();
        int i = s.length();
        while (i-- > 0) {
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}
