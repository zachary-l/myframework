package org.framework.beans.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScanUtil {

    //获取当前项目的绝对路径
    private final static String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("/","\\").substring(1);
    //定义一个list集合，用于存放所有类的完整类名
    private static List<String> list = new ArrayList<>();

    /**
     * 扫描的入口方法
     */
    public static List<String> scan(String pathNames){
        String pathName = changePathName(pathNames);
        readFile(path+pathName);
        return list;
    }

    /**
     * 读取文class件信息
     */
    private static void readFile(String path){
        //构建文件对象
        File f = new File(path);
        File[] files = f.listFiles();
        if(files != null){
            for (File file : files) {
                if(file.isFile()){
//                    System.out.println(file.getAbsolutePath());
                    //如果是文件，就要进行文件名的解析
                    String className = resolveClass(file.getAbsolutePath());
                    list.add(className);
                }else{
                    //如果是目录，那么就执行递归，继续遍历目录
                    readFile(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 文件解析
     */
    private static String resolveClass(String classPath){
        String className = classPath.substring(path.length(), classPath.length());
        className = className.replace(".class", "").replace("\\", ".");
        return className;
    }
    private static String changePathName(String pathNames){
        pathNames = pathNames.replace(".","\\");
        return pathNames;
    }

    public static void main(String[] args) {
        List<String> list =scan("org.framework.beans");
        for (String l:list){
            System.out.println(l);
        }
//        System.out.println(path);
    }
}
