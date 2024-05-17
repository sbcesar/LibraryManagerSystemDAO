package org.example.entity

/**
 * Clase de datos que representa un DVD.
 *
 * @property title El título del DVD.
 * @property principal El actor principal del DVD.
 * @property year El año de lanzamiento del DVD.
 */
data class DVDEntity(val title: String, val principal: String, val year: Int)
