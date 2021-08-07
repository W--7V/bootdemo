//import org.openjdk.btrace.core.annotations.*;
//
//import static org.openjdk.btrace.core.BTraceUtils.println;
//
//@BTrace
//public class TestBtrace {
//
//    @OnMethod(clazz = "local.Running", method = "f1", location = @Location(Kind.RETURN))
//    public static void onF1(@Self Object object, String a, int b) {
//        println(a + "------" + b);
//    }
//}
