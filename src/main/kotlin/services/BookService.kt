package org.example.services

import org.example.DAO.IBookDAO
import org.example.entity.BookEntity

class BookService(
    private val bookDAO: IBookDAO
): IBookService {
    override fun insert(book: BookEntity): BookEntity? {
        return bookDAO.insert(book)
    }

    override fun update(book: BookEntity): BookEntity? {
        return bookDAO.update(book)
    }

    override fun deleteById(bookId: Int): Boolean {
        return bookDAO.deleteById(bookId)
    }

    override fun selectById(bookId: Int): BookEntity? {
        return bookDAO.selectById(bookId)
    }

    override fun selectAll(): List<BookEntity>? {
        return bookDAO.selectAll()
    }
}