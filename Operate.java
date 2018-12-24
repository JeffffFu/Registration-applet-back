package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.metadata.IIOInvalidTreeException;

import com.mysql.jdbc.Statement;




public class Operate {
  
   //��ӱ�������
   public boolean add_match(Match ee) throws IOException{
       String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       PreparedStatement pstmt=null;
       try{
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="insert into match_information (uuid,theme,time,address,rule,color,people,remarks,sponsor,user_join,user_leave)"
           		+ "values(?,?,?,?,?,?,?,?,?,?,?)";
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, ee.Uuid);
           pstmt.setString(2, ee.Theme);
           pstmt.setString(3, ee.Time);
           pstmt.setString(4, ee.Address);
           pstmt.setString(5, ee.Rule);
           pstmt.setString(6, ee.Color);
           pstmt.setString(7, ee.People);
           pstmt.setString(8, ee.Remarks);
           pstmt.setString(9, ee.Sponsor);
           pstmt.setString(10, ee.User_join);
           pstmt.setString(11, ee.User_leave);
           pstmt.execute();
             }catch(ClassNotFoundException e){
                 e.printStackTrace();
             }catch(SQLException e){
                 e.printStackTrace();
             }     
     
       return true;

  }
   
   //�����û����еı����ֶ�����ѯ��ӦID�ı���
   public  ResultSet inquire_match(String user_match) throws ClassNotFoundException, SQLException {
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       Statement stmt=null;
       Statement stmt1=null;
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql1="UPDATE  match_status  SET match_status='3' WHERE STR_TO_DATE(match_time,'%Y-%m-%d %H:%i:%s') < NOW()";
           String sql="SELECT * FROM (SELECT * FROM  match_information WHERE FIND_IN_SET(id,'"+user_match+"')) AS a LEFT JOIN (SELECT * FROM match_status WHERE openid='ovmMc5LNugu4cL005ff5uGG9COjM') AS b ON a.uuid=b.status_uuid order by a.id desc";    
           stmt=(Statement) conn.createStatement();
           stmt.executeUpdate(sql1);
           ResultSet rs=stmt.executeQuery(sql);
           return  rs;
           
           
   }
   
   //�����û���Ϣ����
   public boolean add_user(User aa) throws IOException{
       String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       PreparedStatement pstmt=null;
       try{
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="insert into user(openid,user_name,user_url)values(?,?,?)";
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1, aa.Openid);
           pstmt.setString(2, aa.User_name);
           pstmt.setString(3, aa.User_url);
           pstmt.execute();
             }catch(ClassNotFoundException e){
                 e.printStackTrace();
             }catch(SQLException e){
                 e.printStackTrace();
             }  
 
      
       return true;
  }
   
   //����openid��ȡ�����û������ƣ��Դ��жϻ�ȡ����Openid�Ƿ������ݿ��Ѿ�����
   public  ResultSet inquire_openid(String openid) throws ClassNotFoundException, SQLException {
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       Statement stmt=null;
       
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="select user_name from user where openid = '"+openid+"'";
           stmt=(Statement) conn.createStatement();
           ResultSet rs=stmt.executeQuery(sql);
           return  rs;    

           
   }
   
   //���¸�openid���û����ֺ�ͷ��
   public  int update_user(User bb) throws ClassNotFoundException, SQLException {
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       Statement stmt=null;    
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="update user set user_name='"+bb.User_name+"',user_url='"+bb.User_url+"'  where openid='"+bb.Openid+"'";
           stmt=(Statement) conn.createStatement();
           int result=stmt.executeUpdate(sql);
           return  result;
           
   }
   
   //����openid�ҵ��û�����Ϣ
   public  ResultSet find_userId(String openid) throws ClassNotFoundException, SQLException {
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       Statement stmt=null;
       
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="select * from user where openid='"+openid+"'";
           stmt=(Statement) conn.createStatement();
           ResultSet userId=stmt.executeQuery(sql);
           return  userId;
      
   }
   
    //����uuidȡ���������е�������Ϣ��
   public  ResultSet find_matchInformation(String uuid) throws ClassNotFoundException, SQLException {
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       Statement stmt=null;
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="select * from match_information where uuid='"+uuid+"'";
           stmt=(Statement) conn.createStatement();
           ResultSet allUser=stmt.executeQuery(sql);
           return  allUser;
      
   }
   
      //���ⳡ���������ֶκ�����ֶ����У��µõ����ַ����滻ԭ�����ַ���
    public  int update_join(String uuid,String rs_join,String rs_leave) throws ClassNotFoundException, SQLException { //��������������һ���Ǳ�����openid,�ҵ��ⳡ������һ�����µı����ֶ��ַ���
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
       String username = "root";//���ݿ��˻���һ��Ϊroot
       String password = "15606081231";//���ݿ�����
       Connection conn=null;
       Statement stmt=null;    
           //������������
           Class.forName("com.mysql.jdbc.Driver");
           //������ݿ�����
           conn=(Connection)DriverManager.getConnection(url,username,password);
           String sql="update match_information set user_join='"+rs_join+"',user_leave='"+rs_leave+"' where uuid='"+uuid+"'";
           stmt=(Statement) conn.createStatement();
           int result=stmt.executeUpdate(sql);
           return  result;
           
   }
    
    //��match�����У�ȡ�������˵�������ͷ��
    public ResultSet register_information (String user_join) throws ClassNotFoundException, SQLException { //��������������һ���Ǳ�����openid,�ҵ��ⳡ������һ�����µı����ֶ��ַ���
		   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
	       String username = "root";//���ݿ��˻���һ��Ϊroot
	       String password = "15606081231";//���ݿ�����
	       Connection conn=null;
	       Statement stmt=null;
	           //������������
	           Class.forName("com.mysql.jdbc.Driver");
	           //������ݿ�����
	           conn=(Connection)DriverManager.getConnection(url,username,password);
	           String sql="select user_name,user_url from user where FIND_IN_SET (id,'"+user_join+"')";
	           stmt=(Statement) conn.createStatement();
	           ResultSet register_imformation=stmt.executeQuery(sql);      
	           return register_imformation;
	           
	   }

    
    //��match�����У�ȡ������˵�������ͷ��
    public ResultSet leave_information (String user_leave) throws ClassNotFoundException, SQLException { //��������������һ���Ǳ�����openid,�ҵ��ⳡ������һ�����µı����ֶ��ַ���
		   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
	       String username = "root";//���ݿ��˻���һ��Ϊroot
	       String password = "15606081231";//���ݿ�����
	       Connection conn=null;
	       Statement stmt=null;
	           //������������
	           Class.forName("com.mysql.jdbc.Driver");
	           //������ݿ�����
	           conn=(Connection)DriverManager.getConnection(url,username,password);
	           String sql="select user_name,user_url from user where FIND_IN_SET (id,'"+user_leave+"')";
	           stmt=(Statement) conn.createStatement();
	           ResultSet leave_imformation=stmt.executeQuery(sql);      
	           return leave_imformation;
	           
	   }

    //���û����еı����ֶν��и���
    public  int update_userMatch(String new_userMatch,String openid) throws ClassNotFoundException, SQLException { //��������������һ�����µı����ֶ�,һ�����û���id
	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
     String username = "root";//���ݿ��˻���һ��Ϊroot
     String password = "15606081231";//���ݿ�����
     Connection conn=null;
     Statement stmt=null;    
         //������������
         Class.forName("com.mysql.jdbc.Driver");
         //������ݿ�����
         conn=(Connection)DriverManager.getConnection(url,username,password);
         String sql="update user set `match`='"+new_userMatch+"' where openid ='"+openid+"' ";
         stmt=(Statement) conn.createStatement();
         int result=stmt.executeUpdate(sql);
         return  result;
         
 }

    //��match_status�в������״̬�ͱ���ʱ��
    public boolean add_matchStatus(String uuid,String openid,String status,String time) throws IOException{
        String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
        String username = "root";//���ݿ��˻���һ��Ϊroot
        String password = "15606081231";//���ݿ�����
        Connection conn=null;
        PreparedStatement pstmt=null;
        try{
            //������������
            Class.forName("com.mysql.jdbc.Driver");
            //������ݿ�����
            conn=(Connection)DriverManager.getConnection(url,username,password);
            String sql="insert into match_status(status_uuid,openid,match_status,match_time)values(?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, uuid);
            pstmt.setString(2, openid);
            pstmt.setString(3, status);
            pstmt.setString(4, time);
            pstmt.execute();
              }catch(ClassNotFoundException e){
                  e.printStackTrace();
              }catch(SQLException e){
                  e.printStackTrace();
              }  
  
       
        return true;
   }

    //����match_status�б�����Ӧ�û���״̬
    public  int update_matchStatus(String uuid,String openid,String status) throws ClassNotFoundException, SQLException {
 	   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
        String username = "root";//���ݿ��˻���һ��Ϊroot
        String password = "15606081231";//���ݿ�����
        Connection conn=null;
        Statement stmt=null;    
            //������������
            Class.forName("com.mysql.jdbc.Driver");
            //������ݿ�����
            conn=(Connection)DriverManager.getConnection(url,username,password);
            String sql="update match_status set match_status='"+status+"' where openid='"+openid+"'and status_uuid='"+uuid+"'";
            stmt=(Statement) conn.createStatement();
            int result=stmt.executeUpdate(sql);
            return  result;
            
    }

    //��match_status�� ȡ����Ӧ״̬
    public ResultSet inquire_matchStatus (String uuid,String openid) throws ClassNotFoundException, SQLException { //��������������һ���Ǳ�����openid,�ҵ��ⳡ������һ�����µı����ֶ��ַ���
		   String url = "jdbc:mysql://localhost:3306/match?useSSL=false";
	       String username = "root";//���ݿ��˻���һ��Ϊroot
	       String password = "15606081231";//���ݿ�����
	       Connection conn=null;
	       Statement stmt=null;
	           //������������
	           Class.forName("com.mysql.jdbc.Driver");
	           //������ݿ�����
	           conn=(Connection)DriverManager.getConnection(url,username,password);
	           String sql="select match_status from match_status where status_uuid='"+uuid+"' and openid='"+openid+"'";
	           stmt=(Statement) conn.createStatement();
	           ResultSet leave_imformation=stmt.executeQuery(sql);      
	           return leave_imformation;
	           
	   }
}


