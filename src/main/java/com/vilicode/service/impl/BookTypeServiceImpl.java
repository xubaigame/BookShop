package com.vilicode.service.impl;

import com.vilicode.bean.BookType;
import com.vilicode.bean.Page;
import com.vilicode.mapper.BookMapper;
import com.vilicode.mapper.BookTypeMapper;
import com.vilicode.service.BookService;
import com.vilicode.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    public BookMapper bookMapper;
    @Autowired
    public BookTypeMapper bookTypeMapper;

    @Override
    public List<BookType> queryBookTypes() {
        return bookTypeMapper.queryBookTypes();
    }

    @Override
    public Page queryBookByBookTypeID(int btid, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            if(btid==-1)
                totalCount=bookMapper.queryCountOfBooks();
            else
                totalCount = bookTypeMapper.queryCountOfBooksByTypeID(btid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        try {
            if(btid==-1)
                list=bookMapper.queryBooks((pageNumber-1)*8,8);
            else
                list = bookTypeMapper.queryBooksByBtid(btid,(pageNumber-1)*8,8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList(list);
        return p;
    }

    @Override
    public String queryBookTypeNameByBookTypeID(int btid) {
        return bookTypeMapper.queryBookTypeNameByBookTypeID(btid);
    }

    @Override
    public boolean addBookType(String btname) {
        try {
          int result=bookTypeMapper.identifyBtname(btname);
            if(result>=1)
                return false;
            bookTypeMapper.addBookType(btname);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }


    }

    @Override
    public boolean removeBookType(int btid) {
        try {
            bookMapper.deleteBookByBtid(btid);
            bookTypeMapper.deleteBookType(btid);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean modifyBookType(int btid, String btname) {
        try {
            bookTypeMapper.updateBookType(btid,btname);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
