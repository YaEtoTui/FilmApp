package sazhin.pp.filmapp.models

data class Film(
    val id: Int,
    val name: String,
    val photo: String,
    val type: Type,
    val year: Int,
    val description: String,
    val shortDescription: String,
    val rating: Double,
    val movieLength: Int,
    val personList: List<Person>
)