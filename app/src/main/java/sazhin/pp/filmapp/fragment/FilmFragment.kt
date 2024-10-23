package sazhin.pp.filmapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import sazhin.pp.filmapp.R
import sazhin.pp.filmapp.adapter.FilmsAdapter
import sazhin.pp.filmapp.adapter.PersonsAdapter
import sazhin.pp.filmapp.databinding.FragmentFilmBinding
import sazhin.pp.filmapp.databinding.FragmentListFilmBinding
import sazhin.pp.filmapp.models.Film
import sazhin.pp.filmapp.viewModel.FilmViewModel

class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding

    private val adapter by lazy { PersonsAdapter() }

    private val filmViewModel: FilmViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmBinding.inflate(inflater, container, false)
            .also { this.binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillPage()

        binding.idPersons.adapter = adapter

        binding.imBack.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_film_to_navigation_list_film)
        }
    }

    private fun fillPage() {
        val film: Film? = filmViewModel.film.value

        binding.apply {
            Name.text = film?.name
            Genre.text = "Жанр: " + film?.type.toString()
            Year.text = "Год: " + film?.year
            Rating.text = "Рейтинг: " + film?.rating
            Duration.text = "Продолжительность(мин.): " + film?.movieLength

            Description.text = film?.description

            Glide.with(requireContext())
                .load(film?.photo)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(Photo)
        }

        adapter.submitList(film?.personList)
    }
}