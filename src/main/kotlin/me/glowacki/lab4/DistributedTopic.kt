package me.glowacki.lab4

import com.hazelcast.core.Hazelcast
import kotlin.random.Random

object DistributedTopic {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcast = Hazelcast.newHazelcastInstance()
        val topic = hazelcast.getTopic<Message>("topic")

        while (true) {
            topic.publish(Message(Random.nextInt()))
        }

//        while (true) {
//            topic.addMessageListener { message ->
//                println(message)
//            }
//        }
    }
}

data class Message(val value: Int)
