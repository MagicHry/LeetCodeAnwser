package com.reillyhe.leetcodeanwser.lrucache

import java.lang.StringBuilder

class LRUCache(val capacity: Int) {

    //key链表
    private val keyLinkedLst by lazy {
        KeyLinkedList()
    }
    //k-v存储器
    private val cacheContainer: MutableMap<Int, KeyNode> by lazy {
        mutableMapOf()
    }
    private val currentSize
        get() = cacheContainer.size

    fun dumpCacheInPriority(): String {
        val strBuilder = StringBuilder()
        strBuilder.append("{")
        var cursor = keyLinkedLst.head
        while (cursor != null) {
            strBuilder.append("[${cursor.key}:${cursor.value}]")
            cursor = cursor.next
        }
        strBuilder.append("}")
        return strBuilder.toString()
    }

    fun get(key: Int): Int {
        if (!cacheContainer.containsKey(key)) return -1
        val node = cacheContainer[key]!!
        //移动链表
        val removed = keyLinkedLst.remove(node)
        keyLinkedLst.addToHead(removed!!)
        return removed.value
    }

    fun put(key: Int, value: Int) {
        //是否是存在的key，如果存在，不用进行cache resize
        if (cacheContainer.containsKey(key)) {
            //移动LinkedLst
            val node = cacheContainer[key]!!
            //替换value
            node.value = value
            //移动链表
            val removed = keyLinkedLst.remove(node)
            keyLinkedLst.addToHead(removed!!)
            return
        }
        //不存在
        val node = KeyNode(key, value, null, null)
        if (currentSize >= capacity) {
            //需要调整cache
            cacheResize()
        }
        //加入新元素
        cacheContainer[key] = node
        keyLinkedLst.addToHead(node)
    }

    /**
     * 调整cache，讲最久远的Node剔除出缓存
     */
    private fun cacheResize() {
        keyLinkedLst.tail ?: return
        val tail = keyLinkedLst.tail
        val rmNode = keyLinkedLst.remove(tail)
        //兜底
        rmNode ?: return
        cacheContainer.remove(rmNode.key)
    }

    data class KeyNode(val key: Int, var value: Int, var previous: KeyNode?, var next:KeyNode?)
    class KeyLinkedList() {
        var head: KeyNode? = null
        var tail: KeyNode? = null

        fun addToHead(node: KeyNode) {
            if (head == null) {
                //第一个元素的case
                head = node
                tail = node
                node.next = null
                node.previous = null
                return
            }
            val oldHead = head
            head = node
            head?.next = oldHead
            head?.previous = null
            oldHead?.previous = head
        }

        fun remove(target: KeyNode?): KeyNode? {
            target ?: return null
            if (target == head) {
                //头部处理
                head = target.next
            }
            if (target == tail) {
                //尾部处理
                tail = target.previous
            }
            //自身处理
            target.previous?.next = target.next
            target.next?.previous = target.previous
            return target.also {
                it.previous = null
                it.next = null
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */