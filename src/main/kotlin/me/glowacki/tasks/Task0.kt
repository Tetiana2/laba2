package me.glowacki.tasks

import com.hazelcast.core.Hazelcast

object Task0 {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcast = Hazelcast.newHazelcastInstance()
        val map = hazelcast.getMap<Int, String>("sample")
        for (i in (1..999)) {
            map[i] = "Entry $i"
        }

        map.forEach {
            println(it)
        }
    }
}
