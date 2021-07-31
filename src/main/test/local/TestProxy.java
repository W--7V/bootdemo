package local;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class TestProxy {

	public static void main(String[] args) {
		IA newProxyInstance = (IA) Proxy.newProxyInstance(TestProxy.class.getClassLoader(), new Class[] {IA.class}, new ProxyInvoker());
		newProxyInstance.f1();
	}
	
}

interface IA{
	void f1();
}

class CA implements IA{

	@Override
	public void f1() {
		
	}
}

class ProxyInvoker implements InvocationHandler{
	CA ca = new CA();

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("pre");
		method.invoke(ca, args);
		return null;
	}
	
}