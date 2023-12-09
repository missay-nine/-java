package com.book.controller;


import com.book.pojo.LendList;
import com.book.pojo.ReaderCard;
import com.book.service.BookInfoService;
import com.book.service.LendService;
import com.book.service.ReaderCardService;
import com.book.service.impl.BookInfoServiceImpl;
import com.book.service.impl.LendServiceImpl;
import com.book.service.impl.ReaderCardServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/LendServlet.do")
public class LendServlet extends HttpServlet {
  LendService lendService = new LendServiceImpl();
  BookInfoService bookInfoService = new BookInfoServiceImpl();
  ReaderCardService readerCardService = new ReaderCardServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //请求POST请求乱码处理
        req.setCharacterEncoding("UTF-8");
        String flag = req.getParameter("flag"); //新增状态
        PrintWriter out = resp.getWriter();

        if("lendBook".equals(flag)){
            // 好像没有检测卡号状态
            String book_id = req.getParameter("book_id");
            String readerId = req.getParameter("readerId");
            // 先通过readerId 判断是否被封号
            ReaderCard readerCard = readerCardService.findById(Long.valueOf(readerId));
            Integer statusInteger = readerCard.getCard_state();
            int status = statusInteger != null ? statusInteger : 0; // 这里假设为null时赋予默认值0
            System.out.println(status);
            if (status == 0){
                out.print("<script>alert('该卡号已被封号');location.href='/reader/lendbook.html?bookId="+book_id+"';</script>");
                return;
            }
              //封装对象
            LendList lendList=new LendList(Long.valueOf(book_id),Long.valueOf(readerId));
             //新增数据
            int result = lendService.addLend(lendList);
            if (result>0){
                // 嗲用对象
                bookInfoService.updateBookState(Integer.valueOf(book_id), 0);
                out.print("<script>alert('图书借阅成功');location.href='/reader/reader_query_books.html';</script>");

            }else{
                out.print("<script>alert('系统异常，稍后再试');location.href='/reader/lendbook.html?bookId="+book_id+"';</script>");
            }

        }else if("queryMyLend".equals(flag)){
             // 查询记录  获取session
            HttpSession session = req.getSession();
            String reader_id = session.getAttribute("id").toString();
            List<LendList> lendLists=lendService.queryMyLendBooks(Long.valueOf(reader_id));
            out.print(new Gson().toJson(lendLists));
        }else if("queryList".equals(flag)){
            //查询所有的借阅记录
            String reader_id = req.getParameter("reader_id");
            List<LendList> lendLists=lendService.queryLendList(reader_id);
            out.print(new Gson().toJson(lendLists));
        }else if("backBook".equals(flag)){
            //管理员还书
            String book_id = req.getParameter("book_id");
            String sernum = req.getParameter("sernum");
            int count=lendService.adminBackBook(book_id,sernum);
            if(count>0){
                out.print("1");
            }else{
                out.print("0");
            }
        }else if("deleteBookRecord".equals(flag)){
            String sernum = req.getParameter("sernum");
            int count=lendService.deleteBookRecord(sernum);
            if(count>0){
                out.print("1");
            }else{
                out.print("0");
            }
        }
    }
    }
