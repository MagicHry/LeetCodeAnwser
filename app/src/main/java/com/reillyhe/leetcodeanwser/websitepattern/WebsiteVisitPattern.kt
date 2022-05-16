package com.reillyhe.leetcodeanwser.websitepattern

import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

object WebsiteVisitPattern {

    /**
     * 没有太好的想法，核心思路是构造中间数据结构
     * Step1：我们需要按照时间顺序，生成一个以UserName为Key，按照时间顺序排序的网站列表为Value的中间数据结构
     * Step2：针对这个Map中的每一个UserName，我们要枚举其可能的Pattern，然后看看能不能和其他User匹配
     * Step3: 在这个过程中，我们通过MaxPattern来维护最大的Pattern即可
     */
    fun mostVisitedPattern(username: Array<String>, timestamp: IntArray, website: Array<String>): List<String> {
        //无需容错，因为根据题目，userName.size >= 3，所以没有为空的case出现
        val strBuilder = StringBuilder()
        val userHistoryInPriority = HashMap<String, PriorityQueue<BrowseHistory>>(username.size)
        //Step1:生成中间数据结构，这里以timeStamp为基准访问
        timestamp.forEachIndexed { index, curTime ->
            val curUser = username[index]
            val curWebsite = website[index]
            val history = BrowseHistory(curUser, curWebsite, curTime)
            if (!userHistoryInPriority.containsKey(curUser)) {
                //创建优先队列
                userHistoryInPriority[curUser] = PriorityQueue<BrowseHistory>()
            }
            userHistoryInPriority[curUser]!!.offer(history)
        }
        val userHistoryMap = HashMap<String, MutableList<BrowseHistory>>(username.size)
        //完成优先队列-》普通数组的转换
        for (entry in userHistoryInPriority) {
            if (!userHistoryMap.containsKey(entry.key)) {
                userHistoryMap[entry.key] = mutableListOf()
            }
            while (entry.value.isNotEmpty()) {
                userHistoryMap[entry.key]!!.add(entry.value.poll()!!)
            }
        }
        //Step2:针对userHistoryMap中的每个用户，我们要枚举他可能的pattern
        var currentMaxPatternRawName: String = ""
        var currentMaxPatternCnt = 0
        val patternContainer = HashMap<String, Pattern>()
        for (userEntry in userHistoryMap) {
            val allPatterns = numericPattern(userEntry.value, strBuilder)
            allPatterns?.run {
                forEach {
                    // Step3: 在这个过程中，我们通过MaxPattern来维护最大的Pattern即可
                    if (!patternContainer.containsKey(it.rawPatternValue)) {
                        //不存在，则创建
                        patternContainer[it.rawPatternValue] = it
                    }
                    //增加计数
                    val modValue = patternContainer[it.rawPatternValue]!!.cnt + 1
                    patternContainer[it.rawPatternValue]!!.cnt = modValue
                    if (modValue > currentMaxPatternCnt || currentMaxPatternRawName.isEmpty()) {
                        //大于，直接修改最大值
                        currentMaxPatternRawName = it.rawPatternValue
                        currentMaxPatternCnt = modValue
                    } else if (modValue == currentMaxPatternCnt) {
                        //相等，则进一步比较字母排列顺序，如果当前pattern比较小，则需要修改记录
                        //注意，这里必须要一个一个website对比
                        for (index in 0.until(3)) {
                            val curPatternFirst = it.patternAsLst[index]
                            val curMaxPatternFirst = patternContainer[currentMaxPatternRawName]!!.patternAsLst[index]
                            if (curPatternFirst.compareTo(curMaxPatternFirst) < 0) {
                                //小于，那么我们找到了新的maxPattern
                                currentMaxPatternRawName = it.rawPatternValue
                                currentMaxPatternCnt = modValue
                            } else if (curPatternFirst.compareTo(curMaxPatternFirst) > 0) {
                                //大于，那不用比较了，维持原来的max
                                break
                            } else {
                                //相等，继续比较lst中的下一个website决定大小
                                continue
                            }
                        }
                    }
                }
            }
        }
        return patternContainer[currentMaxPatternRawName]!!.patternAsLst
    }

    private fun numericPattern(websiteHistories: MutableList<BrowseHistory>, strBuilder: StringBuilder): MutableList<Pattern>? {
        //枚举出所有可能的pattern
        //由于pattern为3，所以小于3的用户浏览记录肯定是没有的
        if (websiteHistories.size < 3) return null
        val tmpSet = HashSet<String>()
        strBuilder.clear()
        val ret = mutableListOf<Pattern>()
        for (firstWebSite in 0.until(websiteHistories.size-2)) {
            for (secondWebSite in (firstWebSite+1).until(websiteHistories.size-1)) {
                for (thirdWebSite in (secondWebSite+1).until(websiteHistories.size)) {
                    strBuilder.clear()
                    strBuilder.append(websiteHistories[firstWebSite].website)
                    strBuilder.append(websiteHistories[secondWebSite].website)
                    strBuilder.append(websiteHistories[thirdWebSite].website)
                    //考虑到一个用户可能疯狂上一个网站，所以会枚举出很多相同的pattern，因此这里要做判断
                    val rawPatternName = strBuilder.toString()
                    if (tmpSet.contains(rawPatternName)) continue
                    val pattern = Pattern(rawPatternName)
                    pattern.patternAsLst.add(websiteHistories[firstWebSite].website)
                    pattern.patternAsLst.add(websiteHistories[secondWebSite].website)
                    pattern.patternAsLst.add(websiteHistories[thirdWebSite].website)
                    ret.add(pattern)
                    tmpSet.add(rawPatternName)
                }
            }
        }
        return ret
    }

    class Pattern(val rawPatternValue: String) {
        val patternAsLst: MutableList<String> = mutableListOf()
        var cnt = 0
    }

    class BrowseHistory(val user:String, val website:String, val time: Int): Comparable<BrowseHistory> {
        override fun compareTo(other: BrowseHistory): Int {
            //对于单个用户，优先比较时间
            return when {
                time != other.time -> {
                    time - other.time
                }
                else -> {
                    //如果时间相同，只能比较website的内容
                    website.compareTo(other.website)
                }
            }
        }
    }
}