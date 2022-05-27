package com.example.rickandmortycase.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.example.rickandmortycase.R
import com.example.rickandmortycase.constans.Constants
import com.squareup.picasso.Picasso

fun AppCompatImageView.setCharacterStatus(status: String?) {
    if (status == Constants.ALIVE) this.setImageResource(R.drawable.ic_alive_status)
    else if (status == Constants.DEAD) this.setImageResource(R.drawable.ic_dead_status)
    else if (status == Constants.UNKNOWN) this.setImageResource(R.drawable.ic_unknown_status)
}

fun AppCompatImageView.setCharacterImage(image: String?) {
    Picasso.get().load(image).into(this)
}