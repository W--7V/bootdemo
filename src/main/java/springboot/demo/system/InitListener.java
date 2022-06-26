package springboot.demo.system;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import springboot.demo.service.flowcontrol.RedisFlowcontrol;
import springboot.demo.system.websocketByNetty.NioWebSocketServer;

@Component
public class InitListener implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        RedisFlowcontrol.loadScript();

        System.out.println("my runner");
        NioWebSocketServer nioWebSocketServer = new NioWebSocketServer();
//        nioWebSocketServer.init();
    }
}
