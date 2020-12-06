package com.ssm.publicMethod;

import java.io.InputStream;
import java.util.Properties;

public class MessageManager {

    /**
     * 系统配置文件
     */
    private static Properties sysProperties = null;

    static {
        // 加载属性文件
        try {
            //系统配置文件
            InputStream inputStreamSys=MessageManager.class.getClassLoader().
                    getResourceAsStream("system.properties");
            sysProperties = new Properties();
            sysProperties.load(inputStreamSys);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 构造方法
     */
    public MessageManager() {

    }


    /**
     * 根据key取得系统设置文件中的信息
     *
     * String  messageKey
     * @return propertiesMap properties文件信息
     */
    public static String getSystemProperties(String messageKey) {
        // 信息内容
        String message ;
        // 信息内容
        message = sysProperties.getProperty(messageKey);
        if (message == null) {
            // 将KEY设置成值
            message = messageKey;
        }
        // 返回信息内容
        return message;
    }



//    public static void main(String[] args){
//        String msg;
//        msg=MessageManager.getSystemProperties("EvcsLogName");
//        System.out.println(msg);
//    }
}
