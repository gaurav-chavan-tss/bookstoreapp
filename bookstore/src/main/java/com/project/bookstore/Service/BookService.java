package com.project.bookstore.Service;

import com.project.bookstore.Model.Book;
import com.project.bookstore.Model.Response;
import com.project.bookstore.Repository.BookRepo;
import javax.persistence.EntityNotFoundException;

import com.project.bookstore.enums.OrderBy;
import com.project.bookstore.enums.SortBy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BookService implements IBookService{
    private final BookRepo _bookRepo;

    public BookService(BookRepo bookRepo)
    {
        _bookRepo=bookRepo;
    }

    @Override
    public Response<Book> addBook(Book book) {
        Response response = new Response<>();
        try{
            Book b = new Book();
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setIsbn(book.getIsbn());
            b.setPrice(book.getPrice());

            Optional<Book> IsAvail = Optional.ofNullable(_bookRepo.findByIsbn(b.getIsbn()));
            if(!IsAvail.isPresent())
            {
                   Book newB = _bookRepo.save(b);
                   response.Data = b;
                   response.Message = "Book Added Successfully";

            }
            else{
                throw new Exception("Sorry, can't add the book. Please check ISBN number again!");
            }
        }
        catch(Exception ex)
        {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    @Override
    public Response<Book> getBookByID(Long Id) {
        Response response = new Response<>();
        try{
            Book book = _bookRepo.findById(Id).orElseThrow(
                    () -> new EntityNotFoundException("Book with given ID doesn't exist"));
            response.Data = book;
            response.Message = "Book Found";
        } catch(Exception ex)
        {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
       return response;
    }



    @Override
    public Response<Page<Book>> getBooks(int page, int size, SortBy sortBy, OrderBy orderBy) {
        Response<Page<Book>> response = new Response<>();
        try {
            Sort.Direction direction = orderBy == OrderBy.desc ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size,direction, sortBy.getField());
            Page<Book> booksPage = _bookRepo.findAll(pageable);
            response.Data=booksPage;
            response.Message="Books found";
        } catch (Exception ex) {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }


//    public Response<List<Book>> getBooks() {
//        Response response = new Response<>();
//        try{
//            List books = _bookRepo.findAll();
//            response.Data=books;
//            response.Message = "Books found";
//        }catch(Exception ex)
//        {
//            response.Data = null;
//            response.Success = false;
//            response.Message = ex.getMessage();
//        }
//        return response;
//    }




    @Override
    public Response<Page<Book>> searchBook(String title,int page, int size,SortBy sortBy, OrderBy orderBy) {
        Response <Page<Book>> response = new Response<>();
        try{
            Sort.Direction direction = orderBy == OrderBy.desc ? Sort.Direction.DESC : Sort.Direction.ASC;
//            String searchTerm = "%" + title + "%";
            Pageable pageable = PageRequest.of(page, size, direction, sortBy.getField());
            Page<Book> bookPage = _bookRepo.searchByTitle(title, pageable);
            if(!bookPage.isEmpty())
            {
                response.Data=bookPage;
                response.Message = "Books with title found";
            }
            else{
                response.Message = "Book with given title not found";
            }

        }catch(Exception ex)
        {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    @Override
    public Response<Book> deleteBook(Long Id) {
        Response response = new Response<>();
        try{
           Optional<Book> bk = _bookRepo.findById(Id);
           if(bk.isPresent())
           {
               _bookRepo.deleteById(Id);
               response.Data = bk;
               response.Message = "Book deleted successfully";
           }
           else{
               response.Message = "Book with given id not found";
           }
        } catch(Exception ex){
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }

    @Override
    public Response<Book> updateBook(Long id, Book book) {
        Response response = new Response<>();
        try{
            Book b1 = _bookRepo.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Book with given id not found"));
            b1.setTitle(book.getTitle());
            b1.setAuthor((book.getAuthor()));
            b1.setIsbn(book.getIsbn());
            b1.setPrice(book.getPrice());
            _bookRepo.save(b1);
            if(_bookRepo.findByIsbn(b1.getIsbn())!=null){
                throw new Exception("ISBN number should be unique");
            }
            else{
                response.Data = b1;
                response.Message = "Book updated successfully!";
            }

        } catch(Exception ex)
        {
            response.Data = null;
            response.Success = false;
            response.Message = ex.getMessage();
        }
        return response;
    }




}
