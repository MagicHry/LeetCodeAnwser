package com.reillyhe.leetcodeanwser.maxprofit

object StockMaxProfit {

    /**
     * 核心思路：DP
     * 我们设置 X -》 第X天
     * 那么有 F(X) -》第X天sold股票能够获取到的最大利润
     * 那么有 F(X) = Value(X) - P(X-1)
     * Value(X) -》第X天的股票价格
     * P(X-1) -》从第一天到第X-1天的股票最低价格
     * X -》从1到N，找到最大值即可
     */
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        //dp初始化
        val patternLst = ArrayList<Int>(prices.size)
        for (x in 1..prices.size) {
            //初始化
            if (patternLst.isEmpty()) {
                patternLst.add(prices[x-1])
                continue
            }
            //计算F(X)
            val pValue = patternLst[x-2]
            val profitForX = prices[x-1] - pValue
            maxProfit = Math.max(profitForX, maxProfit)
            //更新patternLst
            val curP = Math.min(pValue, prices[x-1])
            patternLst.add(curP)
        }
        return maxProfit
    }
}