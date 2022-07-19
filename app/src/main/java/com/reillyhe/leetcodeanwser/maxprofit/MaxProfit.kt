package com.reillyhe.leetcodeanwser.maxprofit

object MaxProfit {

    /**
     * 没有太好的方法
     * 核心思路：
     *  - 先按照价格进行排序
     *  - 然后针对排序的价格进行查找，一旦符合条件（买入日期 < 卖出日期）
     *  - 关于上面那点，对于一次迭代，需要进行三次判断，即两个点的当前值，和次一级的值进行相互判断
     */
    fun maxProfit(prices: IntArray): Int {
        //首先进行排序
        val stockLst = ArrayList<StockPrice>(prices.size)
        prices.forEachIndexed { index, price ->
            val stock = StockPrice(price, index)
            stockLst.add(stock)
        }
        stockLst.sortBy {
            it.price
        }
        var max = 0
        //定当前价格最低，尝试需要价格最高的时候
        for (buyIn in stockLst) {
            //buyIn只能寻找buyIn时间点之后的了
            for (soldOutIndex in (buyIn.date+1).until(stockLst.size)) {
                val soldOutPrice = prices[soldOutIndex]
                val profit = soldOutPrice - buyIn.price
                max = Math.max(max, profit)
            }
        }
        return max
    }

    class StockPrice(val price: Int, val date: Int) {
        operator fun minus(other: StockPrice): Int {
            return if (date > other.date) {
                price - other.price
            } else {
                Int.MIN_VALUE
            }
        }
    }
}