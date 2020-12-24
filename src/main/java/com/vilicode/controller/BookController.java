package com.vilicode.controller;

import com.vilicode.bean.Book;
import com.vilicode.bean.Page;
import com.vilicode.bean.User;
import com.vilicode.mapper.RecommendMapper;
import com.vilicode.service.BookService;
import com.vilicode.service.BookTypeService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Controller
public class BookController {

    @Autowired
    public BookService bookService;
    @Autowired
    public BookTypeService bookTypeService;


    @RequestMapping("/recommend_books")
    public String showRecommendBook(int rtype, int pageNumber, HttpServletRequest request)
    {
        if(pageNumber<=0)
            pageNumber=1;
        Page p= bookService.queryBookByRecommendType(rtype,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = bookService.queryBookByRecommendType(rtype,p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("t", rtype);
        return "recommend_list";
    }

    @RequestMapping("/booktypes_list")
    public String showBooksByBookTypeID(int pageNumber, int btid,HttpServletRequest request)
    {
        String btname="";
        if(btid!=0)
        {
            btname=bookTypeService.queryBookTypeNameByBookTypeID(btid);
        }
        request.setAttribute("t",btname);
        //List<Goods> list=gService.selectGoodsByTypeID(id,1,8);
        //request.setAttribute("goodsList",list);
        if(pageNumber<=0)
            pageNumber=1;
        Page p=bookTypeService.queryBookByBookTypeID(btid,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p=bookTypeService.queryBookByBookTypeID(btid,p.getTotalPage());
            }
        }

        request.setAttribute("p",p);
        request.setAttribute("btid",btid);
        return "booktypes_list";
    }

    @RequestMapping("/book_detail")
    public String showBookByID(int bid,HttpServletRequest request)
    {
        Book book= bookService.queryBookByID(bid);
        request.setAttribute("book",book);
        return "book_detail";
    }

    @RequestMapping("/search_books")
    public String SearchBooksByKeyword(int pageNumber,String keyword,HttpServletRequest request) throws UnsupportedEncodingException {

        if(pageNumber<=0)
        {
            pageNumber=1;
        }
        Page p =bookService.searchBooksByKeyword(keyword,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p =bookService.searchBooksByKeyword(keyword,p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword,"utf-8"));
        return "book_search";
    }

    @RequestMapping("/admin/type_list")
    public String ShowBookTypes(HttpServletRequest request)
    {
        request.setAttribute("list",bookTypeService.queryBookTypes());
        return "admin/type_list";
    }
    @RequestMapping("/admin/type_add")
    public String CreateBookType(String btname,HttpServletRequest request)
    {
        boolean result= bookTypeService.addBookType(btname);
        UpdateBookType(request);
        return "redirect:type_list.action";
    }
    @RequestMapping("/admin/type_delete")
    public String RemoveBookType(int btid,HttpServletRequest request)
    {
        boolean result= bookTypeService.removeBookType(btid);
        UpdateBookType(request);
        return "redirect:type_list.action";
    }

    @RequestMapping("/admin/type_update")
    public String ModifyBookType(int btid,String btname,HttpServletRequest request)
    {
        boolean result= bookTypeService.modifyBookType(btid,btname);
        UpdateBookType(request);
        return "redirect:type_list.action";
    }

    public void UpdateBookType(HttpServletRequest request)
    {
        if(request.getServletContext().getAttribute("bookTypes")==null)
        {
            request.getServletContext().setAttribute("bookTypes",bookTypeService.queryBookTypes());
        }
        else
        {
            request.getServletContext().removeAttribute("bookTypes");
            request.getServletContext().setAttribute("bookTypes",bookTypeService.queryBookTypes());
        }
    }

    @RequestMapping("/admin/book_list")
    public String ShowBookByRecommend(int pageNumber,int rtype,HttpServletRequest request)
    {
        if(pageNumber<=0)
            pageNumber=1;
        Page p = bookService.queryBookByRecommendType(rtype, pageNumber);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = bookService.queryBookByRecommendType(rtype, p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("rtype", rtype);
        return "/admin/book_list";
    }
    @RequestMapping("/admin/book_change")
    public String ChangeBookRecommend(int bid,int rtype,String method,int page)
    {
        boolean result=false;
        if(method.equals("add")) {
             result=bookService.addRecommend(bid,rtype);
        }else {
            result=bookService.remoteRecommend(bid,rtype);
        }
       return  "redirect:book_list.action?pageNumber=1&rtype="+page;
    }

    @RequestMapping("/admin/book_delete")
    public String DeleteBook(int bid,int rtype)
    {
        boolean result=bookService.removeBookByBid(bid);
        //
        return  "redirect:book_list.action?pageNumber=1&rtype="+rtype;
    }

    @RequestMapping("/admin/book_add")
    public String uploadimg(HttpServletRequest request) throws Exception{
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            Book b = new Book();
            for(FileItem item:list) {
                if(item.isFormField()) {
                    switch(item.getFieldName()) {
                        case "bname":
                            b.setBname(item.getString("utf-8"));
                            break;
                        case "bprice":
                            b.setBprice(Double.parseDouble(item.getString("utf-8")));
                            break;
                        case "bmark":
                            b.setBmark(item.getString("utf-8"));
                            break;
                        case "bstock":
                            b.setBstock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "btid":
                           b.setBtid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "bisbn":
                            b.setBisbn(item.getString("utf-8"));
                            break;
                        case "bauthor":
                            b.setBauthor(item.getString("utf-8"));
                            break;
                        case "bpublisher":
                            b.setBpublisher(item.getString("utf-8"));
                            break;
                    }
                }else {
                    if(item.getInputStream().available()<=0)continue;
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    String path = request.getServletContext().getRealPath("/images")+fileName;
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    switch(item.getFieldName()) {
                        case "bcover":
                            b.setBcover("images"+fileName);
                            break;
                        case "bimage1":
                            b.setBimage1("images"+fileName);
                            break;
                        case "bimage2":
                            b.setBimage2("images"+fileName);
                            break;
                    }
                }
            }
            bookService.addBook(b);
        } catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }

        return  "redirect:book_list.action?pageNumber=1&rtype=0";

    }

    @RequestMapping("/admin/book_edit_show")
    public String ShowBookByBid(int bid,HttpServletRequest request)
    {
        Book b = bookService.queryBookByID(bid);
        request.setAttribute("g", b);
        return "admin/book_edit";
    }

    @RequestMapping("/admin/book_update")
    public String ModifyBook(HttpServletRequest request) throws Exception {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            Book b=new Book();
            for(FileItem item:list) {
                if(item.isFormField()) {
                    switch(item.getFieldName()) {
                        case "bid":
                            b.setBid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "bname":
                            b.setBname(item.getString("utf-8"));
                            break;
                        case "bprice":
                            b.setBprice(Double.parseDouble(item.getString("utf-8")));
                            break;
                        case "bmark":
                            b.setBmark(item.getString("utf-8"));
                            break;
                        case "bstock":
                            b.setBstock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "btid":
                            b.setBtid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "bisbn":
                            b.setBisbn(item.getString("utf-8"));
                            break;
                        case "bauthor":
                            b.setBauthor(item.getString("utf-8"));
                            break;
                        case "bpublisher":
                            b.setBpublisher(item.getString("utf-8"));
                            break;
                        case "bcover":
                            b.setBcover(item.getString("utf-8"));
                            break;
                        case "bimage1":
                            b.setBimage1(item.getString("utf-8"));
                            break;
                        case "bimage2":
                            b.setBimage2(item.getString("utf-8"));
                            break;
                    }
                }else {
                    if(item.getInputStream().available()<=0)continue;
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    String path = request.getServletContext().getRealPath("/images")+fileName;
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    switch(item.getFieldName()) {
                        case "bcover":
                            b.setBcover("images"+fileName);
                            break;
                        case "bimage1":
                            b.setBimage1("images"+fileName);
                            break;
                        case "bimage2":
                            b.setBimage2("images"+fileName);
                            break;
                    }
                }
            }
            bookService.modifyBook(b);
            //Service.update(g);
            //request.getRequestDispatcher("/admin/goods_list?pageNumber="+pageNumber+"&type="+type).forward(request, response);
        } catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }

        return  "redirect:book_list.action?pageNumber=1&rtype=0";
    }
}
