package com.zy.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="book")
@Proxy(lazy = false)
public class Book {

    //仅仅作为前端序号使用，无实际意义
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long bookId;
    @Column(name = "book_name",length = 20)
    private String bookName;
    @Column(name = "book_author",length = 20)
    private String bookAuthor;
    @Column(name = "book_press",length = 20)
    private String bookPress;
    @Column(name = "book_number",length = 20)
    private String bookNumber;
    @Column(name="book_sort")
    private Integer bookSort;
    @Column(name="book_region")
    private String bookRegion;
    @Column(name = "book_borrow_date")
    private Date bookBorrowDate;
    @Column(name = "book_return_date")
    private Date bookReturnDate;

    /*
     @JsonIgnore
     JPA 在双向映射时，会相互包含对方的实例，相互引用，造成递归迭代，
     堆栈溢出（java.lang.StackOverflowError）
     */
    @ManyToOne(targetEntity = SysUser.class)
    @JoinColumn(name="book_user_id",referencedColumnName = "user_id")
    @JsonIgnore
    private SysUser user;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPress() {
        return bookPress;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Date getBookBorrowDate() {
        return bookBorrowDate;
    }

    public void setBookBorrowDate(Date bookBorrowDate) {
        this.bookBorrowDate = bookBorrowDate;
    }

    public Date getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(Date bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Integer getBookSort() {
        return bookSort;
    }

    public void setBookSort(Integer bookSort) {
        this.bookSort = bookSort;
    }

    public String getBookRegion() {
        return bookRegion;
    }

    public void setBookRegion(String bookRegion) {
        this.bookRegion = bookRegion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPress='" + bookPress + '\'' +
                ", bookNumber='" + bookNumber + '\'' +
                ", bookSort=" + bookSort +
                ", bookRegion='" + bookRegion + '\'' +
                ", bookBorrowDate=" + bookBorrowDate +
                ", bookReturnDate=" + bookReturnDate +
                '}';
    }
}
