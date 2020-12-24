package com.vilicode.mapper;

import com.vilicode.bean.Book;
import com.vilicode.bean.BookType;

import java.util.List;

public interface BookTypeMapper {
    public List<BookType> queryBookTypes();
    public List<Book> queryBooksByBtid(int btid, int pageIndex,int pageSize);//按btid查询书籍
    public int queryCountOfBooksByTypeID(int btid);//根据推荐类型查询总数
    public String queryBookTypeNameByBookTypeID(int btid);
    public void addBookType(String btname);
    public void updateBookType(int btid,String btname);
    public void deleteBookType(int btid);
    public int identifyBtname(String btname);
}
