package me.glowacki

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig


object HazelcastLocalClient {
    @JvmStatic
    fun main(args: Array<String>) {
        val clientConfig = ClientConfig()
        clientConfig.addAddress("172.21.1.225:5701")
        val client = HazelcastClient.newHazelcastClient(clientConfig)
        val map = client.getMap<Int, String>("customers")
        println("Map Size:" + map.size)
    }
}
