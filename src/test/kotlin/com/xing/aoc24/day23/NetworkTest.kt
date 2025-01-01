package com.xing.aoc24.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NetworkTest {
    val rawNetworks: List<String> = listOf(
        """
        kh-tc
        qp-kh
        de-cg
        ka-co
        yn-aq
        qp-ub
        cg-tb
        vc-aq
        tb-ka
        wh-tc
        yn-cg
        kh-ub
        ta-co
        de-co
        tc-td
        tb-wq
        wh-td
        ta-ka
        td-qp
        aq-cg
        wq-ub
        ub-vc
        de-ta
        wq-aq
        wq-vc
        wh-yn
        ka-de
        kh-ta
        co-tc
        wh-qp
        tb-vc
        td-yn
        """,
    )
    val networks = rawNetworks.map { Network.build(it.trimIndent()) }

    @Test
    fun findTTrios() {
        val trios = networks[0].findTrios()
        val ttrios = networks[0].findTTrios()
        println(trios)
        assertEquals(12, trios.size)
        assertEquals(7, ttrios.size)
    }

    @Test
    fun getLanParty() {
        val lanParty = networks[0].getLanParty()
        assertEquals("co,de,ka,ta", lanParty)
    }

}