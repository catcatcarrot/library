package com.zy.library.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sys_user")
public class SysUser{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	@Column(name="user_name",length = 20)
	private String userName;
	@Column(name="user_number",length = 20)
	private String userNumber;
	@Column(name="user_password",length = 20)
	private String userPassword;

	@OneToMany(targetEntity = Book.class,fetch = FetchType.EAGER)
	@JoinColumn(name="book_user_id",referencedColumnName = "user_id")
	private Set<Book> books = new HashSet<>();

	@ManyToMany(targetEntity = SysRole.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name="sys_user_role",
			joinColumns = { @JoinColumn(name="sys_user_id",referencedColumnName = "user_id") },
			inverseJoinColumns = {@JoinColumn(name="sys_role_id",referencedColumnName = "role_id")}
	)
	private Set<SysRole> roles = new HashSet<>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}
}
