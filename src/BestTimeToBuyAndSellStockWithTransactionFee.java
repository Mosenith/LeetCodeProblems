public class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;

        BestTimeToBuyAndSellStockWithTransactionFee bt = new BestTimeToBuyAndSellStockWithTransactionFee();
        System.out.println(bt.maxProfit(prices,fee));
    }

    // ***************** 1st Method ******************
    // Approach 1: Using DP bottom up
    // Runtime  : 22ms        -> + 34.10%
    // Memory   : 55.54MB     -> + 37.13%
    int[] prices;
    int fee;
    public int maxProfit(int[] prices, int fee) {
        this.prices = prices;
        this.fee = fee;

        int[][] dp = new int[prices.length + 1][2];

        for (int i = prices.length - 1; i >= 0; i--) {
            dp[i][1] = Math.max(dp[i+1][1], dp[i+1][0] + prices[i] - fee);
            dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1] - prices[i]);
        }

        return dp[0][0];
    }

    private int dp(int pos, boolean buy) {
        if(pos == prices.length)
            return 0;

        int maxVal = 0;

        if(!buy) {
            // when buy stock
            maxVal = Math.max(maxVal, dp(pos+1,true) - prices[pos] - fee);
        } else {
            // when sell stock
            maxVal = Math.max(maxVal, dp(pos+1,false) + prices[pos]);
        }

        // do nothing
        maxVal = Math.max(maxVal, dp(pos+1,buy));

        return maxVal;
    }
    //  ***************** End of 1st Method ******************

    // ***************** 2nd Method ******************
    // Approach 2: Get max profit by find max profit after buy & sell
    // When buy, it is max(buy,sell-price)
    // When sell, it is max(sell, buy+price-fee)
    // Runtime  : 4ms        -> + 99.03%
    // Memory   : 55.16MB    -> + 55.11%
    public int maxProfit2(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;

        for (int price : prices) {
            buy = Math.max(buy, sell - price);//  max profit after buy
            sell = Math.max(sell, buy + price - fee);// max profit after selling
        }

        return sell;
    }
    //  ***************** End of 2nd Method ******************
}
