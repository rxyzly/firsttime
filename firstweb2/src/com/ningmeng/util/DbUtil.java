/**
 * 连接数据库并测试是否连接成功

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
    private String driver="com.mysql.jdbc.Driver";//加载mysql驱动
    
    public Connection getCon() throws Exception{
        Class.forName(driver);
        Connection con=DriverManager.getConnection(url, user, password);//连接数据库
        return con;
    }
    
    public static void getClose(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }
    
   public static void main(String[] args) {  //测试连接
        DbUtil db=new DbUtil();
        try {
            db.getCon();
            System.out.println("测试连接数据库，连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("测试连接数据库，连接失败");
        }
        
    }
}