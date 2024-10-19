package sazhin.pp.filmapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import sazhin.pp.filmapp.R
import sazhin.pp.filmapp.adapter.FilmsAdapter
import sazhin.pp.filmapp.databinding.FragmentListFilmBinding
import sazhin.pp.filmapp.models.Film
import sazhin.pp.filmapp.viewModel.FilmModel

class ListFilmFragment : Fragment() {

    private lateinit var binding: FragmentListFilmBinding

    private val viewModel by viewModels<FilmModel>()
    private val adapter by lazy { FilmsAdapter() }

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

        binding.idList.layoutManager = LinearLayoutManager(context)
        binding.idList.adapter = adapter

        val filmList: List<Film> = viewModel.filmList
        adapter.submitList(filmList)
    }
}