package com.reillyhe.leetcodeanwser

import com.reillyhe.leetcodeanwser.searchsuggest.SearchSuggestion
import org.junit.Test
import org.junit.Assert.*
class SearchSuggestionTest {

    @Test
    fun ez_test() {
        val products = mutableListOf<String>(
            "mobile","mouse","moneypot","monitor","mousepad"
        )
        val searchword = "mouse"
        val ret = SearchSuggestion.suggestedProducts(products.toTypedArray(), searchword)
        println("XXXX The result is : $ret")
        assertEquals(0,0)
    }

    @Test
    fun mid_test() {
        val products = mutableListOf<String>(
            "bags","baggage","banner","box","cloths"
        )
        val searchword = "bags"
        val ret = SearchSuggestion.suggestedProducts(products.toTypedArray(), searchword)
        println("XXXX The result is : $ret")
        assertEquals(0,0)
    }
}