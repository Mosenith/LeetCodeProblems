public class MaximumNumberOfPointsWithCost {
    public static void main(String[] args) {

    }

    // ***************** 1st Method ******************
    // Approach 1: Loop through the points array and store the max value in a new array
    // Loop through the points array and store the max value in a new array
    // Runtime  : 67ms     -> + 5.06%
    // Memory   : 65.38MB  -> + 16.46%
    public long maxPoints(int[][] points) {
        int n = points[0].length;
        long[] f = new long[n];
        final long inf = 1L << 60;
        for (int[] p : points) {
            long[] g = new long[n];
            long lmx = -inf, rmx = -inf;
            for (int j = 0; j < n; ++j) {
                lmx = Math.max(lmx, f[j] + j);
                g[j] = Math.max(g[j], p[j] + lmx - j);
            }
            for (int j = n - 1; j >= 0; --j) {
                rmx = Math.max(rmx, f[j] - j);
                g[j] = Math.max(g[j], p[j] + rmx + j);
            }
            f = g;
        }
        long ans = 0;
        for (long x : f) {
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
