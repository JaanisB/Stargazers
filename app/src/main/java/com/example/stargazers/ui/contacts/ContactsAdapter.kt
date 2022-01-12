package com.example.stargazers.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stargazers.databinding.ContactItemBinding
import com.example.stargazers.model.Contact
import com.example.stargazers.model.User
import com.example.stargazers.ui.userspage.UserGridAdapter

class ContactsAdapter(val clickLister: OnClickListener) :
    ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(DiffCallback) {

    inner class ContactsViewHolder(private var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contactName: Contact) {
            binding.contact = contactName
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            ContactItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contactName = getItem(position)

        holder.apply {
            itemView.setOnClickListener {
                clickLister.onClick(contactName)
            }
            bind(contactName)
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (contact: Contact) -> Unit) {
        fun onClick(contact: Contact) {
            clickListener(contact)
        }
    }


}