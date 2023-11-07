package com.reillyhe.leetcodeanwser.island

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * 这个解法的核心还是BFS
 * 但是会进行完整的遍历
 * 不够快速
 * 因此虽然能够AC
 * 但是执行时间上会大打折扣
 */
object NumOfIslandTimeConsumingSolu {
    fun numIslands(grid: Array<CharArray>): Int {
        //核心思路是一次循环，针对每个land（点），找到上下左右的相邻土地
        if (grid.isEmpty()) return 0
        val rowCnt = grid.size
        val columnCnt = grid[0].size
        if (columnCnt == 0) return 0
        val strBuilder = StringBuilder()
        val allIslands = mutableListOf<Island>()
        //思路是进行一轮BFS（想象当前元素上下左右都有Node即可）
        //由于可能出现内循环，需要touched set辅助，不然BFS停不下来了
        val touchedLands = HashSet<String>(rowCnt * columnCnt)
        //BFS初始化
        val bfsQueue = LinkedList<Pair<Int, Int>>().also {
            it.offer(Pair(rowCnt/2, columnCnt/2))
        }
        val tempPosLst: ArrayList<Pair<Int, Int>> = ArrayList(4)
        //BFS开始
        while (bfsQueue.isNotEmpty()) {
            val pos = bfsQueue.poll()!!
            val curLand = grid[pos.first][pos.second]
            val curLandKey = genKeys(pos.first, pos.second, strBuilder)
            if (touchedLands.contains(curLandKey)) continue
            //未被访问的情况
            touchedLands.add(curLandKey)
            //上下左右，入队列，同时需要查看其所属岛屿是否可以合并
            tempPosLst.clear()
            //上
            if (pos.first - 1 >= 0) tempPosLst.add(Pair(pos.first-1, pos.second))
            //下
            if (pos.first + 1 < rowCnt) tempPosLst.add(Pair(pos.first+1, pos.second))
            //左
            if (pos.second - 1 >= 0) tempPosLst.add(Pair(pos.first, pos.second-1))
            //右
            if (pos.second + 1 < columnCnt) tempPosLst.add(Pair(pos.first, pos.second+1))

            //找到所属的岛屿，如果没有则创建 (如果是海水则不创建)
            var belongToIsland = if (curLand == '0') {
                null
            } else {
                //这里考虑到断开的情况，还要进行island的合并
                findBelongToIslandAndMerge(pos.first, pos.second, allIslands, strBuilder) ?: Island(strBuilder).apply { addLand(pos.first, pos.second) }
            }
            for (adjacentPos in tempPosLst) {
                //如果此元素未被探索，则加入queue
                if (!touchedLands.contains(genKeys(adjacentPos.first, adjacentPos.second, strBuilder))) {
                    bfsQueue.offer(adjacentPos)
                }
                //如果当前元素是0，也就无需合并了
                if (belongToIsland == null) continue
                //尝试合并
                val curAdjacent = grid[adjacentPos.first][adjacentPos.second]
                if (curAdjacent == '0') continue
                //和当前元素合并成岛屿
                belongToIsland.addLand(adjacentPos.first, adjacentPos.second)
                //如果相邻元素存在岛屿，尝试岛屿合并
                val adjacentBelongToIsand = findBelongToIslandAndMerge(adjacentPos.first, adjacentPos.second, allIslands, strBuilder)
                adjacentBelongToIsand?.apply {
                    belongToIsland.mergeIsLand(this)
                    allIslands.remove(this)
                }
            }
            if (belongToIsland != null && !allIslands.contains(belongToIsland)) {
                //没有merge过，则加入
                allIslands.add(belongToIsland)
            }
        }
        return allIslands.size
    }
    private fun genKeys(rowIndex: Int, colomnIndex: Int, strBuilder: StringBuilder): String {
        strBuilder.clear()
        return strBuilder.append(rowIndex).append("-").append(colomnIndex).toString()
    }

    /**
     * 找到所属的岛屿，如果有多个所属，则合并
     */
    private fun findBelongToIslandAndMerge(rowIndex: Int
                                           , columnIndex: Int
                                           , targetSet: MutableList<Island>
                                           , builder: StringBuilder): Island? {
        if (targetSet.isNullOrEmpty()) return null
        val candidates = targetSet.filter {
            it.contains(rowIndex, columnIndex)
        }
        return when {
            candidates.size > 1 -> {
                //合并
                val head = Island(builder)
                val merged = candidates.fold(head) { head, current ->
                    head.mergeIsLand(current)
                    head
                }
                targetSet.removeAll(candidates)
                targetSet.add(merged)
                merged
            }
            candidates.size == 1 -> {
                candidates[0]
            }
            else -> {
                null
            }
        }
    }

    /**
     * 用来抽象化一个岛屿
     * 岛屿中的土地都是相同的（水平竖直可达）
     */
    class Island(private val strBuilder:StringBuilder) {
        val subLands: HashSet<String> = HashSet()
        fun contains(rowIndex: Int, colomnIndex: Int): Boolean {
            strBuilder.clear()
            val key = strBuilder.append(rowIndex).append("-").append(colomnIndex).toString()
            return subLands.contains(key)
        }
        fun addLand(rowIndex: Int, colomnIndex: Int) {
            if (contains(rowIndex, colomnIndex)) return
            strBuilder.clear()
            val key = strBuilder.append(rowIndex).append("-").append(colomnIndex).toString()
            subLands.add(key)
        }
        fun mergeIsLand(otherIsland: Island) {
            subLands.addAll(otherIsland.subLands)
        }
    }
}