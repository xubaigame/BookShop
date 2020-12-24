package com.vilicode.service.impl;

import com.vilicode.bean.Book;
import com.vilicode.bean.Page;
import com.vilicode.mapper.BookMapper;
import com.vilicode.mapper.RecommendMapper;
import com.vilicode.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    public BookMapper bookMapper;
    @Autowired
    public RecommendMapper recommendMapper;
    @Override
    public List<Book> queryBookByRecommendType(int rtype,int pageNumber,int pageSize)
    {
        return recommendMapper.queryBookByRecommendType(rtype,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public Page queryBookByRecommendType(int rtype, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            if(rtype==0)
                totalCount =bookMapper.queryCountOfBooks();
            else
                totalCount = recommendMapper.queryRecommendCountOfBooksByTypeID(rtype);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List<Book> list=null;
        try {
            if(rtype==0)
                list=bookMapper.queryBooks((pageNumber-1)*8,8);
            else
                list = recommendMapper.queryBookByRecommendType(rtype,(pageNumber-1)*8,8);

            for(int i=0;i<list.size();i++)
            {
                Book book=list.get(i);
                book.setScroll(recommendMapper.queryBookByRtypeAndBid(1,book.getBid())>=1);
                book.setHot(recommendMapper.queryBookByRtypeAndBid(2,book.getBid())>=1);
                book.setNew(recommendMapper.queryBookByRtypeAndBid(3,book.getBid())>=1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList((List) list);
        return p;
    }

    public Book queryBookByID(int bid)
    {
        return bookMapper.queryBookById(bid);

    }

    public Page searchBooksByKeyword(String keyword,int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = bookMapper.queryCountOfBooksByKeyword(keyword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        try {
            list = bookMapper.queryBooksByKeyword(keyword,(pageNumber-1)*8,8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList(list);
        return p;
    }

    @Override
    public boolean addRecommend(int bid, int rtype) {
        try {
            recommendMapper.addRecommendBook(bid,rtype);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remoteRecommend(int bid, int rtype) {
        try {
            recommendMapper.removeRecommendBook(bid,rtype);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeBookByBid(int bid) {
        try {
            recommendMapper.removeRecommend(bid);
            bookMapper.deleteBookByBtid(bid);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addBook(Book book) {
        try {
            bookMapper.addBook(book);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean modifyBook(Book book) {
        try {
            bookMapper.modifyBook(book);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
