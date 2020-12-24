package com.vilicode.controller;

import com.vilicode.bean.Book;
import com.vilicode.bean.BookType;
import com.vilicode.service.BookService;
import com.vilicode.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    public BookService bookService;

    @Autowired
    public BookTypeService bookTypeService;

    @RequestMapping("/index")
    public String Init(HttpServletRequest request)
    {
        List<Book> books=bookService.queryBookByRecommendType(1,1,6);
        if(books.size()>0)
            request.setAttribute("scrollBook",books.get(0));
        books=bookService.queryBookByRecommendType(2,1,6);
        request.setAttribute("hotList",books);
        books=bookService.queryBookByRecommendType(3,1,8);
        request.setAttribute("newList",books);
        List<BookType> bookTypes=bookTypeService.queryBookTypes();
        request.getServletContext().setAttribute("bookTypes",bookTypes);
        return "index";
    }
}
