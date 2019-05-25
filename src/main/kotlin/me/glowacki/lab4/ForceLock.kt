package me.glowacki.lab4

import com.hazelcast.core.Hazelcast

object ForceLock {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcast = Hazelcast.newHazelcastInstance()
        val lock = hazelcast.cpSubsystem.getLock("lock")

        lock.lock()

        try {
//            lock.
        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            lock.unlock()
        }
    }
}
