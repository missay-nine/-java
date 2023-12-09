package com.book.controller;

import com.book.pojo.BookInfo;
import com.book.pojo.ClassInfo;
import com.book.service.BookInfoService;
import com.book.service.impl.BookInfoServiceImpl;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import  javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/BookInfoServlet.do")
public class BookInfoServlet extends HttpServlet {

    BookInfoService bookInfoService = new BookInfoServiceImpl();

    protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html;charset=utf-8");
      req.setCharacterEncoding("UTF-8");
      String flag = req.getParameter("flag");
      PrintWriter out = resp.getWriter();
        if(flag.equals("add")) {
            book_add(req, out);
        }else if(flag.equals("query")){
            book_query(req, out);
        }else if(flag.equals("queryClass")) {
            book_queryClass(req, out);
        }else if(flag.equals("queryDetail")) {
            book_queryDeail(req, out);
        }else if(flag.equals("delete")){
            book_delete(req, out);
        }else if(flag.equals("findById")) {
            book_findById(req, out);
        }else if(flag.equals("edit")){
            // 修改图书信息接口
            book_edit(req,out);
        }

    }

    private void book_findById(HttpServletRequest req, PrintWriter out) {
        // 根据图书id查询
        String book_id = req.getParameter("book_id");
        BookInfo bookinfo = bookInfoService.findById(Long.valueOf(book_id));
        out.print(new Gson().toJson(bookinfo));
    }

    private void book_delete(HttpServletRequest req, PrintWriter out) {
        // 根据图书id删除
        String book_id = req.getParameter("book_id");
        //调用service 层的方法
        int result = bookInfoService.deleteById(Long.valueOf(book_id));
        if (result > 0){
            out.print("1");
        }else{
            out.print("0");
        }
    }

    private void book_queryDeail(HttpServletRequest req, PrintWriter out) {
        String book_id = req.getParameter("book_id");
        BookInfo bookinfo = bookInfoService.findById(Long.valueOf(book_id));
        out.print(new Gson().toJson(bookinfo));
    }

    private void book_queryClass(HttpServletRequest req, PrintWriter out) {
        List<ClassInfo> classInfoList = bookInfoService.findBookClass();
        Gson gson = new Gson();
        String json = gson.toJson(classInfoList);
        out.print(json);
    }

    private void book_query(HttpServletRequest req, PrintWriter out) {
        //  绑定参数 书名字
        String name = req.getParameter("name");
        // 后台输出
        System.out.println("name:"+name);
        // 调用service层的方法
        List<BookInfo> bookInfos = bookInfoService.queryBook(name);
        // 转换成json
        Gson  gson = new Gson();
        String json = gson.toJson(bookInfos);
        // 发送JSON
        out.print(json);
    }

    private void book_add(HttpServletRequest req, PrintWriter out) {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publish = req.getParameter("publish");
        String isbn = req.getParameter("isbn");
        String introduction = req.getParameter("introduction");
        String language = req.getParameter("language");
        String price = req.getParameter("price");
        String pubdate = req.getParameter("pubdate");
        String classId = req.getParameter("classId");
        String pressmark = req.getParameter("pressmark");

        // 封装对象
        BookInfo bookInfo = new BookInfo(name,author,publish,isbn,introduction,language,Double.valueOf(price),pubdate,
                Integer.valueOf(classId),Integer.valueOf(pressmark));
        int result = bookInfoService.addBook(bookInfo);
        if (result > 0){
            out.print("<script>alert('添加成功');location.href='/admin/allbooks.html';</script>");
        }else{
            out.print("<script>alert('添加失败');location.href='/admin/allbooks.html';</script>");
        }
    }

    private  void book_edit(HttpServletRequest req,PrintWriter out){
        String book_id = req.getParameter("book_id");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publish = req.getParameter("publish");
        String isbn = req.getParameter("isbn");
        String introduction = req.getParameter("introduction");
        String language = req.getParameter("language");
        String class_id = req.getParameter("class_id");
        String price = req.getParameter("price");
        String pubdate = req.getParameter("pubdate");
        String state = req.getParameter("state");
        String pressmark = req.getParameter("pressmark");

        BookInfo bookInfo = new BookInfo(name,author,publish,isbn,introduction,language,
                Double.valueOf(price),pubdate ,Integer.valueOf(class_id), Integer.valueOf(pressmark));
        int result = bookInfoService.updateBook(bookInfo);
        if (result>0){
            out.print("<script>alert('修改成功');location.href='/bookinfo.html';</script>");
        }else{
            out.print("<script>alert('修改失败');location.href='/bookinfo.html';</script>");
        }
    }

}
