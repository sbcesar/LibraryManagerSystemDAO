package org.example.DAO

import org.example.entity.BookEntity
import org.example.output.IConsole
import java.sql.SQLException
import javax.sql.DataSource

/**
 * Implementación de la interfaz IBookDAO para gestionar objetos BookEntity en una base de datos.
 *
 * @property dataSource La fuente de datos utilizada para las conexiones a la base de datos.
 * @property console La interfaz de consola para mostrar mensajes.
 */
class BookDAO(
    private val dataSource: DataSource,
    private val console: IConsole
) :IBookDAO {

    /**
     * Inserta un nuevo libro en la base de datos.
     *
     * @param book El libro a insertar.
     * @return El libro insertado si tiene éxito, null en caso contrario.
     */
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

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param book El libro a actualizar.
     * @return El libro actualizado si tiene éxito, null en caso contrario.
     */
    override fun update(book: BookEntity): BookEntity? {
        val query = "UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, PUBLISHYEAR = ? WHERE ID = ?"

        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(query).use { preparedStatement ->
                    preparedStatement.setString(1, book.title)
                    preparedStatement.setString(2, book.author)
                    preparedStatement.setInt(3, book.publishYear)
                    preparedStatement.setInt(4, book.id)
                    preparedStatement.executeUpdate()
                    book
                }
            }
        } catch (e: SQLException) {
            console.showMessage("Update query failed! (${e.message})")
            null
        }
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param bookId El ID del libro a eliminar.
     * @return True si el libro se eliminó con éxito, false en caso contrario.
     */
    override fun deleteById(bookId: Int): Boolean {
        val query = "DELETE FROM BOOKS WHERE ID = ?"

        try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(query).use { preparedStatement ->
                    preparedStatement.setInt(1,bookId)
                    return (preparedStatement.executeUpdate() == 1)
                }
            }
        } catch (e: SQLException) {
            console.showMessage("Delete query failed! (${e.message})")
            return false
        }
    }

    /**
     * Selecciona un libro de la base de datos por su ID.
     *
     * @param bookId El ID del libro a seleccionar.
     * @return El libro seleccionado si se encuentra, null en caso contrario.
     */
    override fun selectById(bookId: Int): BookEntity? {
        val query = "SELECT * FROM BOOKS WHERE ID = ?"

        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(query).use { preparedStatement ->
                    preparedStatement.setInt(1,bookId)
                    val resultSet = preparedStatement.executeQuery()
                    if (resultSet.next()) {
                        BookEntity(
                            id = resultSet.getInt("ID"),
                            title = resultSet.getString("TITLE"),
                            author = resultSet.getString("AUTHOR"),
                            publishYear = resultSet.getInt("PUBLISHYEAR")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("Select query failed! (${e.message})")
            null
        }
    }

    /**
     * Selecciona todos los libros de la base de datos.
     *
     * @return Una lista de todos los libros, o null si la consulta falla.
     */
    override fun selectAll(): List<BookEntity>? {
        val query = "SELECT * FROM BOOKS"

        return try {
            dataSource.connection.use { connection ->
                connection.prepareStatement(query).use { preparedStatement ->
                    val books = mutableListOf<BookEntity>()
                    val resultSet = preparedStatement.executeQuery()
                    while (resultSet.next()) {
                        books.add(
                            BookEntity(
                                id = resultSet.getInt("ID"),
                                title = resultSet.getString("TITLE"),
                                author = resultSet.getString("AUTHOR"),
                                publishYear = resultSet.getInt("PUBLISHYEAR")
                            )
                        )
                    }
                    books
                }
            }

        } catch (e: SQLException) {
            console.showMessage("Select query failed! (${e.message})")
            null
        }
    }

}