//package local;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import redis.clients.jedis.JedisSentinelPool;
//
//public class TestJedisSentinelPool {
//
//	public static void main(String[] args) {
//		Set<String>sentinels = new HashSet<>();
//		sentinels.add("127.0.0.1:26379");
//		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
//		System.out.println(pool.getResource().get("name"));
//	}
//
//}
