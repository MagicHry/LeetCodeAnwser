package com.reillyhe.leetcodeanwser.kclosedp

import android.util.SparseArray
import kotlin.math.pow
import kotlin.math.sqrt

object KClosetPoints {

    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        if (k == 0 || points.isEmpty()) return arrayOf(intArrayOf())
        //首先，我们需要让整个数组中所有的点对于原点求解距离
        val pointDistanceLst = ArrayList<PointWithDis>(points.size)
        points.forEachIndexed { index, point ->
            val distance = calDistance(point)
            val pointWidD = PointWithDis(index, distance)
            pointDistanceLst.add(pointWidD)
        }
        //接下来，我们使用一个小根堆进行堆排序，当然，堆排序只需要迭代K次就可以了
        littleRootHeapSort(pointDistanceLst, k)
        //取后K个元素，就是距离远点最近的K个点了
        val retArr = mutableListOf<IntArray>()
        for (index in (pointDistanceLst.size-1).downTo(pointDistanceLst.size-k)) {
            val indexInOriginArr = pointDistanceLst[index].indexInOriginArr
            retArr.add(points[indexInOriginArr])
        }
        return retArr.toTypedArray()
    }

    private fun littleRootHeapSort(points: ArrayList<PointWithDis>, iterationCnt: Int) {
        var iterCnt = 0
        for (curNeedSortLstLenght in points.size.downTo(1)) {
            iterCnt++
            //进行迭代
            organizeLittleRoot(points, curNeedSortLstLenght)
            //完成后，调换前后元素位置
            val tailNode = points[curNeedSortLstLenght-1]
            points[curNeedSortLstLenght-1] = points[0]
            points[0] = tailNode
            //如果已经达到次数限制了，就停止，不用继续了
            if (iterCnt == iterationCnt) return
        }
    }

    private fun organizeLittleRoot(points: ArrayList<PointWithDis>, needSortLstLength: Int) {
        val lastPointIndex = needSortLstLength - 1
        val lastParentNodeIndex = when {
            lastPointIndex%2 == 0 -> (lastPointIndex - 2)/2
            else -> (lastPointIndex - 1)/2
        }
        for (curParentIndex in lastParentNodeIndex.downTo(0)) {
            //从最后一个parent节点开始往前不断调整subtree
            organizeSubTree(points, needSortLstLength, curParentIndex)
        }
    }

    private fun organizeSubTree(points: ArrayList<PointWithDis>, needSortLstLength: Int, parentIndex: Int) {
        //怕堆栈溢出，这里本来的subtree递归改成循环
        var curParentIndex = parentIndex
        while (isParentIndex(curParentIndex, needSortLstLength)) {
            //进入这里，他必定还是一个父节点
            val leftChildIndex = curParentIndex * 2 + 1
            val rightChildIndex = curParentIndex * 2 + 2
            //求解最小的孩子index
            val minChildIndex = when {
                leftChildIndex >= needSortLstLength -> rightChildIndex
                rightChildIndex >= needSortLstLength -> leftChildIndex
                points[leftChildIndex].distance <= points[rightChildIndex].distance -> leftChildIndex
                else -> rightChildIndex
            }
            //和父节点对比
            val parentNode = points[curParentIndex]
            if (points[parentIndex].distance <= points[minChildIndex].distance) {
                //如果还是父亲小，则不用迭代了，当前这棵树以及其下面的subtree都是完备的小根
                return
            }
            //如果父亲大，则要进行交换，同时要探查被调换位置的孩子所构成的subtree的情况
            points[curParentIndex] = points[minChildIndex]
            points[minChildIndex] = parentNode
            //同时将指针移动到minChildIndex处，继续进行迭代
            curParentIndex = minChildIndex
        }
    }

    /**
     * 检查一个index是否是ParentNode
     */
    private fun isParentIndex(target: Int, needSortLstLength: Int): Boolean {
        if (target >= needSortLstLength) return false
        val leftChildIndex = target * 2 + 1
        val rightChildIndex = target * 2 + 2
        if (leftChildIndex >= needSortLstLength && rightChildIndex >= needSortLstLength) return false
        return true
    }

    /**
     * 求解某一点到原点的欧几里得距离
     */
    private fun calDistance(points: IntArray): Double {
        if (points.size != 2) {
            return 0.0
        }
        val xV = points[0].toDouble()
        val yV = points[1].toDouble()
        return Math.sqrt(Math.pow(xV, 2.0) + Math.pow(yV, 2.0))
    }

    data class PointWithDis(val indexInOriginArr: Int, val distance: Double)
}