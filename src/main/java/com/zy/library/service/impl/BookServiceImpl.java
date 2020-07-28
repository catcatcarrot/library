package com.zy.library.service.impl;

import com.zy.library.entity.Book;
import com.zy.library.entity.SysUser;
import com.zy.library.repository.BookRepository;
import com.zy.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable(cacheNames = "books",unless="#result==null || #result.size()==0")
    public List<Book> findByBookName(String bookName) {
        System.out.println("---名字---");
        return bookRepository.findByBookName(bookName);
    }

    @Cacheable(cacheNames = "books",unless="#result==null || #result.size()==0")
    public List<Book> findByBookAuthor(String bookAuthor) {
        System.out.println("---作者---");
        return bookRepository.findByBookAuthor(bookAuthor);
    }

    @Cacheable(cacheNames = "books",unless="#result==null || #result.size()==0")
    public List<Book> findByBookPress(String bookPress) {
        System.out.println("---出版社---");
        return bookRepository.findByBookPress(bookPress);
    }

    @Override
    public List<Book> findByBookSort(Integer sort) {
        return bookRepository.findByBookSort(sort);
    }

    @Override
    public List<Book> findBorrowBooksBySysUserId(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        return bookRepository.findByUser(sysUser);
    }

    @Override
    public boolean borrowBookByUserId(Long bookId, SysUser user) {
        Book oldBook = bookRepository.getOne(bookId);
        oldBook.setUser(user);

        Date borrowDate = new Date();
        Date returnDate = addDate(30);
        oldBook.setBookBorrowDate(borrowDate);
        oldBook.setBookReturnDate(returnDate);

        Book newBook = bookRepository.save(oldBook);
        return newBook.getBookId().equals(oldBook.getBookId());
    }

    public static Date addDate (int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date date = calendar.getTime();
        return date;
    }

    @Override
    public boolean returnBookByUserId(Long bookId, SysUser user) {
        Book oldBook = bookRepository.getOne(bookId);
        oldBook.setUser(user);
        oldBook.setBookBorrowDate(null);
        oldBook.setBookReturnDate(null);
        Book newBook = bookRepository.save(oldBook);
        return newBook.getBookId().equals(oldBook.getBookId());
    }

    @Override
    public List<Book> findByBookNumber(String bookNumber) {
        return bookRepository.findByBookNumber(bookNumber);
    }

    //jpa的分页从0开始
    public Page<Book> findAllBooks(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public void deleteBookById(Long bookId){
        bookRepository.deleteById(bookId);
    }

    @Override
    public void insertBook(Book book) {
        bookRepository.save(book);
    }
}
