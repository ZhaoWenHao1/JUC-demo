package LoaderTest;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @program: JUC-demo
 * @description: MyClassLoader 加载String
 * @author: zwh
 * @create: 2021-05-08 16:17
 **/
public class MyClassLoader extends ClassLoader {
    private String root;
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);

        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }
    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar +
                className.replace('.', File.separatorChar) + ".class";
        try {
            InputStream ins = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = 0;
            while ((length = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public static void main(String[] args) throws Exception {

        MyClassLoader classLoader = new MyClassLoader();
        classLoader.setRoot("F:\\workspace\\JUC-demo\\src\\main\\java");
        Class<?> clz = classLoader.findClass("myLang.String");
        Object instance = clz.newInstance();
        Method test = clz.getDeclaredMethod("test");
        test.setAccessible(true);
        test.invoke(instance);
        System.out.println(instance.getClass().getClassLoader());

    }
}