package com.xandecoelho5.bookservice.controller;

import com.xandecoelho5.bookservice.model.Book;
import com.xandecoelho5.bookservice.proxy.CambioProxy;
import com.xandecoelho5.bookservice.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book Endpoint")
@RestController
@RequestMapping("book-service")
@RequiredArgsConstructor
public class BookController {

    private final Environment environment;
    private final BookRepository repository;
    private final CambioProxy proxy;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping("/{id}/{currency}")
    public Book findBook(@PathVariable Long id, @PathVariable String currency) {
        var book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book port: " + port + ", Cambio port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());

        return book;
    }
}
