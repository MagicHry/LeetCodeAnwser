package com.reillyhe.leetcodeanwser.randomcp

object RandomCopy {
    fun copyRandomList(node: Node?): Node? {
        node ?: return null
        //老链表的HashCode集合
        val oldLinkedLstIdSet = mutableSetOf<Int>()
        //新链表的random集合
        val newlinkedLstIdMap = mutableMapOf<Int, Node>()
        //新老链表的hashCode映射表
        val oldToNewMapping = mutableMapOf<Int, Int>()
        var oldNodeCursor = node
        var newNodeCursor: Node? = null
        var copiedNodeHead: Node? = null
        //拷贝投
        while (oldNodeCursor != null) {
            //对于当前的Node进行拷贝
            val curCopiedNode: Node = if (oldLinkedLstIdSet.contains(oldNodeCursor.hashCode())) {
                //需要使用缓存
                val stashedNewRandom = newlinkedLstIdMap[oldToNewMapping[oldNodeCursor.hashCode()]]!!
                stashedNewRandom
            } else {
                val copy = Node(oldNodeCursor.`val`)
                //缓存
                oldLinkedLstIdSet.add(oldNodeCursor.hashCode())
                oldToNewMapping[oldNodeCursor.hashCode()] = copy.hashCode()
                newlinkedLstIdMap[copy.hashCode()] = copy
                copy
            }
            //接入新链表
            if (copiedNodeHead == null) {
                copiedNodeHead = curCopiedNode
                newNodeCursor = curCopiedNode
            } else {
                newNodeCursor?.next = curCopiedNode
                newNodeCursor = curCopiedNode
            }
            //处理randomNode相关
            val oldRandom = oldNodeCursor.random
            if (oldRandom == null) {
                curCopiedNode.random = null
            } else {
                //缓存，这里的random指向的node，也有可能是已经存在了的case(指向自己，指向左侧)
                val newRandom = if (oldLinkedLstIdSet.contains(oldRandom.hashCode())) {
                    newlinkedLstIdMap[oldToNewMapping[oldRandom.hashCode()]]!!
                } else {
                    oldLinkedLstIdSet.add(oldRandom.hashCode())
                    val copy = Node(oldRandom.`val`)
                    newlinkedLstIdMap[copy.hashCode()] = copy
                    oldToNewMapping[oldRandom.hashCode()] = copy.hashCode()
                    copy
                }
                curCopiedNode.random = newRandom
            }
            //移动指针
            oldNodeCursor = oldNodeCursor.next
        }
        //最后需要添加尾巴
        newNodeCursor?.next = null
        return copiedNodeHead
    }
}