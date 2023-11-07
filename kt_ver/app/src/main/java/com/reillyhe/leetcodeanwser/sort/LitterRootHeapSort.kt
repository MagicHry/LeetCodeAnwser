package com.reillyhe.leetcodeanwser.sort

/**
 * 公式：
 *  - 对于某个元素A,index=i
 *   -- 则其LeftChildIndex = 2*i+1
 *   -- 则其RightChildindex = 2*i+2
 *  - 对于某个[IntArray]形成的堆
 *   -- 其最后一个ParentNode index = arr.size/2 - 1
 *   -- 也可以是
 *    --- lastIndex = arr.size - 1, 若为奇，则说明其为一个LeftChild，则 (lastIndex-1)/2
 *    --- lastIndex = arr.size - 1, 若为偶，则说明其为一个RightChild, 则 (lastIndex-2)/2
 */
object LitterRootHeapSort {

    fun doBigRootHeapSort(originalArr: IntArray) {
        if (originalArr.isEmpty()) return
        for (curArrLength in originalArr.size.downTo(1)) {
            //调整一次，然后交换队首（小根）和队尾，size-1，重新迭代
            organizeLittleRoot(originalArr, curArrLength)
            val curMax = originalArr[0]
            originalArr[0] = originalArr[curArrLength-1]
            originalArr[curArrLength-1] = curMax
        }
    }

    /**
     * 进行一次小根堆调整
     * 注意，这里的初始数组的size不一定和[arrLength]相等
     * [arrLength]是指当前还需要调整的长度，originalArr中可能包括已经调整完毕了的部分
     * 因此[arrLength] <= [orignalArr.size]
     */
    fun organizeLittleRoot(orignalArr: IntArray, arrLength: Int) {
        //找到最后一个ParentNode
        val lastNodeIndex = arrLength-1
        //根据最后一个待调整堆数组元素的index，决定最后一个parentindex
        val lastParentNodeIndex = when {
            lastNodeIndex % 2 == 0 -> (lastNodeIndex-2)/2
            else -> (lastNodeIndex-1)/2
        }
        for (curParentNodeIndex in lastParentNodeIndex.downTo(0)) {
            //开始进行调整
            organizeSubTree(curParentNodeIndex, orignalArr, arrLength)
        }
    }

    /**
     * 进行调整
     * 这里会调整当前subTree的root（根据是大根还是小根的规则）
     * 并且会根据调整的结果，进一步调整当前root下面延伸出的tree
     */
    private fun organizeSubTree(currentParentIndex: Int, orignalArr: IntArray, arrLength: Int) {
        //如果已经不是parent了，则终止
        if (!isParentIndexValid(currentParentIndex, arrLength)) return
        //保证parentNode是最大（此时left/right 必有一个有值）
        val leftChildIndex = currentParentIndex * 2 + 1
        val rightChildIndex = currentParentIndex * 2 + 2
        val leftChild = if (leftChildIndex >= arrLength) null else orignalArr[leftChildIndex]
        val rightChild = if (rightChildIndex >= arrLength) null else orignalArr[rightChildIndex]
        val minChildIndex = when {
            leftChild == null -> rightChildIndex
            rightChild == null -> leftChildIndex
            leftChild < rightChild -> leftChildIndex
            else -> rightChildIndex
        }
        if (orignalArr[currentParentIndex] <= orignalArr[minChildIndex]) {
            //parent小于最小孩子，则不动
            return
        }
        //如果小于，则要交换
        val parentValue = orignalArr[currentParentIndex]
        orignalArr[currentParentIndex] = orignalArr[minChildIndex]
        orignalArr[minChildIndex] = parentValue
        //由于我们修改了maxChildIndex处的值，maxChildIndex可能也是一个parentNode，要继续进行调整
        //直到我们撞见了叶子节点或者根本不用调整
        organizeSubTree(minChildIndex, orignalArr, arrLength)
    }

    private fun isParentIndexValid(currentRootIndex: Int, arrLength: Int): Boolean {
        if (currentRootIndex >= arrLength) return false
        val leftChildIndex = currentRootIndex * 2 + 1
        val rightChildIndex = currentRootIndex * 2 + 2
        if (leftChildIndex < arrLength || rightChildIndex < arrLength) {
            //有一个孩子就行，就算是合格的ParentNode
            return true
        }
        return false
    }
}