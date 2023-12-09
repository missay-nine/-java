package com.book.controller;

import com.book.pojo.ReaderCard;
import com.book.pojo.ReaderInfo;
import com.book.service.ReadInfoService;
import com.book.service.ReaderCardService;
import com.book.service.impl.ReadInfoServiceImpl;
import com.book.service.impl.ReaderCardServiceImpl;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ReaderInfoServlet.do")
public class ReadInfoServlet extends HttpServlet {

    ReadInfoService readInfoService = new ReadInfoServiceImpl();
    ReaderCardService readerCardService = new ReaderCardServiceImpl();

    protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html;charset=utf-8");
       req.setCharacterEncoding("UTF-8");
       String flag = req.getParameter("flag");
       //定义输出
        PrintWriter out = resp.getWriter();
        // 下面是不同的方法 1 添加读者 2查找读者id  3查找所有的用户 4 查找用户通过id 5编辑用户 6 更新密码 7查找读者个人信息 8更新个人信息
       if (flag.equals("add")){
           reader_add(req,out);
       }else if(flag.equals("checkReaderId")) {
           reader_checkId(req, out);
       }else if(flag.equals("query")) {
           reader_query(req, out);
       }else if(flag.equals("findById")) {
           reader_findById(req, out);
       }else if(flag.equals("edit")) {
           // 修改读者信息接口
           reader_edit(req, out);
       }else if("updatePass".equals(flag)){
              //修改密码
              updatePass(req, out,resp);
       }else if("queryMyInfo".equals(flag)) {
           //查询读者个人信息
           queryReaderInfo(req, out);
       }else if("readerInfoUpdate".equals(flag)) {
           //更新读者个人信息 这个是读者自己更新
           readerInfoUpdate(req, out);
       }else if("queryCard".equals(flag)) {
          // 查询读者卡信息
              queryCard(req, out);
       }else if("limitCard".equals(flag)){
              //限制读者卡
              limitCard(req, out);
       }else if("unLimitCard".equals(flag)){
                //解除限制
                unLimitCard(req, out);
       }else if("getReaderStatus".equals(flag)){
            // 查询读者状态
           getReaderStatus(req,out);
       }
    }

    private void getReaderStatus(HttpServletRequest req, PrintWriter out) {
        HttpSession session = req.getSession();
        String reader_id = session.getAttribute("id").toString();
        System.out.print(reader_id);
        ReaderCard readerCard = readerCardService.findById(Long.valueOf(reader_id));
        if (readerCard == null){
            // 说明不存在
            out.print("0");
        }
        int  Status = readerCard.getCard_state();
        if (Status == 0){
            // 表示已封号
            // 让当前session失效
            session.invalidate();
            // 创建Cookie对象
            Cookie ucookie = new Cookie("ucookie", "");
            ucookie.setMaxAge(0);
            out.print("0");
        }
        out.print("1");

    }

    private void unLimitCard(HttpServletRequest req, PrintWriter out) {
        String reader_id = req.getParameter("reader_id");
        int result = readerCardService.unLimitCard(Long.valueOf(reader_id));
        System.out.print(result);
        if (result>0){
            // 解除成功
            out.print("1");
        }else{
            // 解除失败
            out.print("0");
        }
    }

    private void limitCard(HttpServletRequest req, PrintWriter out) {
        String reader_id = req.getParameter("reader_id");
        int result = readerCardService.limitCard(Long.valueOf(reader_id));
        System.out.print(result);
        if (result>0){
            // 封号成功
            out.print("1");
        }else{
            // 解除失败
            out.print("0");
        }
    }

    private void queryCard(HttpServletRequest req, PrintWriter out) {
        List<ReaderCard> readerCard  = readerCardService.queryCard();
        // 将里面的密码信息隐藏
        for (ReaderCard card : readerCard) {
            card.setPasswd("******");
        }
        Gson gson = new Gson() ;
        String json = gson.toJson(readerCard);
        out.print(json);
    }

    private void readerInfoUpdate(HttpServletRequest req, PrintWriter out) {
       String readerId = req.getParameter("readerId");
       String name = req.getParameter("name");
       String sex =  req.getParameter("sex");
       String birth =   req.getParameter("birth");
       String address = req.getParameter("address");
       String telcode = req.getParameter("telcode");

       // 封装对象

        ReaderInfo readerInfo=new ReaderInfo(Long.valueOf(readerId),name,sex,birth,address,telcode);

        int result = readInfoService.updateReader(readerInfo);
        System.out.print(result);
        if(result>0){
            out.print("<script>alert('个人信息更新成功');location.href='/reader/reader_info.html';</script>");
        }else{
            out.print("<script>alert('个人信息更新失败');location.href='/reader/reader_info_edit.html';</script>");
        }
    }

    private void queryReaderInfo(HttpServletRequest req, PrintWriter out) {
        // 查询读者个人信息
        HttpSession session = req.getSession();
        String reader_id = session.getAttribute("id").toString();
        ReaderInfo readerInfo = readInfoService.findById(Long.valueOf(reader_id));
        out.print(new Gson().toJson(readerInfo));
    }

    private void updatePass(HttpServletRequest req, PrintWriter out,HttpServletResponse resp) {
     String oldPasswd = req.getParameter("oldPasswd");
     String newPasswd = req.getParameter("newPasswd");
     String reNewPasswd = req.getParameter("reNewPasswd");

     // 有好几个判断注意了 看你自己怎么判断了
        if(newPasswd.equals(reNewPasswd)){
            //原密码验证
            HttpSession session = req.getSession();
            String password= session.getAttribute("password").toString();
            String reader_id= session.getAttribute("id").toString();
            if(password.equals(oldPasswd)){
                //原密码输入正确
                int count = readInfoService.updatePass(Integer.valueOf(reader_id),newPasswd);
                if(count > 0){
                    //session失效
                    session.invalidate();

                    //清除的cookie
                    Cookie ucookie=new Cookie("ucookie","hello...");
                    ucookie.setMaxAge(0); //立即失效
                    resp.addCookie(ucookie);

                    out.print("<script>alert('密码修改成功！');parent.location.href='/login.html';</script>");
                }else{
                    out.print("<script>alert('密码修改异常！');location.href='/login.html';</script>");
                }
            }else{
                out.print("<script>alert('原密码输入错误！');location.href='/reader/reader_repasswd.html';</script>");
            }
        }else{
            out.print("<script>alert('两次输入密码不一致！');location.href='/reader/reader_repasswd.html';</script>");
        }
    }

    private void reader_edit(HttpServletRequest req, PrintWriter out) {
        String readerId = req.getParameter("readerId");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String birth = req.getParameter("birth");
        String address = req.getParameter("address");
        String telcode = req.getParameter("telcode");

        // 封装对象
        ReaderInfo readerInfo = new ReaderInfo(Long.valueOf(readerId),name,sex,birth,address,telcode);
        // 调用方法
        int result = readInfoService.updateReader(readerInfo);
        if (result>0){
            out.print("<script>alert('修改成功');location.href='/admin/allreaders.html';</script>");
        }else{
            out.print("<script>alert('修改失败');location.href='/admin/reader_edit.html?readerId="+readerId+"';</script>");
        }

    }

    private void reader_findById(HttpServletRequest req, PrintWriter out) {
      String readerId = req.getParameter("readerId");
      ReaderInfo readerInfo =  readInfoService.findById(Long.valueOf(readerId));
      out.print(new Gson().toJson(readerInfo));
    }

    private void reader_query(HttpServletRequest req, PrintWriter out) {
        // 查询所有的读者
        List<ReaderInfo> readerInfoList = readInfoService.findReaders();
        Gson gson = new Gson();
        String json = gson.toJson(readerInfoList);
        out.print(json);
    }

    private void reader_checkId(HttpServletRequest req, PrintWriter out) {
        // 查找id 是否存在 为前台做校验
        String readerId = req.getParameter("readerId");
        // 打印到控制台
        System.out.println("readerId:"+readerId);
        ReaderInfo readerInfo = readInfoService.findById(Long.valueOf(readerId));
        if (readerInfo != null){
            // 说明存在
            out.print("1");
        }else{
            // 说明不存在
            out.print("0");
        }
    }

    private void reader_add(HttpServletRequest req, PrintWriter out) {
       String readerId = req.getParameter("readerId");
       String name = req.getParameter("name");
       String sex = req.getParameter("sex");
       String birth = req.getParameter("birth");
       String address = req.getParameter("address");
       String telcode = req.getParameter("telcode");

       //登录的关键好像是卡号 读者表并不存取用户名和密码
        ReaderInfo readerInfo = new ReaderInfo(Long.valueOf(readerId), name, sex, birth, address, telcode);
        int result = readInfoService.addReader(readerInfo);
        if (result>0){
            out.print("<script>alert('添加成功');location.href='/admin/allreaders.html';</script>");
        }else{
            out.print("<script>alert('添加失败');location.href='/admin/reader_add.html';</script>");
        }

    }
}
