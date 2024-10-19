package sazhin.pp.filmapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import sazhin.pp.filmapp.R
import sazhin.pp.filmapp.adapter.FilmsAdapter
import sazhin.pp.filmapp.databinding.FragmentListFilmBinding
import sazhin.pp.filmapp.models.Film
import sazhin.pp.filmapp.viewModel.FilmModel
import sazhin.pp.filmapp.viewModel.FilmViewModel

class ListFilmFragment : Fragment() {

    private lateinit var binding: FragmentListFilmBinding

    private val viewModel by viewModels<FilmModel>()
    private val adapter by lazy { FilmsAdapter() }

    private val filmViewModel: FilmViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListFilmBinding.inflate(inflater, container, false)
            .also { this.binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmList: List<Film> = viewModel.filmList

        adapter.setOnButtonClickListener(object : FilmsAdapter.OnButtonClickListener {
            override fun onClick(id: Int) {
                filmViewModel.film.value = filmList.first { it.id == id }
                findNavController().navigate(R.id.action_navigation_list_film_to_navigation_film)
            }
        })
        binding.idList.layoutManager = LinearLayoutManager(context)
        binding.idList.adapter = adapter

        adapter.submitList(filmList)
    }
}