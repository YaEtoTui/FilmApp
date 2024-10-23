package sazhin.pp.filmapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sazhin.pp.filmapp.models.Film

class FilmViewModel : ViewModel() {
    val film = MutableLiveData<Film>()
}