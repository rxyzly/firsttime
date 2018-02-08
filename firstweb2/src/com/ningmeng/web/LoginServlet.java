package com.ningmeng.web;
//web����ʹ��Servlet�����ݽ��д���Ͳ���

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
    private static final long serialVersionUID = 1L;//serialVersionUID   ����������Ĳ�ͬ�汾��ļ����ԡ�

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");//�����ȡlogin.jsp���û���username��ֵ
        String password=request.getParameter("password");//�����ȡlogin.jsp������password��ֵ
        Connection con=null;
        try {
            User user=new User(username,password);
            con=db.getCon();
            User currentUser=userDao.login(con, user);//�ȶ����ݿ��˻�����Ϳͻ����Ƿ�һ�¡�
            if(currentUser==null){
                request.setAttribute("error", "�û��������������"); //����һ��error,��������ָ������error�����ڵ�½������棬request������������
                request.setAttribute("username", username); //����һ��username��������username�����ݸ�ֵ��ǰ��һ��username�����ڵ�½�ɹ�����
                request.setAttribute("password", password);//����һ��password��������password�����ݸ�ֵ��ǰ��һ��password�����ڵ�½�ɹ�����
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                HttpSession session=request.getSession();//��ȡ session
                session.setAttribute("currentUser",currentUser);//����currentUser��������
                response.sendRedirect("main.jsp");//��ת����½�ɹ�����
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
}