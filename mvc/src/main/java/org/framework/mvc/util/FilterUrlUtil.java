package org.framework.mvc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterUrlUtil {

    public static boolean match(String str , String regEx){
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 查找字符串中是否有匹配正则表达式的字符/字符串
        boolean rs = matcher.find();

        if(str.length() != regEx.length()){
            if (!(regEx.endsWith("*") || regEx.endsWith("/"))){
                rs = false;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        System.out.println(match("/a/adaf","/a/"));
    }

}
