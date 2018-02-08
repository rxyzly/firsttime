/**
 * 获得数据，对数据库操作进行封装

 *
 * @author mengjian
 */
package com.ningmeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ningmeng.model.User;

public class UserDao {
    
    public User login(Connection con,User user) throws SQLException{
    	User resultUser=null;
        String sql="select * from user where username=? and password=?";//sql语句，搜索这个username和password在数据库是否存在
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs=ps.executeQuery();	//运行sql语句，并把得到的结果放入结果集ResultSet中
        
        if(rs.next()){ //判断这个结果集是否存在，一般username只有一个
            resultUser=new User();
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}