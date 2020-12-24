package com.vilicode.controller;

import com.vilicode.bean.Book;
import com.vilicode.bean.Order;
import com.vilicode.bean.Page;
import com.vilicode.bean.User;
import com.vilicode.service.BookService;
import com.vilicode.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    public BookService bookService;
    @Autowired
    public OrderService orderService;

    @RequestMapping(value = "/books_buy")
    public void AddBookToCart(@RequestParam("bid") int bid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order o = null;
        if(request.getSession().getAttribute("order") != null) {
            o = (Order) request.getSession().getAttribute("order");
        }else {
            o = new Order();
            o.setOamount(0);
            o.setOtotal(0.0);
            request.getSession().setAttribute("order", o);
        }
        Book book = bookService.queryBookByID(bid);
        if(book.getBstock()>0) {
            o.addGoods(book);
            response.getWriter().print("ok");
        }else {
            response.getWriter().print("fail");
        }
    }
    @RequestMapping(value = "/books_lessen")
    public void LessenBookToCart(@RequestParam("bid") int bid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        o.lessen(bid);
        response.getWriter().print("ok");
    }

    @RequestMapping(value = "/books_delete")
    public void DeleteBookToCart(@RequestParam("bid") int bid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        o.delete(bid);
        response.getWriter().print("ok");
    }

    @RequestMapping(value = "/order_confirm")
    public String OrderConfirm( int opaytype,HttpServletRequest request)
    {
        Order o = (Order) request.getSession().getAttribute("order");
        o.setOstatus(2);
        o.setOpaytype(opaytype);
        User user=(User)request.getSession().getAttribute("user");
        o.setUid(user.getUid());
        o.setOrealname(user.getUrealname());
        o.setOphone(user.getUphone());
        o.setOaddress(user.getUaddress());
        boolean result=orderService.addOrder(o);
        if(result)
        {
            request.getSession().removeAttribute("order");
            request.setAttribute("msg", "订单支付成功！");
            return "order_result";
        }
        else
        {
            request.setAttribute("failmsg", "订单支付失败！");
            return "order_result";
        }

    }
    @RequestMapping("/order_submit")
    public String OrderSubmit(HttpServletRequest request)
    {
        if(request.getSession().getAttribute("user")!=null) {
            return "order_submit";
        }else {
            request.setAttribute("failMsg", "请登录后，再提交订单！");
            return "redirect:user_login.jsp";
        }
    }

    @RequestMapping("order_list")
    public String ShowOrderByUid(HttpServletRequest request)
    {
        User user=(User)request.getSession().getAttribute("user");
        if(user==null)
        {
            return "redirect:/index.action";
        }
        List<Order> orderList=orderService.queryOrderByUid(user.getUid());
        request.setAttribute("orderList", orderList);
        return "order_list";
    }

    @RequestMapping("/admin/order_list")
    public String ShowOrderList(int pageNumber,int ostatus,HttpServletRequest request)
    {
        request.setAttribute("ostatus", ostatus);
        if(pageNumber<=0)
            pageNumber=1;
        Page p = orderService.queryOrdersByOstatus(ostatus,pageNumber);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = orderService.queryOrdersByOstatus(ostatus,p.getTotalPage());
            }
        }

        request.setAttribute("p", p);
        return "admin/order_list";
    }

    @RequestMapping("/admin/order_status_change")
    public String ChangeOrderStatusByOid(String oid,int ostatus,HttpServletRequest request)
    {
        boolean result= orderService.updateOrderStatus(oid,ostatus);
        return "redirect:order_list.action?pageNumber=1&ostatus="+ostatus;
    }

    @RequestMapping("/admin/order_delete")
    public String DeleteOrderByOid(String oid,int ostatus,HttpServletRequest request)
    {
        boolean result= orderService.deleteOrderByOid(oid);
        return "redirect:order_list.action?pageNumber=1&ostatus="+ostatus;
    }

}
