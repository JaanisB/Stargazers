package com.example.stargazers

import android.view.View
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stargazers.model.User
import com.example.stargazers.network.StargazersApiStatus
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

/*// Bind Spinner data to XML
@BindingAdapter("spinnerData")
fun bindSpinnerdata(autoCompleteTextView: AutoCompleteTextView, data: List<User>?) {
    autoCompleteTextView.setAdapter(activity, R.layout.mainmenu_dropdown_item, data.onEach { it.login }))
    ArrayAdapter.createFromResource()
    val adapter = spinner.adapter as SpinnerAdapter

}*/

// Adapter binds status and according to that changes pictures or animations in XML View
@BindingAdapter("stargazersApiStatus")
fun bindStatus (statusImageView: ImageView, status: StargazersApiStatus?) {
    when (status) {
        StargazersApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        StargazersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        StargazersApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}