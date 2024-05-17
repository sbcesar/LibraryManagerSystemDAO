package org.example.DAO

import org.example.entity.BookEntity

interface IBookDAO {
    fun insert(book: BookEntity): BookEntity?
    fun update(book: BookEntity): BookEntity?
    fun deleteById(bookId: Int): Boolean
    fun selectById(bookId: Int): BookEntity?
    fun selectAll():List<BookEntity>?
}