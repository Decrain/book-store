package cn.edu.bistu.action;

import cn.edu.bistu.dao.BookDaoImp;
import cn.edu.bistu.entity.Book;
import cn.edu.bistu.util.SuperAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.List;


/**
 * Created by hongyu on 2016/12/23.
 */
public class BookAction extends SuperAction implements ModelDriven<Book>, Preparable {

    private static final long serialVersionUID = 1L;

    public String execute() throws Exception {
        System.out.println("execute");
        return SUCCESS;
    }

    public String add() throws Exception {
        System.out.println("add");
        BookDaoImp bookDaoImp = new BookDaoImp();
        if (bookDaoImp.addBook(book)) {
            List<Book> list = bookDaoImp.listBook();
            session.setAttribute("bookList", list);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String delete() throws Exception {
        System.out.println("delete");
        BookDaoImp bookDaoImp = new BookDaoImp();
        if (bookDaoImp.deleteBook(book)) {
            List<Book> list = bookDaoImp.listBook();
            session.setAttribute("bookList", list);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String update() throws Exception {
        System.out.println("update");
        System.out.println(book.toString());
        BookDaoImp bookDaoImp = new BookDaoImp();
        if (bookDaoImp.updateBook(book)) {
            List<Book> list = bookDaoImp.listBook();
            session.setAttribute("bookList", list);
            return "retrieve";
        } else {
            return ERROR;
        }
    }

    public String retrieve() throws Exception {
        System.out.println("retrieve");
        BookDaoImp bookDaoImp = new BookDaoImp();
        List<Book> list = bookDaoImp.listBook();
        session.setAttribute("bookList", list);
        return SUCCESS;
    }


    private Book book = new Book();

    public Book getModel() {
        return this.book;
    }

    public void prepare() throws Exception {
        System.out.println("prepare");
        BookDaoImp bookDaoImp = new BookDaoImp();
        List<Book> list = bookDaoImp.listBook();
        session.setAttribute("bookList", list);
    }
}
