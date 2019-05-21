package me.glowacki

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val cfg = Config()
        val instance = Hazelcast.newHazelcastInstance(cfg)
        val mapCustomers = instance.getMap<Int, String>("customers")
        mapCustomers[1] = "Joe"
        mapCustomers[2] = "Ali"
        mapCustomers[3] = "Avi"

        println("Customer with key 1: " + mapCustomers[1])
        println("Map Size:" + mapCustomers.size)
    }
}
