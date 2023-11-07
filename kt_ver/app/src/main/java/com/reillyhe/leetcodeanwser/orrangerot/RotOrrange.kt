package com.reillyhe.leetcodeanwser.orrangerot

import java.util.*
import kotlin.collections.ArrayList

object RotOrrange {

    fun orangesRotting(grid: Array<IntArray>): Int {
        //容错
        if (grid.isEmpty()) return -1
        if (grid[0].isEmpty()) return -1
        val rowCnt = grid.size
        val columnCnt = grid[0].size
        //第一步，先遍历grid，找到所有在0时就腐烂的橘子
        val allRottenNodes = mutableListOf<Node>()
        //总集，最后输出结果用
        val allNodeGrid = ArrayList<ArrayList<Node>>(rowCnt)
        for (row in 0.until(rowCnt)) {
            val curCurLst = ArrayList<Node>(columnCnt)
            for (col in 0.until(columnCnt)) {
                val value = grid[row][col]
                val node = Node(row, col).apply {
                    isOrange = (value != 0)
                    if (value == 2) {
                        isRotten = true
                        //这是一个一开始就腐烂的橘子
                        rotAtTime = 0
                    } else {
                        isRotten = false
                    }
                }
                if (node.isRotten) allRottenNodes.add(node)
                curCurLst.add(node)
            }
            allNodeGrid.add(curCurLst)
        }

        //第二步，分别对所有腐烂的Node进行BFS
        for (rootNode in allRottenNodes) {
            startRotNodes(rootNode, allNodeGrid, rowCnt, columnCnt)
        }

        //最后一步，遍历allNodeGrid，输出结果
        var orangeNodeCnt = 0
        var rotOrangeNodeCnt = 0
        var maxRotTime = -1
        for (row in 0.until(rowCnt)) {
            for (col in 0.until(columnCnt)) {
                val curNode = allNodeGrid[row][col]
                if (curNode.isOrange) orangeNodeCnt++
                if (curNode.isRotten) {
                    rotOrangeNodeCnt++
                    maxRotTime = Math.max(curNode.rotAtTime, maxRotTime)
                }
            }
        }
        if (orangeNodeCnt == rotOrangeNodeCnt) {
            if (orangeNodeCnt == 0) {
                //压根没有橘子
                return 0
            }
            //所有橘子都腐烂了
            return maxRotTime
        } else {
            //有橘子是好的
            return -1
        }
    }

    private fun startRotNodes(rootRottenNode: Node,
                              grid: ArrayList<ArrayList<Node>>,
                              rowCnt: Int,
                              colCnt: Int) {
        //初始化BFS参数
        val bfsQueue = LinkedList<Node>()
        bfsQueue.offer(rootRottenNode)
        val tmpLst = ArrayList<Node>(4)
        while (bfsQueue.isNotEmpty()) {
            val curNode = bfsQueue.poll()!!
            //只有root，或者当前是橘子，才进行探索
            //之所以是橘子就要探索，是因为存在多个root同时腐烂的case
            if (curNode.isRootNode() || curNode.isOrange) {
                //先让自己感染
                curNode.isRotten = true
                //尝试感染周围橘子(上下左右)
                tmpLst.clear()
                val curRow = curNode.rowIndex
                val curCol = curNode.colIndex
                val topNode = if (curRow - 1 >= 0) grid[curRow-1][curCol] else null
                if (topNode != null) tmpLst.add(topNode)
                val btmNode = if (curRow + 1 < rowCnt) grid[curRow+1][curCol] else null
                if (btmNode != null) tmpLst.add(btmNode)
                val leftNode = if (curCol - 1 >= 0) grid[curRow][curCol-1] else null
                if (leftNode != null) tmpLst.add(leftNode)
                val rightNode = if (curCol + 1 < colCnt) grid[curRow][curCol+1] else null
                if (rightNode != null) tmpLst.add(rightNode)
                //孩子的腐烂时间为父亲的+1
                val childRotTime = curNode.rotAtTime + 1
                //看看要不要加入队列中
                for (childNode in tmpLst) {
                    //不是橘子就不需要进一步探索了
                    if (!childNode.isOrange) continue
                    if (childNode.isHeathOrange() || (childNode.isRotten && childNode.rotAtTime > childRotTime)) {
                        //case1,是健康橘子，那么需要进一步操作
                        //case2,是腐烂橘子，但是当前的这个父亲，可以让其更快腐烂
                        childNode.setRottenAt(childRotTime)
                        bfsQueue.offer(childNode)
                    }
                }
            }
        }
    }

    class Node(val rowIndex:Int, val colIndex:Int) {
        //是空地还是橘子
        var isOrange = false
        //如果是橘子，橘子是否腐烂
        var isRotten = false
        //橘子腐烂的时间
        var rotAtTime = Int.MAX_VALUE

        fun setRottenAt(rottenAt: Int) {
            //永远只接受比现在更小的数字（因为两个不同的root可能会touch到相同的Node）
            if (rottenAt < rotAtTime) {
                rotAtTime = rottenAt
            }
        }

        fun isRootNode(): Boolean{
            return (isOrange && isRotten && (rotAtTime == 0))
        }

        fun isHeathOrange(): Boolean {
            return (isOrange && !isRotten)
        }
    }
}