package me.glowacki.lab4

import com.hazelcast.core.Hazelcast
import kotlin.random.Random

object DistributedQueue {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcast = Hazelcast.newHazelcastInstance()
        val queue = hazelcast.getQueue<String>("queue")

//        queue.clear()

        while (true) {
            queue.put("${Random.nextInt()}")
        }

//        while (true) {
//            queue.take()
//        }
    }
}
