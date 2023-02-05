package springboot.demo.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import cn.dev33.satoken.stp.StpUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springboot.demo.bean.Novel;
import springboot.demo.dao.NovelMapper;
import springboot.demo.service.NovelService;
import springboot.demo.service.WebSocket;
import springboot.demo.system.websocketByNetty.ChannelSupervise;
import springboot.demo.util.CommandUtil;

import java.io.IOException;

@RestController
public class DemoController {

	@Autowired
	WebSocket webSocket;

	@Autowired
	NovelMapper novelMapper;
	
	@Autowired
	NovelService novelService;

	@Autowired
	ApplicationContext applicationContext;

	Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/testhello")
	public String testhello(HttpServletRequest request, HttpServletResponse response, String helloparam) {
		LOGGER.info("testhello");
//		HttpSession session = request.getSession();
//		int maxInactiveInterval = session.getMaxInactiveInterval();
//		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("");
//		Object novelService = applicationContext.getBean("novelService");
//		response.addCookie(new Cookie("sessionId", UUID.randomUUID().toString().replace("-","")));
		return "hello";
	}

	@GetMapping("/hello")
	public String get(HttpServletRequest request) {
//		List<String> services = discoveryClient.getServices();
//		for (String string : services) {
//			System.out.println(string);
//		}
//		List<ServiceInstance> instances = discoveryClient.getInstances("consumer");
//		for (ServiceInstance serviceInstance : instances) {
//			System.out.println(serviceInstance.getUri());
//		}
		DispatcherServlet d;
		WebMvcConfigurationSupport w;
		System.out.println(request.getServletPath());
		ServletContext servletContext = request.getServletContext();
		System.out.println(request.getServletContext().getServletContextName());
		LOGGER.info("Hello spring");
		
		novelService.list();
		return "Hello spring";
	}

	/**
	 * 测试sql注入
	 * @param query
	 * @return
	 */
	@GetMapping("/testSqlInject")
	public String testSqlInject(String query){
		return novelService.testSqlInject(query).getNovelName();
	}

	/**
	 * 测试事务不生效场景
	 * @param query
	 * @return
	 */
	@GetMapping("/testTransactional")
	public String testTransactional(String query){
		return novelService.testTransactional(query);
	}

	@RequestMapping("/sendMessage")
	public void sendMessage(String message, String id) {
		webSocket.sendMessage(message, id);
	}

	@RequestMapping("/sendMessageByNetty")
	public void sendMessageByNetty(String message, String id) {
		ChannelSupervise.send2All(new TextWebSocketFrame(message));
	}

	@RequestMapping("/getNovelName")
	public String getNovelName(int id) {
		return novelService.getNovelById(id).getNovelName();
	}

	@RequestMapping("/updateNovel")
	public String updateNovel(Integer id, String name) {
		Novel novel = new Novel();
		novel.setId(id);
		novel.setNovelName(name);
		novelMapper.updateByPrimaryKeySelective(novel);
		return "200";
	}

	@RequestMapping("/login")
	public void login(){
		StpUtil.login(1);
	}

	@RequestMapping("/getLoginId")
	public String getLoginId(){
		return StpUtil.getLoginId().toString();
	}

	@RequestMapping("/shutdown")
	public void shutdown(){
		try {
			LOGGER.info("shutdown the server");
			CommandUtil.run("poweroff");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
