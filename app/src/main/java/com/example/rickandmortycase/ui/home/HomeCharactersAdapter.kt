package com.example.rickandmortycase.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycase.R
import com.example.rickandmortycase.data.model.response.Result
import com.example.rickandmortycase.databinding.ItemHomeCharacterBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class HomeCharactersAdapter @Inject constructor(
    private val resultList: List<Result>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<GamesViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        context = parent.context
        val binding =
            ItemHomeCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val currentItem = resultList[position]
        holder.binding.apply {

            Picasso.get().load(currentItem.image).into(ivItemCharacterImage)
            tvItemCharacterName.text = currentItem.name

            val characterStatus = currentItem.status
            tvItemCharacterStatus.text = characterStatus

            if (characterStatus == "Alive") ivItemCharacterStatus.setImageResource(R.drawable.ic_alive_status)
            else if (characterStatus == "Dead") ivItemCharacterStatus.setImageResource(R.drawable.ic_dead_status)
            else ivItemCharacterStatus.setImageResource(R.drawable.ic_unknown_status)

        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}

class GamesViewHolder @Inject constructor(val binding: ItemHomeCharacterBinding) :
    RecyclerView.ViewHolder(binding.root)