package LoaderTest;

import java.io.*;

/**
 * @program: JUC-demo
 * @description: 自定义classLoader
 * @author: zwh
 * @create: 2021-05-08 18:05
 **/
public class HotClassLoader extends ClassLoader{
    private String root;
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 如果是java开始，则使用JVM自带的类加载器加载
        if(name.startsWith("java")){
            return getSystemClassLoader().loadClass(name);
        }
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
}
