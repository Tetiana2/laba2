package me.glowacki.lab4

import com.hazelcast.core.Hazelcast

object DistributedLock {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcast = Hazelcast.newHazelcastInstance()
        val lock = hazelcast.cpSubsystem.getLock("lock")

        while (true) {
            try {
                lock.lock()
                Thread.sleep(1000)
            } catch (t: Throwable) {
                t.printStackTrace()
            } finally {
                lock.unlock()
            }
        }
    }
}
