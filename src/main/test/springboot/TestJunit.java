//package springboot;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import springboot.demo.StartApplication;
//import springboot.demo.bean.NovelExample;
//import springboot.demo.controller.DemoController;
//import springboot.demo.dao.NovelMapper;
//import springboot.demo.util.Reflection;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//public class TestJunit {
//
//	@Autowired
//	NovelMapper novelMapper;
//
//	DemoController demoController = new DemoController();
//
//	List l;
//
//	/**
//	 * 构造mock数据
//	 */
//	@Before
//	public void testMock() {
//		List mock = Mockito.mock(List.class);
//		Mockito.when(mock.get(0)).thenReturn(0);
//
//		JedisPool jedisPool = Mockito.mock(JedisPool.class);
//		Jedis jedis = Mockito.mock(Jedis.class);
//
//		Mockito.when(jedisPool.getResource()).thenReturn(jedis);
//		Mockito.when(jedis.get("1")).thenReturn("1");
//
//		Object o = mock.get(0);
//		l = mock;
//
//		String r = jedisPool.getResource().get("1");
//
//		Reflection.setPrivate(demoController,"jedisPool", jedisPool);
//
//		System.out.println(r);
//	}
//
//	@Test
//	public void test0() {
//		System.out.println("test");
//	}
//
////	@Test
//	public void testGet() {
////		String r = demoController.getKV("1");
////		Assert.assertEquals("1",r);
//	}
//
//	@Test
//	public void testNovelMapper() {
//		NovelExample example = new NovelExample();
//		example.createCriteria().andIdEqualTo(1);
//		System.out.println(novelMapper.selectByExample(example));
//
//		Object o = l.get(0);
//		Assert.assertEquals(0,o);
//		System.out.println(o);
//	}
//
//}
