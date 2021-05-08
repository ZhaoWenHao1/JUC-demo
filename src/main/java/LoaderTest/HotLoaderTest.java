package LoaderTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: JUC-demo
 * @description: 热部署测试
 * @author: zwh
 * @create: 2021-05-08 18:03
 **/
public class HotLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, InterruptedException {
        while(true){
            MyClassLoader classLoader = new MyClassLoader();
            classLoader.setRoot("F:\\workspace\\JUC-demo\\src\\main\\java");
            Class<?> clz = classLoader.findClass("LoaderTest.SayHello");
            Object instance = clz.newInstance();
            Method test = clz.getDeclaredMethod("say");
            test.setAccessible(true);
            test.invoke(instance);
            Thread.sleep(2000);
        }
    }
}
