/**
 * �������ݿⲢ�����Ƿ����ӳɹ�

 * 
 * @author mengjian
 */
package com.ningmeng.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    
    private String url="jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String user="root";
    private String password="123456";
    private String driver="com.mysql.jdbc.Driver";//����mysql����
    
    public Connection getCon() throws Exception{
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url, user, password);//�������ݿ�
        return con;
    }
    
    public static void getClose(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }
    
   public static void main(String[] args) {  //��������
        DbUtil db=new DbUtil();
        try {
            db.getCon();
            System.out.println("�����������ݿ⣬���ӳɹ�");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("�����������ݿ⣬����ʧ��");
        }
        
    }
}