package com.zy.library.repository;

import com.zy.library.entity.Book;
import com.zy.library.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    public List<Book> findByBookName(String bookName);

    public List<Book> findByBookAuthor(String bookAuthor);

    public List<Book> findByBookPress(String bookPress);

    public List<Book> findByBookSort(Integer sort);

    public List<Book> findByUser(SysUser user);

    public List<Book> findByBookNumber(String bookNumber);

}
