package org.example

/**
 * Determina si un usuario puede tomar prestado un libro y devuelve un mensaje informativo.
 *
 * @param user El usuario que intenta tomar prestado el libro.
 * @param book El libro que se intenta tomar prestado.
 * @return Un mensaje indicando si el usuario puede tomar prestado el libro y, en caso afirmativo, por cuánto tiempo.
 */
fun canBorrow(user: User, book: Book): String {
    return when (user) {
        is User.Student -> "Can borrow (3 months)."
        is User.Teacher -> "Can borrow (1 year)."
        else -> "Can't borrow. You must be a student or teacher."
    }
}

fun main() {
    val book1 = Book("El Señor de los anillos", "J. R. R. Tolkien", 1954)
    val book2 = Book("Jujutsu Kaisen", "Gege Akutami", 2018)
    val book3 = Book("El regreso al reino de la fantasía", "Gerónimo Stilton", 2007)

    val student = User.Student("1234", "César", "DevOps expert")
    val teacher = User.Teacher("5678", "Iván", "Ciencias")
    val visitant = User.Visitant("9012", "Pepe")

    println("Who can borrow books:")
    println("${book1.title}:")
    println("${student.name}: ${canBorrow(student,book1)}")
    println("${teacher.name}: ${canBorrow(teacher,book1)}")
    println("${visitant.name}: ${canBorrow(visitant,book1)}")

    println("${book2.title}:")
    println("${student.name}: ${canBorrow(student,book2)}")
    println("${teacher.name}: ${canBorrow(teacher,book2)}")
    println("${visitant.name}: ${canBorrow(visitant,book2)}")

    println("${book3.title}:")
    println("${student.name}: ${canBorrow(student,book3)}")
    println("${teacher.name}: ${canBorrow(teacher,book3)}")
    println("${visitant.name}: ${canBorrow(visitant,book3)}")
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