package me.glowacki

import com.hazelcast.core.Hazelcast


object IdGeneratorExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcastInstance = Hazelcast.newHazelcastInstance()
        val idGen = hazelcastInstance.getIdGenerator("newId")
        while (true) {
            val id = idGen.newId()
            System.err.println("Id: $id")
            Thread.sleep(1000)
        }
    }
}
