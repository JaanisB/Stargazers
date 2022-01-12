package com.example.stargazers

import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stargazers.model.User
import com.example.stargazers.ui.userspage.UserGridAdapter

// Bind image to XML "imageUrl" tag
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    // Executes lambda expression on imgUrl object
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        // Loads image to imageView with Glide libary
        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }
}

// Bind Recyclerview to XML
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as UserGridAdapter
    adapter.submitList(data)
}

// Bind Spinner data to XML
@BindingAdapter("spinnerData")
fun bindSpinnerdata(spinner: Spinner, data: List<User>?) {
    val adapter = data?.let { ArrayAdapter(spinner.context, android.R.layout.simple_spinner_item, it.toMutableList().map { it.login }) }
    if (adapter != null) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter.notifyDataSetChanged()
        spinner.adapter = adapter
    }
}

