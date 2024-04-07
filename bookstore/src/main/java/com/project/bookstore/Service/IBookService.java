package com.project.bookstore.Service;

import com.project.bookstore.Model.Book;
import com.project.bookstore.Model.Response;
import com.project.bookstore.enums.OrderBy;
import com.project.bookstore.enums.SortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    public Response<Book> addBook(Book book);
    public Response<Book> getBookByID(Long Id);


    Response<Page<Book>> getBooks(int page, int size, SortBy sortBy, OrderBy orderBy);

    public Response<Page<Book>> searchBook(String title,int page, int size,SortBy sortBy, OrderBy orderBy );

    public Response<Book> deleteBook(Long Id);
    public Response<Book> updateBook(Long id, Book book);


}
