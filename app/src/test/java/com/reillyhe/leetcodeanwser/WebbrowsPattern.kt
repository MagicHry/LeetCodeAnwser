package com.reillyhe.leetcodeanwser
import com.reillyhe.leetcodeanwser.websitepattern.WebsiteVisitPattern
import org.junit.Test
import org.junit.Assert.*
class WebbrowsPattern {

    @Test
    fun mid_test() {
        val user = arrayOf(
            "h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"
        )
        val timeStamp = intArrayOf(
            527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930
        )
        val website = arrayOf(
            "hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"
        )
        WebsiteVisitPattern.mostVisitedPattern(user, timeStamp, website)
    }

    @Test
    fun str_test() {
        val str1 = "m"
        val str2 = "mhpzoaiw"
        val ret = str1.compareTo(str2)
        assertEquals(-9,ret)
    }
}