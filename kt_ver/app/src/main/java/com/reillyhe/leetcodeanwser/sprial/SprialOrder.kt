package com.reillyhe.leetcodeanwser.sprial

object SprialOrder {

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val numofRow = matrix.size
        val numofColomn = matrix.firstOrNull()?.size
        val result = mutableListOf<Int>()
        //特殊情况 空matrix
        if (numofRow == 0 || numofColomn == null) return result

        //分两步进行，第一步，先计算出，水平方向以及垂直方向的ExploreSet
        var horizontalLeft = 0
        var horizontalRight = numofColomn - 1
        var verticalTop = 0
        var verticalBottom = numofRow - 1
        val exporeSets = mutableListOf<ExploreSet>()
        while (((horizontalLeft <= horizontalRight) && (verticalTop <= verticalBottom))) {
            //left->right
            exporeSets.add(ExploreSet(horizontalLeft, horizontalRight, Direction.LEFT_TO_RIGHT))
            //top->bottom
            exporeSets.add(ExploreSet(verticalTop, verticalBottom, Direction.TOP_TO_BTM))
            //right->left
            exporeSets.add(ExploreSet(horizontalRight, horizontalLeft, Direction.RIGHT_TO_LEFT))
            //bottom->top
            exporeSets.add(ExploreSet(verticalBottom, verticalTop, Direction.BTM_TO_TOP))
            //调整index
            horizontalLeft++
            horizontalRight--
            verticalTop++
            verticalBottom--
        }

        //第二步，根据生成的探索集，添加元素
        var curRow = 0
        var curColomn = 0
        val valueSet = mutableSetOf<String>()
        for (set in exporeSets) {
            //水平方向的，是闭区间，垂直方向的，是开区间
            when (set.direction) {
                Direction.LEFT_TO_RIGHT -> {
                    for (columIndex in set.startIndex..set.endIndex) {
                        val value = matrix[curRow][columIndex]
                        val posInfo = "${curRow}-${columIndex}"
                        if (posInfo !in valueSet) {
                            result.add(value)
                            valueSet.add(posInfo)
                        }
                        curColomn = columIndex
                    }
                }
                Direction.TOP_TO_BTM -> {
                    for (rowIndex in (set.startIndex+1) until set.endIndex) {
                        if (rowIndex < 0) continue
                        val value = matrix[rowIndex][curColomn]
                        val posInfo = "${rowIndex}-${curColomn}"
                        if (posInfo !in valueSet) {
                            result.add(value)
                            valueSet.add(posInfo)
                        }
                        curRow = rowIndex
                    }
                    //top->btm的最后，rowIndex需要向下再走一步
                    if (curRow < (numofRow - 1)) {
                        curRow++
                    }
                }
                Direction.RIGHT_TO_LEFT -> {
                    for (columIndex in set.startIndex.downTo(set.endIndex)) {
                        val value = matrix[curRow][columIndex]
                        val posInfo = "${curRow}-${columIndex}"
                        if (posInfo !in valueSet) {
                            result.add(value)
                            valueSet.add(posInfo)
                        }
                        curColomn = columIndex
                    }
                }
                Direction.BTM_TO_TOP -> {
                    for (rowIndex in (set.startIndex-1).downTo(set.endIndex+1)) {
                        if (rowIndex < 0) continue
                        val value = matrix[rowIndex][curColomn]
                        val posInfo = "${rowIndex}-${curColomn}"
                        if (posInfo !in valueSet) {
                            result.add(value)
                            valueSet.add(posInfo)
                        }
                        curRow = rowIndex
                    }
                    //btm->top的最后，不需要做任何操作
                }
            }
        }
        return result
    }

    data class ExploreSet(val startIndex: Int, val endIndex: Int, val direction: Direction)
    enum class Direction {
        LEFT_TO_RIGHT, TOP_TO_BTM, RIGHT_TO_LEFT, BTM_TO_TOP
    }
}