package com.Servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.mysql.jdbc.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class DataProcessing {
	
	//�����ַ������ݣ�����һ���ַ����Զ��ŷָ�
	public String increase_string(String allUser,String user){   //��������������һ���Ǳ���ԭ�����ַ�������һ����Ҫ�����
		if(allUser == null || allUser.isEmpty()) {    //���ж�ԭ�����ַ����ǲ���Ϊ��
			return user;
		}else {
	        List<String> test = new ArrayList<String>();  //��Ҫ�Ƚ�һ���յ�new Array
	        test.addAll(Arrays.asList(allUser.split(",")));  //����asList�ֽ�����ַ�����new Array������ֽ����ַ�ȫ����ӽ�ȥ
	        if(test.contains(user)) {
	        	return allUser;                //�ж��Ƿ���ڸ��ַ�����������ڲ����
	        }else
	        for(int i=0;i<test.size();i++){
	      	 System.out.println(test.get(i));              //����test
	           }
	       test.add(user);                                 //��������ַ�
	       String increase_result = String.join(",", test);
	       return increase_result;
		}
	}
		
	//�����ַ������ݣ���ȥһ���ַ����Զ��ŷָ�
	public String reduce_string(String allUser,String user){  //��������������һ���Ǳ���ԭ�����ַ�������һ����Ҫ��ȥ��
		if(allUser == null || allUser.isEmpty()) 
		{	
			return allUser;
        }else {
            List<String> test = new ArrayList<String>();    //��Ҫ�Ƚ�һ���յ�new Array
            test.addAll(Arrays.asList(allUser.split(",")));    //����asList�ֽ�����ַ�����new Array������ֽ����ַ�ȫ����ӽ�ȥ
            for(int i=0;i<test.size();i++){
               if(test.get(i).equals(user)) {
              	 test.remove(i);    //����test,�������user,�����Ƴ�
              	 i--;              
               }                        
          }
                                    //ȥ��������ַ����������
            String increase_result = String.join(",", test);
            return increase_result;
    	   }
	}
	
	//���������
	 public  String getId(){
         UUID uuid = UUID.randomUUID();
         return uuid.toString().replace("-", "");
  }
	 
	 //���ݿ���ȡ����rusultSet����ת����json����
	 public String resultSetToJson(ResultSet rs) throws SQLException,JSONException
	    {
	       // json����
	       JSONArray array = new JSONArray();
	      
	       // ��ȡ����
	       ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
	       int columnCount = metaData.getColumnCount();
	      
	       // ����ResultSet�е�ÿ������
	        while (rs.next()) {
	            JSONObject jsonObj = new JSONObject();
	           
	            // ����ÿһ��
	            for (int i = 1; i <= columnCount; i++) {
	                String columnName =metaData.getColumnLabel(i);
	                String value = rs.getString(columnName);
	                jsonObj.put(columnName, value);
	            } 
	            array.add(jsonObj);
	        }
	      
	       return array.toString();
	    }
	 
	 //�ж��Զ���������ַ������Ƿ����ĳ���ַ���
	 public boolean judge_string (String str,String id)  {  
		 String a=str;
		 List<String> test=Arrays.asList(a.split(",")); 
		 if(test.contains(id)) {
			return true;
		 }else {
			return false;
		}
		 
	 }
	//�����Զ���������ַ������ַ�������
	public int count_string(String string){   //��������������һ���Ǳ���ԭ�����ַ�������һ����Ҫ�����

			String[] split=string.split(",");
			return split.length;
		}
	
	//��ȡ��ǰ����ʱ��
	public String get_time(){   //��������������һ���Ǳ���ԭ�����ַ�������һ����Ҫ�����
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		 return df.format(new Date());
	}
	
}
