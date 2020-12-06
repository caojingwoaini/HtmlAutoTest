package com.ssm.publicMethod;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SshMethod {
    /**
     * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
     *
     * @param host
     *            主机名
     * @param user
     *            用户名
     * @param psw
     *            密码
     * @param port
     *            端口
     * @param command
     *            命令
     * @return
     */
    public static String exec(String host, String user, String psw, int port, String command) {
        String result = "";
        Session session = null;
        ChannelExec openChannel = null;
        try {
            // String osName = System.getProperty("os.name");
            // System.out.println("os name:"+osName);
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
            openChannel = (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            // System.out.println(exitStatus);
            openChannel.connect();
            InputStream in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result += new String(buf.getBytes("gbk"), "UTF-8") + "  \r\n";
            }
        } catch (JSchException | IOException e) {
            result += e.getMessage();
        } finally {
            if (openChannel != null && !openChannel.isClosed()) {
                openChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return result;
    }

    public static void main(String args[]) {
//        String memory =exec("10.9.2.87", "root", "eichong", 22,
// "cd /home/app/tomcat/logs/\ngrep \"http://pay1.keytop.cn:8099/api/wec/SyncChargePilePay\" ./html_xixi.log.1 | grep 158571920948508041990");

//        String memory =exec("10.9.2.87", "root", "eichong", 22,
//                "cd /home/app/tomcat-evcs/logs\ngrep \"https://test-hlht-notify.uniev.com/evcs/v1.0.0/notification_start_charge_result\" html_evcs.log  | grep MA01H3BQ1202008271512262229");
//        System.out.println(memory);


//        String begin_charge_time="2020-09-10 14:53:19".substring(0,17)+"*";
//        String end_charge_time="2020-09-10 14:54:42".substring(0,17)+"*";
//        String cmd=String.format("sed -n \"/%s/,/%s/p\" %s |  grep \"%s\" | grep %s",begin_charge_time,end_charge_time,
//                MessageManager.getSystemProperties("EvcsLogAddress"),"https://test-hlht-notify.uniev.com/evcs/v1.0.0/notification_stationStatus",
//                "3301060019321445"+"0"+"1");
////        String cmd=String.format("grep \"%s\" %s  | grep %s","https://test-hlht-notify.uniev.com/evcs/v1.0.0/notification_equip_charge_status",
////        MessageManager.getSystemProperties("EvcsLogAddress"),"MA01H3BQ1202009101453117673");
//        String str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
//        MessageManager.getSystemProperties("EvcsSshUsername"),
//        MessageManager.getSystemProperties("EvcsSshPassword"),
//        Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
//        String str2=str.split("\n")[str.split("\n").length-1];
//        System.out.println(str2);
    }


}
