package me.glowacki

import com.hazelcast.core.Hazelcast
import com.hazelcast.transaction.TransactionOptions


object TransactionExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val hazelcastInstance = Hazelcast.newHazelcastInstance()

        val options = TransactionOptions()
            .setTransactionType(TransactionOptions.TransactionType.TWO_PHASE)

        val context = hazelcastInstance.newTransactionContext(options)
        context.beginTransaction()

        val queue = context.getQueue<Any>("myqueue")
        val map = context.getMap<Any, Any>("mymap")
        val set = context.getSet<Any>("myset")

        try {
            queue.poll()
            //process obj
            map.put("1", "value1")
            set.add("value")
            //do other things
            context.commitTransaction()
        } catch (t: Throwable) {
            context.rollbackTransaction()
        }

    }
}
