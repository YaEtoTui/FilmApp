package sazhin.pp.filmapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sazhin.pp.filmapp.R
import sazhin.pp.filmapp.databinding.ItemListFilmBinding
import sazhin.pp.filmapp.models.Film

class FilmsAdapter: ListAdapter<Film, FilmsAdapter.Holder>(Comparator()) {

    private lateinit var onButtonClickListener: OnButtonClickListener

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemListFilmBinding.bind(view)

        fun bind(film: Film, onButtonClickListener: OnButtonClickListener) = with(binding) {
            binding.apply {

                Glide.with(itemView.context)
                    .load(film.photo)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(Photo)

                Description.text = film.shortDescription

                cView.setOnClickListener {
                    onButtonClickListener.onClick(film.id)
                }
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_film, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), onButtonClickListener)
    }

    interface OnButtonClickListener {
        fun onClick(id: Int)
    }

    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        onButtonClickListener = listener
    }
}