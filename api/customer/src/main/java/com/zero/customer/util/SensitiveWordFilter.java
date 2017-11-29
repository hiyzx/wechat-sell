package com.zero.customer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 敏感词过滤
 */
@Component
public class SensitiveWordFilter {

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveWordFilter.class);

    private static Pattern pattern = null;

    /**
     * 从sensitivewords.properties初始化正则表达式字符串
     */
    @PostConstruct
    private static void initPattern() {
        StringBuilder patternBuf = new StringBuilder("");
        try {
            InputStream in = SensitiveWordFilter.class.getClassLoader()
                    .getResourceAsStream("sensitivewords.properties");
            if (in != null) {
                Properties pro = new Properties();
                pro.load(in);
                Enumeration<?> enu = pro.propertyNames();
                patternBuf.append("(");
                while (enu.hasMoreElements()) {
                    patternBuf.append((String) enu.nextElement()).append("|");
                }
                patternBuf.deleteCharAt(patternBuf.length() - 1);
                patternBuf.append(")");

                // unix换成UTF-8
                pattern = Pattern.compile(patternBuf.toString());
                // win下换成gb2312
                // pattern = Pattern.compile(new String(patternBuf.toString()
                // .getBytes("ISO-8859-1"), "gb2312"));
            } else {
                LOGGER.info("敏感词 job : sensitivewords.properties 文件没有找到！");
            }
        } catch (IOException ioEx) {
            LOGGER.error("sensitivewords.properties：敏感词初始化异常...", ioEx);
        }
    }

    /**
     * 返回不敏感的词
     */
    public static String doCharacter(String str) {
        if (pattern != null) {
            Matcher m = pattern.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 返回敏感的词,以字符串的形式返回(特殊符号进行隔开)
     */
    public static String doUnCharacter(String str, String reg) {
        String sbstr = "";
        if (pattern != null) {
            Matcher m = pattern.matcher(str);
            int start = 0;
            int i = 0;
            while (m.find(start)) {
                if (!"".equals(sbstr)) {
                    sbstr += reg + str.substring(m.start(), m.end());
                } else {
                    sbstr = str.substring(m.start(), m.end());
                }
                start = m.end();
                i++;
            }
        }
        return sbstr;
    }

    /**
     * 返回敏感的词,以集合的形式返回
     */
    public static List<Object> doUnCharacterSet(String str) {
        List<Object> strList = new ArrayList<>();
        if (pattern != null) {
            Matcher m = pattern.matcher(str);
            int start = 0;
            int i = 0;
            while (m.find(start)) {
                strList.add(str.substring(m.start(), m.end()));
                start = m.end();
                i++;
            }
        }
        return strList;
    }

    /**
     * 是否存在敏感字符(存在:true ,不存在 false)
     */
    public static boolean existsCharacter(String str) {
        if (pattern != null) {
            Matcher m = pattern.matcher(str);
            return m.find();
        }
        return false;
    }

    /**
     * 用*替代敏感字符
     */
    public static String doReplaceCharacterWithStar(String str) {
        return doReplaceCharacter(str, "**");
    }

    /**
     * 用特殊符号，替代敏感字符
     */
    public static String doReplaceCharacter(String str, String reg) {
        if (pattern != null) {
            Matcher m = pattern.matcher(str);
            str = m.replaceAll(reg);
        }
        return str;
    }
}
