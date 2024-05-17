package org.example.DAO

import org.example.entity.BookEntity
import org.example.output.IConsole
import java.sql.SQLException
import javax.sql.DataSource

class BookDAO(
    private val dataSource: DataSource,
    private val console: IConsole
) :IBookDAO {
    override fun insert(book: BookEntity): BookEntity? {
        val query = "INSERT INTO BOOKS (TITLE, AUTHOR, PUBLISHYEAR) VALUES (?, ?, ?)"

        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(query).use { preparedStatement ->
                    preparedStatement.setString(1,book.title)
                    preparedStatement.setString(2,book.author)
                    preparedStatement.setInt(3,book.publishYear)
                    val resultSet = preparedStatement.executeUpdate()
                    if (resultSet == 1) {
                        book
                    } else {
                        console.showMessage("Insert query failed! ($resultSet records instead)")
                        null
                    }
                }
            }

        } catch (e: SQLException) {
            console.showMessage("Insert query error: ($e)")
            null
        }
    }

    override fun update(book: BookEntity): BookEntity? {
        TODO("Not yet implemented")
    }

    override fun deleteById(bookId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun selectById(bookId: Int): BookEntity? {
        TODO("Not yet implemented")
    }

    override fun selectAll(): List<BookEntity>? {
        TODO("Not yet implemented")
    }

}