package org.example.kmpskeleton.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object CharacterDetailScreen

@Serializable
object HomeScreen

fun findAnagrams(s: String, p: String): List<Int> {
    val result = mutableListOf<Int>()

    val pCount = IntArray(26)
    val sCount = IntArray(26)
    var matches = 0

    for(i in p.indices) {
        pCount[p[i] - 'a']++
        sCount[s[i] - 'a']++
    }

    for (i in 0..25){
        if (pCount[i] == sCount[i]) matches++
    }

    //abab ab

    for (i in p.length until s.length) {
        if (matches == 26) result.add(i - p.length)

        val rightChar = s[i] - 'a'
        val leftChar = s[i - p.length] - 'a'

        // Add right char
        sCount[rightChar]++
        if (sCount[rightChar] == pCount[rightChar]) {
            matches++
        } else if (sCount[rightChar] == pCount[rightChar] + 1) {
            matches--
        }

        // Remove left char
        sCount[leftChar]--
        if (sCount[leftChar] == pCount[leftChar]) {
            matches++
        } else if (sCount[leftChar] == pCount[leftChar] - 1) {
            matches--
        }
    }

    if (matches == 26) result.add(s.length - p.length)

    return result
}
