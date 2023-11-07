package com.reillyhe.leetcodeanwser.logreorder

object LogReorder {
    /**
     * 核心思路是两步：
     * Step1:根据内部，构建POJO，包含了等下需要排序用的信息
     * Step2:利用大根对进行排序
     */
    fun reorderLogFiles(logs: Array<String>): Array<String> {
        //容错
        val ret = ArrayList<String>()
        if (logs.isEmpty()) return ret.toTypedArray()
        val logFilesLst = mutableListOf<LogFile>()
        //第一步，扫描logs，构造LogFile用于后面排序
        logs.forEachIndexed { index, logStr ->
            val logFile = LogFile(logStr)
            val firstSpace = logStr.indexOf(" ")
            val firstLetter = logStr[firstSpace+1]
            val valueStr = logStr.substring(firstSpace+1,logStr.length)
            val id = logStr.substring(0, firstSpace)
            logFile.apply {
                isDigit = firstLetter.isDigit()
                originalIndex = index
                logValue = valueStr
                identifier = id
            }
            logFilesLst.add(logFile)
        }
        //第二步，排序
        heapSort(logFilesLst)
        //最后输出结果
        logFilesLst.forEach {
            ret.add(it.logWholeStr)
        }
        return ret.toTypedArray()
    }

    private fun heapSort(input: MutableList<LogFile>) {
        var curHeapLength = input.size
        while (curHeapLength >= 1) {
            //进行一次堆调整，输出当前堆中最大值
            heapAdjust(input, curHeapLength)
            //调整位置
            val curMax = input[0]
            input[0] = input[curHeapLength-1]
            input[curHeapLength-1] = curMax
            //继续迭代
            curHeapLength--
        }
    }

    private fun heapAdjust(input: MutableList<LogFile>, heapLength: Int) {
        val lastElementIndex = heapLength - 1
        //根据最后一个元素index奇偶性质，求解最后一个父亲节点的index
        val lastParentIndex = when {
            lastElementIndex%2 == 0 -> (lastElementIndex-2)/2
            else -> (lastElementIndex-1)/2
        }
        for (curParentIndex in lastParentIndex.downTo(0)) {
            //进行树调整
            modifySubTree(input, heapLength, curParentIndex)
        }
    }

    /**
     * 进行父亲节点和child的调整，保证能够满足大根堆的性质
     */
    private fun modifySubTree(input: MutableList<LogFile>, heapLength: Int, parentIndex: Int) {
        //如果当前节点已经不是父亲节点了，那么调整已经完毕
        var curParentIndex = parentIndex
        while (isParent(input, heapLength, curParentIndex)) {
            //保证我们的parent总是最大的
            val leftChildIndex = curParentIndex*2 + 1
            val rightChildIndex = curParentIndex*2 + 2
            val maxChildIndex = when {
                //一个孩子不存在，那么只有可能是右孩子
                rightChildIndex >= heapLength -> {
                    leftChildIndex
                }
                //两个孩子都存在走排序
                input[leftChildIndex] > input[rightChildIndex] -> {
                    leftChildIndex
                }
                else -> {
                    rightChildIndex
                }
            }
            if (input[curParentIndex] > input[maxChildIndex]) {
                //父节点大，那么结束循环
                return
            } else {
                //孩子大，那么进行交换
                val parent = input[curParentIndex]
                input[curParentIndex] = input[maxChildIndex]
                input[maxChildIndex] = parent
                //由于发生了交换，现在开始查看maxChildIndex这个位置是否需要进行调整
                curParentIndex = maxChildIndex
            }
        }
    }

    private fun isParent(input: MutableList<LogFile>, heapLength: Int, parentIndex: Int): Boolean {
        val leftChildIndex = parentIndex*2 + 1
        val rightChildIndex = parentIndex*2 + 2
        //如果两个孩子都不存在，那么已经不是父亲节点了
        return when {
            leftChildIndex < heapLength -> {
                true
            }
            rightChildIndex < heapLength -> {
                true
            }
            else -> {
                false
            }
        }
    }

    /**
     * Log对象，主要用于排序
     */
    class LogFile(val logWholeStr: String): Comparable<LogFile>{
        var isDigit: Boolean = false
        var originalIndex: Int = -1
        var logValue: String = ""
        var identifier: String = ""
        override fun compareTo(other: LogFile): Int {
            return when {
                //都是digit类型，顺序维持相对不变
                isDigit && other.isDigit -> {
                    originalIndex - other.originalIndex
                }
                //一个是digit，一个是letter，则letter更小
                isDigit && !other.isDigit -> {
                    1
                }
                //一个是digit，一个是letter，则letter更小
                !isDigit && other.isDigit -> {
                    -1
                }
                //都是letter，则返回string排序
                else -> {
                    if (logValue == other.logValue) {
                        //相等的情况，比较identifier
                        identifier.compareTo(other.identifier)
                    } else {
                        logValue.compareTo(other.logValue)
                    }
                }
            }
        }
    }
}