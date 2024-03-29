import java.sql.SQLOutput;
import java.util.*;

public class TaskScheduler {
    public static void main(String[] args) {
//        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'}; // n = 2 => 8
        char[] tasks = {'A', 'C', 'A', 'B', 'D', 'B'};  // n = 1 => 6
//        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};  // n = 3 => 10

        System.out.println(leastInterval(tasks,1));
    }

    // ***************** 1st Method ******************
    // Approach 1: Count frequency of each char in tasks[] and sort the array alpha[]
    // Get the maxFreq & cal idleTime=(maxFreq - 1) * n. Then add other tasks between maxFreq
    // Update idleTime=min(maxFreq-1,alpha[i]), have idleTime=max(0,idleTime)
    // Return idleTime+tasks.len
    // Runtime  : 2ms         -> + 99.14%
    // Memory   : 45.15MB     -> + 93.26%
    public static int leastInterval(char[] tasks, int n) {
        int[] alpha = new int[26];

        // count frequencies of each char in task
        for(char c : tasks) {
            alpha[c - 'A']++;
        }

        // sort the frequency array
        Arrays.sort(alpha);

        // get max frequency
        int maxFreq = alpha[25];

        // Calculate idle intervals
        int idleTime = (maxFreq - 1) * n;

        // Assign other task between maxFreq
        for (int i = 24; i>=0 && alpha[i]>0; i--) {
            idleTime -= Math.min(maxFreq-1, alpha[i]);
        }

        // Calculate total intervals required
        idleTime = Math.max(0, idleTime); // Ensure idle time is non-negative
        return idleTime + tasks.length; // Total intervals required
    }
    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Similar approach to the 1st
    // First find freq(cnt[]) of tasks & maxFreq
    // Find #maxFreq of char in cnt[] & find interval by max(tasks.length, (maxFreq-1)*(n+1)+s)
    // Runtime  : 3ms         -> + 78.07%
    // Memory   : 45.61MB     -> + 58.12%
    public static int leastInterval2(char[] tasks, int n) {
        int[] cnt = new int[26];
        int maxFreq = 0;
        // track frequency while also getting maxFreq
        for (char c : tasks) {
            c -= 'A';
            ++cnt[c];
            maxFreq = Math.max(maxFreq, cnt[c]);
        }
        int s = 0;
        // find number of tasks that has maxFreq
        for (int v : cnt) {
            if (v == maxFreq) {
                ++s;
            }
        }

        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + s);
    }
    //  ***************** End of 2nd Method ******************
}
