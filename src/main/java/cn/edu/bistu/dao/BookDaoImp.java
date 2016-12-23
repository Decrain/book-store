package cn.edu.bistu.dao;

import cn.edu.bistu.entity.Book;
import cn.edu.bistu.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongyu on 2016/12/23.
 */
public class BookDaoImp implements BookDao {
    /* Method to CREATE an book in the database */
    public boolean addBook(Book book) {
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
            //session.close();
        }
        return flag;
    }

    /* Method to  READ all the books */
    public List<Book> listBook() {
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
            //session.close();
        }
        return list;
    }

    /* Method to UPDATE salary for an book */
    public boolean updateBook(Book book){
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "update Book set isbn = :isbn, name = :name,price = :price, author = :author, press=:press where bookId = :bookId";
            Query query = session.createQuery(hql);
            query.setParameter("isbn", book.getIsbn());
            query.setParameter("name", book.getName());
            query.setParameter("price", book.getPrice());
            query.setParameter("author", book.getAuthor());
            query.setParameter("press", book.getPress());
            query.setParameter("bookId", book.getBookId());
            int i = query.executeUpdate();
            tx.commit();
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
            //session.close();
        }
        return flag;
    }

    /* Method to DELETE an book from the records */
    public boolean deleteBook(Book book) {
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "delete from Book where bookId = :bookId";
            Query query = session.createQuery(hql);
            query.setParameter("bookId", book.getBookId());
            int i = query.executeUpdate();
            tx.commit();
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
            //session.close();
        }
        return flag;
    }
}
