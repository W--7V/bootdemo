package springboot.demo.system;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {

        System.out.println("MyListener thread:"+Thread.currentThread().getName());
    }
}
