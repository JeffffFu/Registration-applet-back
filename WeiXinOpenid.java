package com.Servlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeiXinOpenid {

	public static String connectionUrl(String path,String data) {
    	String openId="";
        try {
            URL url = new URL(path);
            //�򿪺�url֮�������
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //����ʽ
//          conn.setRequestMethod("POST");
//           //����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 
            //�����Ƿ���httpUrlConnection����������Ƿ��httpUrlConnection���룬���ⷢ��post�����������������
            //��õ�Http�����޷���get��post��get������Ի�ȡ��̬ҳ�棬Ҳ���԰Ѳ�������URL�ִ����棬���ݸ�servlet��
            //post��get�� ��֮ͬ������post�Ĳ������Ƿ���URL�ִ����棬���Ƿ���http����������ڡ�
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            //�����������������
            out.print(data);
            //��������
            out.flush();
            //��ȡURLConnection�����Ӧ��������
            InputStream is = conn.getInputStream();new WeiXinOpenid();
            //����һ���ַ�������
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
            	openId=str;
                System.out.println(str);
            }
            //�ر���
            is.close();
            //�Ͽ����ӣ����д�ϣ�disconnect���ڵײ�tcp socket���ӿ���ʱ���жϡ�������ڱ������߳�ʹ�þͲ��жϡ�
            //�̶����̵߳Ļ��������disconnect�����ӻ����ֱ࣬���շ�������Ϣ��д��disconnect������һЩ��
            conn.disconnect();
            System.out.println("��������");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openId;
	}
	
	 public static String GetOpenID(String appid,String appsecret,String Code) {
	    	//��ʱ��¼ƾ֤
	    	String URL = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecret+"&js_code="+Code+"&grant_type=authorization_code";
	    	String openId=connectionUrl(URL, "");
	    	return openId;
	    }

	
	
}
