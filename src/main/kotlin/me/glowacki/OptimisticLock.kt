package me.glowacki

import com.hazelcast.core.Hazelcast
import java.io.Serializable


object OptimisticLock {
    @JvmStatic
    fun main(args: Array<String>) {
        val hz = Hazelcast.newHazelcastInstance()
        val map = hz.getMap<String, Value>("map")
        val key = "1"
        map[key] = Value()
        println("Starting")
        for (k in 0..999) {
            if (k % 10 == 0) println("At: $k")
            while (true) {
                val oldValue = map[key]
                val newValue = Value(oldValue!!)
                Thread.sleep(10)
                newValue.amount++
                if ((map as java.util.Map<String, Value>).replace(key, oldValue, newValue))
                    break
            }
        }
        println("Finished! Result = " + map[key]?.amount)
    }

    internal class Value : Serializable {
        var amount: Int = 0

        constructor()

        constructor(that: Value) {
            this.amount = that.amount
        }

        override fun equals(other: Any?): Boolean {
            if (other === this) return true
            if (other !is Value) return false
            val that = other as Value?
            return that!!.amount == this.amount
        }

        override fun hashCode(): Int {
            return amount
        }
    }
}
