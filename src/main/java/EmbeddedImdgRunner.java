import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Queue;

public class EmbeddedImdgRunner {
    public static void main(String[] args) {
        Config hzConfig = new Config();
        // start Hazelcast IMDG cluster member
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(hzConfig);

        // IMap is hz extension
        IMap<Integer, String> customers = hz.getMap("customers"); // creates or retrieves data structure
        customers.put(1, "Uladzimir");
        customers.put(2, "John");
        customers.put(3, "Jack");

        System.out.println("Customer with key #1 : " + customers.get(1));
        System.out.println("IMap size = " + customers.size());

        Queue<String> queueCustomers = hz.getQueue("customers");
        queueCustomers.offer("Tom");
        queueCustomers.offer("Jane");
        queueCustomers.offer("Trace");

        System.out.println("First customer into the queue : " + queueCustomers.poll());
        System.out.println("Second customer into the queue : " + queueCustomers.peek());
        System.out.println("Queue size : " + queueCustomers.size());

        hz.shutdown();
    }
}
