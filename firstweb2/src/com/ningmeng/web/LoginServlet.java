package com.ningmeng.web;
//web包，使用Servlet对数据进行处理和操作

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ningmeng.dao.UserDao;
import com.ningmeng.model.User;
import com.ningmeng.util.DbUtil;

public class LoginServlet extends HttpServlet{

    DbUtil db=new DbUtil();
    UserDao userDao=new UserDao();
    /**
     * 
     */
    private static final long serialVersionUID = 1L;//serialVersionUID   用来表明类的不同版本间的兼容性。

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");//请求获取login.jsp的用户名username的值
        String password=request.getParameter("password");//请求获取login.jsp的密码password的值
        Connection con=null;
        try {
            User user=new User(username,password);
            con=db.getCon();
            User currentUser=userDao.login(con, user);//比对数据库账户密码和客户端是否一致。
            if(currentUser==null){
                request.setAttribute("error", "用户名或者密码错误"); //设置一个error,将后面的字赋给这个error，用于登陆错误界面，request的作用域有限
                request.setAttribute("username", username); //设置一个username，将后面username其内容赋值给前面一个username，用于登陆成功界面
                request.setAttribute("password", password);//设置一个password，将后面password其内容赋值给前面一个password，用于登陆成功界面
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                HttpSession session=request.getSession();//获取 session
                session.setAttribute("currentUser",currentUser);//传递currentUser对象属性
                response.sendRedirect("main.jsp");//跳转到登陆成功界面
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}