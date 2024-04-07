package com.project.bookstore.Controller;

import com.project.bookstore.Model.Book;
import com.project.bookstore.Model.Response;
import com.project.bookstore.Service.IBookService;
import com.project.bookstore.enums.OrderBy;
import com.project.bookstore.enums.SortBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/books")
public class BookController {



    @Autowired
    IBookService _bookService;

    @PostMapping("AddBook")
    public ResponseEntity<Response<Book>> addNewBook(@RequestBody Book book)
    {
        Response<Book> data = _bookService.addBook(book);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    @GetMapping("GetBooks")
    public ResponseEntity<Response<Page<Book>>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) SortBy sortBy,
            @RequestParam(required = false) OrderBy orderBy
    )
    {
        Response<Page<Book>> data = _bookService.getBooks(page, size,sortBy,orderBy);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    @GetMapping("GetBookByID/{ID}")
    public ResponseEntity<Response<Book>> getBookById(@PathVariable Long ID)
    {
       Response<Book> data = _bookService.getBookByID(ID);
       if(data.Success){
           return ResponseEntity.ok(data);
       }
       else{
           return ResponseEntity.badRequest().body(data);
       }
    }

    @GetMapping("SearchBook")
    public ResponseEntity<Response<Page<Book>>> searchBook(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) SortBy sortBy,
            @RequestParam(required = false) OrderBy orderBy
    ){
        Response<Page<Book>> data = _bookService.searchBook(title,page,size,sortBy,orderBy);
        if (data.Success) {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }

    @PutMapping("UpdateBook/{id}")
    public ResponseEntity<Response<Book>> UpdateBook(@PathVariable(value="id") Long id, @RequestBody Book book)
    {
        return ResponseEntity.ok(_bookService.updateBook(id,book));
    }

   @DeleteMapping("DeleteBook/{id}")
    public ResponseEntity<Response<Book>> deleteBook(@PathVariable Long id)
   {
       Response<Book> data = _bookService.deleteBook(id);
       if (data.Success) {
           return ResponseEntity.ok(data);
       }
       return ResponseEntity.badRequest().body(data);

   }



}
