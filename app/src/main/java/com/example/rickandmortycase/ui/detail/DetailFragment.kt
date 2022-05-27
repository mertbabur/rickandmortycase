package com.example.rickandmortycase.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.rickandmortycase.data.model.response.Result
import com.example.rickandmortycase.databinding.FragmentDetailBinding
import com.example.rickandmortycase.ui.base.BaseFragment
import com.example.rickandmortycase.utils.extensions.setCharacterStatus
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()
    private val characterResult: Result by lazy { args.characterResult }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCharacterResultInfo()
    }

    private fun setCharacterResultInfo() {
        with(binding) {
            tvDetailCharacterStatus.text = characterResult.status
            Picasso.get().load(characterResult.image).into(ivDetailCharacterImage)
            tvDetailCharacterName.text = characterResult.name
            tvDetailCharacterSpecies.text = characterResult.species
            tvDetailCharacterGender.text = characterResult.gender
            tvDetailCharacterOrigin.text = characterResult.origin?.name
            tvDetailCharacterLocation.text = characterResult.location?.name
            tvDetailCharacterEpisodesCount.text =
                "Episodes " + characterResult.episode?.size.toString()
            ivDetailCharacterStatus.setCharacterStatus(characterResult.status)
        }
    }

}