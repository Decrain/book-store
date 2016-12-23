package cn.edu.bistu.dao;

import cn.edu.bistu.entity.Book;
import cn.edu.bistu.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyu on 2016/12/23.
 */
public class BookDaoImp {
    /* Method to CREATE an book in the database */
    @Test
    public void addBook() {
        Book book = new Book(88 ,"9787302448082","Hibernate实战（第2版）","Christian Bauer, Gavin King","清华大学出版社");
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Integer i = (Integer) session.save(book);
            System.out.println("new book id: " + i);
            flag = i>0;
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to  READ all the books */
    @Test
    public void listBook() {
        List<Book> list = new ArrayList<Book>();
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "from Book";
            Query query = session.createQuery(hql);
            list = query.list();
            if (list.size() > 0) {
                System.out.println("listBook success! " + list.size());
            } else {
                System.out.println("listBook failure!");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println(list.toString());
    }

    /* Method to UPDATE salary for an book */
    @Test
    public void updateBook(){
        Book book = new Book(88 ,"9787302448082","Hibernate实战（第2版）","Christian Bauer, Gavin King","清华大学出版社");
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "update Book set isbn = :isbn, price = :price, author = :author, press=:press where bookId = :bookId";
            Query query = session.createQuery(hql);
            query.setParameter("isbn", book.getIsbn());
            query.setParameter("price", book.getPrice());
            query.setParameter("author", book.getAuthor());
            query.setParameter("press", book.getPress());
            query.setParameter("bookId", book.getBookId());
            int i = query.executeUpdate();
            tx.commit();//提交事务
            if (i > 0) {
                flag = true;
                System.out.println("updateBook success");
            } else {
                System.out.println("updateBook failure");
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an book from the records */
    @Test
    public void deleteBook() {
        Book book = new Book(88 ,"9787302448082","Hibernate实战（第2版）","Christian Bauer, Gavin King","清华大学出版社");
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "delete from Book where bookId = :bookId";
            Query query = session.createQuery(hql);
            query.setParameter("bookId", book.getBookId());
            int i = query.executeUpdate();
            tx.commit();//提交事务
            if (i > 0) {
                flag = true;
                System.out.println("deleteBook success");
            } else {
                System.out.println("deleteBook failure");
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
