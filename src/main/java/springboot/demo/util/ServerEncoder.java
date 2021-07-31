package springboot.demo.util;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ServerEncoder implements Encoder.Text<String>  {
    //代表websocket调用sendObject方法返回客户端的时候，必须返回的是DomainResponse对象
    @Override
    public String encode(String s) {
        //将java对象转换为json字符串
        return s;
    }
    @Override
    public void init(EndpointConfig endpointConfig) { }

    @Override
    public void destroy() { }
}
