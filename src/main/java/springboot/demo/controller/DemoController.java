package springboot.demo.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import cn.dev33.satoken.stp.StpUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.demo.bean.Novel;
import springboot.demo.dao.NovelMapper;
import springboot.demo.service.NovelService;
import springboot.demo.service.WebSocket;
import springboot.demo.service.flowcontrol.RedisFlowcontrol;
import springboot.demo.system.websocketByNetty.ChannelSupervise;

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

	Logger log = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/testhello")
	public String testhello(HttpServletRequest request) {
		System.out.println("testhello");
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
		System.out.println(request.getServletPath());
		ServletContext servletContext = request.getServletContext();
		System.out.println(request.getServletContext().getServletContextName());
		log.info("Hello spring");
		
		novelService.list();
		return "Hello spring";
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

}
