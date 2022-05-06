package com.reillyhe.leetcodeanwser.island

import java.lang.StringBuilder
import java.util.*
import kotlin.math.max

object NumOfIsland {

    fun numIslands(grid: Array<CharArray>): Int {
        //常规容错
        if (grid.isEmpty()) return 0
        val rowCnt = grid.size
        val columnCnt = grid[0].size
        if (columnCnt == 0) return 0
        //核心思路是从左上角开始进行DFS，把每个土地/海洋看成一个NODE，一个NODE有上下左右4个NODE（会出现重复访问的case）
        //我们在访问NODE的时候，需要进行过滤筛查，保证不会重复访问NODE
        //DFS初始化
        val strBuilder = StringBuilder()
        val dfsStack = LinkedList<String>()
        var dfsIterCnt = 0
        for (row in 0.until(rowCnt)) {
            for (column in 0.until(columnCnt)) {
                val curLand = grid[row][column]
                if (curLand == '1') {
                    //找到还未访问的土地块，则要出发DFS
                    dfsIterCnt++
                    searchForIsland(
                        stack = dfsStack,
                        startPosRow = row,
                        startPosCol = column,
                        stringBuilder = strBuilder,
                        grid = grid,
                        rowCnt = rowCnt,
                        colCnt = columnCnt)
//                    println("iterCnt=$dfsIterCnt ------ now grid looks like")
//                    for (row in grid) {
//                        var str = ""
//                        for (land in row) {
//                            str = str + land + " "
//                        }
//                        println(str)
//                    }
                }
            }
        }
        return dfsIterCnt
    }

    /**
     * 触发一次DFS,完全搜索到某个完整的岛屿链
     */
    private fun searchForIsland(stack: LinkedList<String>,
                                startPosRow: Int,
                                startPosCol: Int,
                                stringBuilder: StringBuilder,
                                grid: Array<CharArray>,
                                rowCnt: Int,
                                colCnt: Int) {
        val startPos = stringBuilder.clear().append(startPosRow).append("-").append(startPosCol).toString()
        stack.clear()
        stack.push(startPos)
        while (stack.isNotEmpty()) {
            val curPos = stack.pop()
            val curRow = curPos.split('-')[0].toInt()
            val curCol = curPos.split('-')[1].toInt()
            //当前已经被探索过的土地，我们要进行标记，避免被重复探索
            grid[curRow][curCol] = 'X'
            //上下左右
            if ((curRow - 1) >= 0) {
                val topValue = grid[curRow-1][curCol]
                if (topValue == '1') {
                    //如果也是未探查土地，则加入stack，等下继续探索
                    stack.push(stringBuilder.clear().append(curRow-1).append("-").append(curCol).toString())
                }
            }
            if ((curRow + 1) < rowCnt) {
                val btmValue = grid[curRow+1][curCol]
                if (btmValue == '1') {
                    //如果也是未探查土地，则加入stack，等下继续探索
                    stack.push(stringBuilder.clear().append(curRow+1).append("-").append(curCol).toString())
                }
            }
            if ((curCol - 1) >= 0) {
                val leftValue = grid[curRow][curCol-1]
                if (leftValue == '1') {
                    //如果也是未探查土地，则加入stack，等下继续探索
                    stack.push(stringBuilder.clear().append(curRow).append("-").append(curCol-1).toString())
                }
            }
            if ((curCol + 1) < colCnt) {
                val rightValue = grid[curRow][curCol+1]
                if (rightValue == '1') {
                    //如果也是未探查土地，则加入stack，等下继续探索
                    stack.push(stringBuilder.clear().append(curRow).append("-").append(curCol+1).toString())
                }
            }
        }
    }
}