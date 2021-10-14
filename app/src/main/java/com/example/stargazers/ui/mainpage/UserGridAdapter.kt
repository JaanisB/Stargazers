package com.example.stargazers.ui.mainpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazers.databinding.GridViewItemBinding
import com.example.stargazers.network.UserNetworkEntity

class UserGridAdapter : ListAdapter<UserNetworkEntity, UserGridAdapter.UserViewHolder>(DiffCallback) {

    inner class UserViewHolder (private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userNetworkEntity: UserNetworkEntity) {
            binding.user = userNetworkEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<UserNetworkEntity>() {
        override fun areItemsTheSame(oldItem: UserNetworkEntity, newItem: UserNetworkEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserNetworkEntity, newItem: UserNetworkEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }

}