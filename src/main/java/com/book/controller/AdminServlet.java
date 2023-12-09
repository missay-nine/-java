package com.book.controller;

import com.book.pojo.Admin;
import com.book.pojo.ReaderCard;
import com.book.service.AdminService;
import com.book.service.ReaderCardService;
import com.book.service.impl.AdminServiceImpl;
import com.book.service.impl.ReaderCardServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@WebServlet(value = "/AdminServlet.do")
public class AdminServlet extends HttpServlet {

    AdminService adminService = new AdminServiceImpl();
    ReaderCardService readerCardService = new ReaderCardServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置响应乱码
        resp.setContentType("text/html;charset=utf-8");
        //设置请求乱码
        req.setCharacterEncoding("UTF-8");
        //2.获取flag的值value值
        String flag = req.getParameter("flag");
        if (flag.equals("login")) {
            //用户登录
            login(req, resp);
        } else if ("getLoginName".equals(flag)) {
            HttpSession session = req.getSession();
            //获取session中的数据(登录的用户名)
            //session.setMaxInactiveInterval(30); //30秒有效期,  默认30分钟
            Object obj = session.getAttribute("loginName");
            System.out.println("获取用户的sesion:" + obj);
            PrintWriter out = resp.getWriter();
            if (obj != null) {
                String loginName = obj.toString();
                out.print(loginName);
            } else {
                //session失效
                out.print("");
            }
        }else if("logout".equals(flag)) {
            HttpSession session = req.getSession();
            //删除session数据
            //session.removeAttribute("loginName");
            //让session失效
            session.invalidate();
            //重定向到登录页面
            resp.sendRedirect("/login.html");
        }else if("getReaderId".equals(flag)) {
            // 获取读者的id
            HttpSession session = req.getSession();
            Object obj = session.getAttribute("id");
            PrintWriter out = resp.getWriter();
            if (obj != null) {
                String id = obj.toString();
                out.print(id);
            } else {
                out.print("");
            }
        }else if("changeAdminPassWord".equals(flag)) {
            // 修改管理员密码
            PrintWriter out = resp.getWriter();
            // 获取参数
            String oldPasswd = req.getParameter("oldPasswd");
            String newPasswd = req.getParameter("newPasswd");
            String reNewPass = req.getParameter("reNewPasswd");
            HttpSession session = req.getSession();
            String password = session.getAttribute("password").toString();
            Integer admin_id = Integer.valueOf(session.getAttribute("id").toString());
            if (password.equals(oldPasswd)) {
                // 旧密码正确
                if (newPasswd.equals(reNewPass)) {
                    // 两次新密码一致
                    int i = adminService.updatePassWord(admin_id, newPasswd);
                    if (i > 0) {
                        // 更改完成后，让session失效
                        session.invalidate();
                        // 创建Cookie对象
                        Cookie ucookie = new Cookie("ucookie", "");
                        ucookie.setMaxAge(0);
                        // 修改成功
                        out.print("<script>alert('修改成功');location.href='/login.html';</script>");
                    } else {
                        // 修改失败
                        out.print("<script>alert('修改失败');location.href='/admin/admin_change_password.html';</script>");
                    }
                } else {
                    // 两次新密码不一致
                    out.print("<script>alert('两次新密码不一致');location.href='/admin/admin_change_password.html';</script>");
                }
            }else{
                // 旧密码错误
                out.print("<script>alert('旧密码错误');location.href='/admin/admin_change_password.html';</script>");
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //用户登录
        String role = request.getParameter("role"); //登录用户角色
        String username = request.getParameter("username"); //账号
        String password = request.getParameter("password"); //密码
        String remember = request.getParameter("remember"); //记住我
        PrintWriter out = response.getWriter();

        if (role.equals("admin")) {
            //管理员
            //封装对象
            Admin admin = new Admin(Integer.valueOf(username), password);
            System.out.println(admin);
            //调用登录
            Admin loginAdmin = adminService.login(admin);
            System.out.println(loginAdmin);
            if (loginAdmin != null) {
                //创建Cookie对象
                rememberMe(response, role, username, password, remember);

                //获取用户的session存储区(当前登录用户的Session)
                HttpSession session = request.getSession();
                System.out.println("sessionID是:" + session.getId());
                //往session存数据(登录账号)
                session.setAttribute("loginName", loginAdmin.getAdmin_id());  // loginName=212121
                session.setAttribute("password", loginAdmin.getPassword());
                session.setAttribute("id", loginAdmin.getAdmin_id());

                //登录成功
                out.print("<script>alert('登录成功');location.href='/admin/admin_index.html';</script>");
            } else {
                //登录失败
                out.print("<script>alert('登录失败');location.href='/login.html';</script>");
            }
        }else{
            // 读者
            ReaderCard readerCard = new ReaderCard();
            readerCard.setReader_id(Integer.valueOf(username));
            readerCard.setPasswd(password);

            // 查询是否有
            ReaderCard loginCard = readerCardService.login(readerCard);
            if(loginCard!=null){

                //获取用户的session存储区(当前登录用户的Session)
                HttpSession session = request.getSession();
                //往session存数据(登录账号)
                session.setAttribute("loginName",loginCard.getName());  // loginName=小毛
                session.setAttribute("password",loginCard.getPasswd());
                session.setAttribute("id",loginCard.getReader_id());

                //记住我功能
                rememberMe(response, role, username, password, remember);
                //登录成功
                out.print("<script>alert('登录成功');location.href='/reader/reader_index.html';</script>");
            }else{
                //登录失败
                out.print("<script>alert('账号或密码错误');location.href='login.html';</script>");
            }
        }


    }
    private void rememberMe(HttpServletResponse response, String role, String username, String password, String remember) {
        try {
            // username@password@role@remember, Cookie设置值的时候中文编码
            Cookie ucookie = new Cookie("ucookie", URLEncoder.encode(username + "@" + password + "@" + role + "@" + remember, "UTF-8"));
            //cookie路径
            ucookie.setPath("/");
            if ("1".equals(remember)) {
                ucookie.setMaxAge(1 * 24 * 60 * 60);//内存存储，取值有三种：>0有效期，单位秒；=0立即失效；<0内存存储(浏览器一旦关闭立即失效)
                //把cookie写入浏览器
            } else {
                //不记住
                ucookie.setMaxAge(0);
            }
            response.addCookie(ucookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
    }


}
