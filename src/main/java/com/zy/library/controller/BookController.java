package com.zy.library.controller;

import com.zy.library.entity.Book;
import com.zy.library.entity.Msg;
import com.zy.library.entity.SysUser;
import com.zy.library.entity.SysUserMsg;
import com.zy.library.service.BookService;
import com.zy.library.service.SysUserService;
import com.zy.library.utils.LRUCache;
import com.zy.library.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class BookController {
    private static LRUCache bookHotSearch = new LRUCache(3);

    @Autowired
    private BookService bookService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/findByBookName")
    public Msg findBookByName(Integer page, Integer limit, String name){
        List<Book> books = bookService.findByBookName(name);

        //利用LRUCache做的热门书籍缓存
        books.forEach(book->bookHotSearch.put(book.getBookName(),book));

        //利用Redis做的热门书籍缓存
        books.forEach(book -> redisUtil.incrementScore("hotBooks",book,1));

        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("处理成功");
        msg.setData(books);
        return msg;
    }

    @RequestMapping("/findByBookAuthor")
    public Msg findBookByAuthor(Integer page, Integer limit, String author){
        List<Book> books = bookService.findByBookAuthor(author);
        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("处理成功");
        msg.setData(books);
        return msg;
    }

    @RequestMapping("/findByBookPress")
    public Msg findBookByPress(Integer page, Integer limit, @RequestParam("keyword") String press){
        List<Book> books = bookService.findByBookPress(press);
        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("处理成功");
        msg.setData(books);
        return msg;
    }

    @RequestMapping("/getBooksBySort")
    public Msg findBooksBySort(Integer page, Integer limit, Integer sort){
        List<Book> books = bookService.findByBookSort(sort);
        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("处理成功");
        msg.setData(books);
        return msg;
    }

    @RequestMapping("/findBorrowBooksByUserId")
    public Msg findBorrowBooksByBookUserId(Integer page, Integer limit, HttpSession session){
        SysUser user = ((SysUserMsg) session.getAttribute("userMsg")).getData();
        List<Book> books = bookService.findBorrowBooksBySysUserId(user.getUserId());
        System.out.println(books);
        Msg msg = new Msg();
        msg.setCode(0);
        msg.setMsg("处理成功");
        msg.setData(books);
        return msg;
    }

    //借书   创建一个 借书时间--计算出还书时间并插入
    @RequestMapping("/borrowBookByUserId")
    public Msg borrowBookByUserId(Long bookId, HttpSession session){
        SysUser user = ((SysUserMsg) session.getAttribute("userMsg")).getData();
        boolean result = bookService.borrowBookByUserId(bookId, user);
        if(result)
            return Msg.success();
        return Msg.fail();
    }

    //还书  借书时间、还书时间、book_user_id都要设为null
    @RequestMapping("/returnBookByUserId")
    public Msg returnBookByUserId(Long bookId){
        boolean result = bookService.returnBookByUserId(bookId, null);
        if(result)
            return Msg.success();
        return Msg.fail();
    }

    @RequestMapping("/findBookByNumber")
    public Msg findBookByNumber(Integer page, Integer limit, String bookNumber){
        if(bookNumber==null || bookNumber.trim().equals("")){
            return findAllBooks(page,limit);
        }

        List<Book> books = bookService.findByBookNumber(bookNumber);
        return Msg.success().addData(books);
    }

    @RequestMapping("/findAllBooks")
    public Msg findAllBooks(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit);
        Page<Book> pageBook = bookService.findAllBooks(pageable);
        long id = 1;
        id =limit*pageBook.getNumber()+1;

        List<Book> books = pageBook.getContent();
        for(Book book:books){
            book.setId(id++);
        }
        long count = pageBook.getTotalElements();
        Msg msg = new Msg();
        if(books.size()==0){
            msg.setCode(1);
            msg.setMsg("无数据");
        }else{
            msg.setCode(0);
            msg.setCount(count);
            msg.setData(books);
        }
        return msg;
    }

    @RequestMapping("/deleteBookById")
    public Msg deleteBookById(Long bookId){
        bookService.deleteBookById(bookId);
        return Msg.success();
    }

    @RequestMapping("/insertBook")
    public Msg insertBook(Book book){
        bookService.insertBook(book);
        return Msg.success();
    }

    @RequestMapping("/getHotBooks")
    public List<Book> getHotBooks(){
        System.out.println("~~~~~getHotBooks~~~~~");

        List<Book> hotBooks=new ArrayList<>();
        for(Map.Entry<String, Book> entry:bookHotSearch.entrySet()){
            hotBooks.add(0,entry.getValue());
        }

        return hotBooks;
    }

    @RequestMapping("/getHotBooksByRedis")
    public Set<Object> getHotBooksByRedis(){
        System.out.println("~~~~~getHotBooks  Redis~~~~~");

        Set<Object> hotBooks = redisUtil.reverseRange("hotBooks", 0, 2);

        return hotBooks;
    }

}
