package cn.edu.bistu.dao;

import cn.edu.bistu.entity.Book;

import java.util.List;

/**
 * Created by hongyu on 2016/12/23.
 */
public interface BookDao {
    /* Method to CREATE an book in the database */
    public boolean addBook(Book book);

    /* Method to  READ all the books */
    public List<Book> listBook();

    /* Method to UPDATE salary for an book */
    public boolean updateBook(Book book);

    /* Method to DELETE an book from the records */
    public boolean deleteBook(Book book);
}