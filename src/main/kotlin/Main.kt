package org.example

import org.example.DAO.BookDAO
import org.example.database.DataBase
import org.example.entity.BookEntity
import org.example.entity.UserEntity
import org.example.output.Consola
import org.example.services.BookService

fun main() {
    val console = Consola()
    val datasource = DataBase.getDataSource(DataBase.DataSourceType.HIKARI)
    val bookDAO = BookDAO(datasource,console)
    val bookService = BookService(bookDAO)


    val book1 = BookEntity(1,"El Señor de los anillos", "J. R. R. Tolkien", 1954)
    val book2 = BookEntity(2,"Jujutsu Kaisen", "Gege Akutami", 2018)
    val book3 = BookEntity(3,"El regreso al reino de la fantasía", "Gerónimo Stilton", 2007)

    bookService.insert(book1)
    bookService.insert(book2)
    bookService.insert(book3)

    val student = UserEntity.Student("1234", "César", "DevOps expert")
    val teacher = UserEntity.Teacher("5678", "Iván", "Ciencias")
    val visitant = UserEntity.Visitant("9012", "Pepe")

    /**
     * Determina si un usuario puede tomar prestado un libro y devuelve un mensaje informativo.
     *
     * @param user El usuario que intenta tomar prestado el libro.
     * @param book El libro que se intenta tomar prestado.
     * @return Un mensaje indicando si el usuario puede tomar prestado el libro y, en caso afirmativo, por cuánto tiempo.
     */
    fun canBorrow(user: UserEntity, book: BookEntity): String {
        return when (user) {
            is UserEntity.Student -> "Can borrow (3 months)."
            is UserEntity.Teacher -> "Can borrow (1 year)."
            else -> "Can't borrow. You must be a student or teacher."
        }
    }

    console.showMessage("Who can borrow books:")
    console.showMessage("${book1.title}:")
    console.showMessage("${student.name}: ${canBorrow(student,book1)}")
    console.showMessage("${teacher.name}: ${canBorrow(teacher,book1)}")
    console.showMessage("${visitant.name}: ${canBorrow(visitant,book1)}")

    console.showMessage("${book2.title}:")
    console.showMessage("${student.name}: ${canBorrow(student,book2)}")
    console.showMessage("${teacher.name}: ${canBorrow(teacher,book2)}")
    console.showMessage("${visitant.name}: ${canBorrow(visitant,book2)}")

    console.showMessage("${book3.title}:")
    console.showMessage("${student.name}: ${canBorrow(student,book3)}")
    console.showMessage("${teacher.name}: ${canBorrow(teacher,book3)}")
    console.showMessage("${visitant.name}: ${canBorrow(visitant,book3)}")

    //Uso de update
    val updatedBook1 = book1.copy(title = "El Señor de los Anillos (Actualizado)")
    bookService.update(updatedBook1)
    console.showMessage("\nLibro actualizado:")
    console.showMessage(bookService.selectById(1).toString())

    // Uso de deleteById
    bookService.deleteById(2)
    console.showMessage("\nDespués de eliminar el libro con ID 2:")
    bookService.selectAll()?.forEach { console.showMessage(it.toString()) }

    // Uso de selectById
    val selectedBook = bookService.selectById(3)
    console.showMessage("\nSeleccionar libro con ID 3:")
    console.showMessage(selectedBook.toString())

    // Uso de selectAll
    val allBooks = bookService.selectAll()
    console.showMessage("\nSeleccionar todos los libros:")
    allBooks?.forEach { console.showMessage(it.toString()) }
}

/*
CREATE TABLE Books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    publishYear INT
);

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    userType VARCHAR(50),
    career VARCHAR(255) NULL,
    department VARCHAR(255) NULL
);
 */