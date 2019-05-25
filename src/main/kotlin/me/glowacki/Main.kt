package me.glowacki

import com.hazelcast.config.Config
import com.hazelcast.config.InterfacesConfig
import com.hazelcast.config.NetworkConfig
import com.hazelcast.core.Hazelcast


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val cfg = Config()
        cfg.setProperty("hazelcast.socket.bind.any", "false")
//        val interfaces = InterfacesConfig().setEnabled(true).setInterfaces(listOf("172.20.10.*"))
        val interfaces = InterfacesConfig().setEnabled(true).setInterfaces(listOf("192.168.1.*"))
        val network = NetworkConfig().setInterfaces(interfaces)
        cfg.networkConfig = network
        val instance = Hazelcast.newHazelcastInstance(cfg)
        val mapCustomers = instance.getMap<Int, String>("customers")
        mapCustomers[1] = "Joe"
        mapCustomers[2] = "Ali"
        mapCustomers[3] = "Avi"

        println("Customer with key 1: " + mapCustomers[1])
        println("Map Size:" + mapCustomers.size)
    }
}
