package MostAsked100Q;

import com.sun.source.doctree.SeeTree;

import java.lang.reflect.Array;
import java.util.*;

public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
//        String s = "leetcode"; // 0
        String s = "loveleetcode"; // 2

        System.out.println(firstUniqChar(s));
        System.out.println(firstUniqChar3(s));

    }

    // ***************** 1st Method ******************
    // Approach : Use Hashtable - store each char to table
    // if dups, increase value. No dup => 0
    // Check table in new loop to find key that has value 0 => return its index
    // Runtime  : 35ms        -> + 46.60%
    // Memory   : 43.96 MB    -> + 89.78%
    public static int firstUniqChar(String s) {
        Hashtable<Character, Integer> store = new Hashtable<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            store.computeIfPresent(c, (k,v) -> v+1);
            store.computeIfAbsent(c, v -> 0);
        }

        if(!store.containsValue(0)) return -1;

        int j=0;
        for(; j<s.length(); j++) {
            char c = s.charAt(j);
            if(store.get(c) == 0) {
//                ans = j;
                break;
            }
        }
        return j;
    }
    // ***************** End of 1st Method ******************

    // ***************** 2nd Method : Best Method ******************
    // Approach : array of integer len=26
    // 1st for-loop: increase ++arr[s.charAt(i)-'a']
    // 2nd for-loop: from 0-s.len, if cnt[s.charAt(i) - 'a'] == 1 => i
    // otherwise out of loop, return -1
    // Runtime  : 5ms           -> + 91.83%
    // Memory   : 44.15 MB      -> + 64.95%
    public static int firstUniqChar2(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < n; ++i) {
            if (cnt[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
    // ***************** End of 2nd Method ******************

    // ***************** 3rd Method ******************
    // Approach 3: Similar to 2nd method but use queue to store & scan each char
    // So that we don't need to scan from 0 to 25
    // Runtime  : 15ms        -> + 62.38%
    // Memory   : 44.77 MB    -> + 53.31%
    public static int firstUniqChar3(String s) {
        int[] occurrence = new int[26];
        Queue<Character> queue = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            ++occurrence[c - 'a'];
            queue.add(c);
        }

        // pop from queue and scan in occurrence[]
        int index = 0;
        while(!queue.isEmpty()) {
            char c = queue.poll();
            if(occurrence[c - 'a'] == 1) {
                return index;
            }
            index++;
        }

        return -1;
    }
    // ***************** End of 3rd Method ******************
}
