package com.zy.library.service;

import com.zy.library.entity.Book;
import com.zy.library.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    public List<Book> findByBookName(String bookName);

    public List<Book> findByBookAuthor(String bookAuthor);

    public List<Book> findByBookPress(String bookPress);

    public List<Book> findByBookSort(Integer sort);

    public List<Book> findBorrowBooksBySysUserId(Long sysUserId);

    public boolean borrowBookByUserId(Long bookId, SysUser user);

    public boolean returnBookByUserId(Long bookId, SysUser user);

    public List<Book> findByBookNumber(String bookNumber);

    public Page<Book> findAllBooks(Pageable pageable);

    public void deleteBookById(Long bookId);

    public void insertBook(Book book);

}
