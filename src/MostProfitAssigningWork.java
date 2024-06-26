import java.util.*;

public class MostProfitAssigningWork {
    public static void main(String[] args) {
        int[] difficulty = {7,20,68};
        int[] profit = {26,28,57};
        int[] worker = {71,20,71};

        System.out.println(maxProfitAssignment2(difficulty,profit,worker));
    }

    // ***************** 1st Method ******************
    // Approach 1: Use difWorker to store difficulty and worker, then iterate through profit
    // If worker is greater than or equal to difficulty, add profit to maxProfit
    // Return maxProfit
    // Work but exceed time limit
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Map<Integer, List<Integer>> difWorker = new HashMap<>();

        for(int i=0; i<difficulty.length; i++) {
            int level = difficulty[i];
            difWorker.put(i, new ArrayList<>());
            for(int j=0; j<worker.length; j++) {
                if(worker[j] >= level) {
                    difWorker.get(i).add(j);
                }
            }
        }
        Map<Integer,Integer> profitMap = new HashMap<>();
        for(int i=0; i<profit.length; i++) {
            List<Integer> tmp = difWorker.get(i);
            if(tmp.isEmpty()) continue;
            for(int n : tmp) {
                profitMap.put(n, Math.max(profitMap.getOrDefault(n,0),profit[i]));
            }
        }

        int maxProfit = 0;
        for(int p : profitMap.values()) {
            maxProfit += p;
        }
        return maxProfit;
    }
    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Use mapDiff to store difficulty and mapProf to store profit
    // Use mapProf to store profit and its location, while also check if the difficulty is less than the previous difficulty
    // In the case, there are duplicate profit, only store the one with the highest difficulty
    // Sort difficulty, profit, and profit, then iterate from the end
    // If difficulty is less than worker, add profit to maxProfit
    // Else, move to the next profit
    // Runtime  : 42ms       -> + 27.76%
    // Memory   : 48.54MB   -> + 5.40%
    public static int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        Map<Integer,Integer> mapDiff = new HashMap<>();
        Map<Integer,Integer> mapProf = new HashMap<>();
        int maxProfit = 0;
        for(int i=0; i<difficulty.length; i++) {
            mapDiff.put(i,difficulty[i]);
            if(!mapProf.isEmpty() && mapProf.containsKey(profit[i])) {
                int prevLoc = mapProf.get(profit[i]);
                if(mapDiff.get(prevLoc) > difficulty[i]) {
                    mapProf.put(profit[i], i);
                }
            } else {
                mapProf.put(profit[i], i);
            }

        }
        Arrays.sort(difficulty);
        Arrays.sort(profit);
        Arrays.sort(worker);
        int w = worker.length-1;
        for(int i=profit.length-1; i>=0 && w>=0; ) {

            int loc = mapProf.get(profit[i]);
            if(mapDiff.get(loc) > worker[w]) {
                i--;
            } else {
                maxProfit += profit[i];
                w--;
            }
        }
        return maxProfit;
    }
    //  ***************** End of 2nd Method ******************

    // ***************** 3rd Method ******************
    // Approach 3: Calculate the maxDifficulty
    // Create bestProfit array to store the best profit for each difficulty, bestProfit has the length of maxDifficulty + 1
    // Iterate through bestProfit and get maxProfit, then store it in bestProfit
    // Iterate through worker, if worker is greater than maxDifficulty, add bestProfit[maxDifficulty] to result
    // Else, add bestProfit[worker] to result
    // Runtime  : 5ms       -> + 99.02%
    // Memory   : 45.19MB   -> + 93.73%
    public static int maxProfitAssignment3(int[] difficulty, int[] profit, int[] worker) {
        if (difficulty.length != profit.length) {return 0;}

        int maxDifficulty = 0;
        for (int diff: difficulty) {
            maxDifficulty = Math.max(maxDifficulty, diff);
        }

        int[] bestProfit = new int[maxDifficulty + 1];
        for (int i = 0; i < profit.length; i++) {
            bestProfit[difficulty[i]] = Math.max(bestProfit[difficulty[i]], profit[i]);
        }

        int maxProfit = 0;
        for (int i = 0; i < bestProfit.length; i++) {
            if (bestProfit[i] > maxProfit) {
                maxProfit = bestProfit[i];
            }
            bestProfit[i] = maxProfit;
        }

        int result = 0;
        for (int w: worker) {
            if (w > maxDifficulty) {
                result += bestProfit[maxDifficulty];
            } else {
                result += bestProfit[w];
            }
        }
        return result;
    }
    //  ***************** End of 3rd Method ******************
}

// 57 + 57 = 114 + 28 = 142