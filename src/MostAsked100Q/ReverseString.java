package MostAsked100Q;

import java.util.Arrays;

public class ReverseString {
    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};

        reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    // ***************** 1st Method ******************
    // Approach 1: Use left & right indices to swap
    // Runtime  : 1ms        -> + 84.63%
    // Memory   : 50.7MB      -> + 65.28%
    public static void reverseString(char[] s) {
        int left = 0, right = s.length-1;
        while(left < right) {
            if(s[left] != s[right]) {
                char temp = s[left];
                s[left] = s[right];
                s[right] = temp;
            }
            left++;
            right--;
        }
    }

    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Use for-loop and index i&j to track element & swap
    // Condition - i < j
    // Runtime  : 0ms          -> + 100.00%
    // Memory   : 49.13MB      -> + 80.98%
    public static void reverseString2(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; ++i, --j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
    //  ***************** End of 2nd Method ******************
}
