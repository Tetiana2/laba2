package me.glowacki

import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast


object ReplicatedMapExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val cfg = Config()
        val hz = Hazelcast.newHazelcastInstance(cfg)
        val map = hz.getReplicatedMap<String, String>("map")

        map["1"] = "Tokyo"
        map["2"] = "Paris"
        map["3"] = "New York"

        println("Finished loading map")
        hz.shutdown()
    }
}
