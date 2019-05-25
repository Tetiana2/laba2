package me.glowacki.tasks

import com.hazelcast.core.Hazelcast
import java.io.Serializable

object Task1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcast = Hazelcast.newHazelcastInstance()
        val key = "1"
        val map = hazelcast.getMap<String, Value>("map")
        map.putIfAbsent(key, Value())

        for (k in 1..999) {
            val value = map[key]
            Thread.sleep(100)
            value?.let { it.amount += 1 }
            map.set(key, value)
        }

        println("Result ${map[key]?.amount}")
    }

    internal class Value : Serializable {
        var amount: Int = 0
    }
}
