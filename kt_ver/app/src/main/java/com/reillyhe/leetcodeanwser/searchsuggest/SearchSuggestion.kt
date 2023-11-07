package com.reillyhe.leetcodeanwser.searchsuggest

import java.lang.StringBuilder

/**
 * 理论上可以使用类似于B+树这样的结构，增强搜索
 * 这里的话就不用了
 * 核心思路：
 * 1.利用上一次的搜索结果，因为 假设此次输入m,下次输入mo，我们在匹配mo的时候，用m的结果即可
 * 2.对于[products]进行排序，这样到时候我们产生的结果集，直接取前三个就可以了
 */
object SearchSuggestion {

    fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
        //products[i]和searchWord的size都>=1，因此无需容错
        val result = mutableListOf<List<String>>()
        //Step1，首先要对products按照字典顺序进行排序
        val sortedProducts = products.sortedBy {it}
        val searchWordChars = searchWord.toCharArray()
        //首次搜索，需要匹配整个prodcuts
        var searchSet = sortedProducts
        val strBuilder = StringBuilder()
        for (curTypeInChar in searchWordChars) {
            strBuilder.append(curTypeInChar)
            //用于撞库的prefix需要包含之前的prefix
            val curPrefix = strBuilder.toString()
            val searchResultRaw = searchSet.filter {
                it.startsWith(curPrefix)
            }
            //生成本次的搜索结果
            val searchResult = mutableListOf<String>()
            when {
                searchResultRaw.size <= 3 -> {
                    //不足3个，则全部加入
                    for (product in searchResultRaw) {
                        searchResult.add(product)
                    }
                }
                searchResultRaw.size > 3 -> {
                    //大于3个，取前三个
                    for (index in 0.until(3)) {
                        searchResult.add(searchResultRaw[index])
                    }
                }
                else -> {}
            }
            //加入结果
            result.add(searchResult)
            //本次的搜索结果将会是下一次的搜索集
            searchSet = searchResultRaw
        }
        return result
    }
}