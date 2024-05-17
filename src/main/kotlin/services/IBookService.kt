package org.example.services

import org.example.entity.BookEntity

interface IBookService {
    fun insert(book: BookEntity): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(bookId: Int): Boolean
    fun selectById(bookId: Int): BookEntity?
    fun selectAll():List<BookEntity>?
}