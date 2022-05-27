package com.example.rickandmortycase.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortycase.data.model.response.Result
import com.example.rickandmortycase.databinding.ItemHomeCharacterBinding
import com.example.rickandmortycase.utils.extensions.setCharacterImage
import com.example.rickandmortycase.utils.extensions.setCharacterStatus
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
            val characterStatus = currentItem.status
            tvItemCharacterStatus.text = characterStatus
            ivItemCharacterStatus.setCharacterStatus(characterStatus)
            ivItemCharacterImage.setCharacterImage(currentItem.image)
            tvItemCharacterName.text = currentItem.name
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClickListener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return resultList.size
    }
}

class GamesViewHolder @Inject constructor(val binding: ItemHomeCharacterBinding) :
    RecyclerView.ViewHolder(binding.root)