package LoaderTest;

import myLang.String;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @program: JUC-demo
 * @description: ClassLoaderDemo
 * @author: zwh
 * @create: 2021-05-08 15:44
 **/
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
        URLClassLoader systemClassLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        URL[] urLs = systemClassLoader.getURLs();
        for (URL url: urLs) {
            System.out.println(url);
        }
    }
    public void test(){
        System.out.println("test");
    }
}
