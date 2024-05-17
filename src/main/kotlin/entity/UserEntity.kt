package org.example.entity

/**
 * Clase sellada que representa diferentes tipos de usuarios.
 */
sealed class UserEntity {
    /**
     * Representa un estudiante.
     *
     * @property id El ID del estudiante.
     * @property name El nombre del estudiante.
     * @property career La carrera del estudiante.
     */
    data class Student(val id: String, val name: String, val career: String): UserEntity()

    /**
     * Representa un profesor.
     *
     * @property id El ID del profesor.
     * @property name El nombre del profesor.
     * @property department El departamento al que pertenece el profesor.
     */
    data class Teacher(val id: String, val name: String, val department: String): UserEntity()

    /**
     * Representa un visitante.
     *
     * @property id El ID del visitante.
     * @property name El nombre del visitante.
     */
    data class Visitant(val id: String, val name: String): UserEntity()
}