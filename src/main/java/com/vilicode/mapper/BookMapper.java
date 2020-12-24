package com.vilicode.mapper;

import com.vilicode.bean.Book;

import java.util.List;

public interface BookMapper {
    public void addBook(Book book);             //添加一本书籍
    public void removeBook(int bid);            //删除一本书籍
    public void modifyBook(Book book);          //修改一本书籍
    public List<Book> queryBooks(int pageIndex,int pageSize);             //查询全部书籍
    public int queryCountOfBooks();
    public List<Book> queryBooksByKeyword(String keyword, int pageIndex,int pageSize);             //查询全部书籍
    public int queryCountOfBooksByKeyword(String keyword);
    public Book queryBookById(int bid);         //按bid查询一本书籍
    public Book queryBookByIsbn(String bisbn);  //按bisbn查询一本书籍
    public void deleteBookByBtid(int btid);
}

