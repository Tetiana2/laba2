package me.glowacki

import com.hazelcast.core.Hazelcast
import java.io.Serializable


object PessimisticLock {
    @JvmStatic
    fun main(args: Array<String>) {
        val hz = Hazelcast.newHazelcastInstance()
        val map = hz.getMap<String, Value>("map")
        val key = "1"
        map[key] = Value()
        println("Starting")
        for (k in 0..999) {
            map.lock(key)
            try {
                val value = map[key]
                Thread.sleep(10)
                value?.let { it.amount++ }
                map[key] = value
            } finally {
                map.unlock(key)
            }
        }
        println("Finished! Result = " + map[key]?.amount)
    }

    internal class Value : Serializable {
        var amount: Int = 0
    }
}
