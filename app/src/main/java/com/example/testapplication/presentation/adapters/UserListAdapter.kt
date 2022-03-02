package com.example.testapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.database.UserEntity
import com.example.testapplication.databinding.ItemUserAdapterBinding


class UserListAdapter :
    ListAdapter<UserEntity, UserListAdapter.UserListViewHolder>(Companion) {

    interface OnUserItemTap {
        fun onUserTap(position: Int)
    }

    var onTapListener: OnUserItemTap? = null
    private var context: Context? = null

    companion object : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(
            oldItem: UserEntity,
            newItem: UserEntity
        ): Boolean = oldItem.id === newItem.id

        override fun areContentsTheSame(
            oldItem: UserEntity,
            newItem: UserEntity
        ): Boolean = oldItem == newItem
    }

    inner class UserListViewHolder(val binding: ItemUserAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserAdapterBinding.inflate(layoutInflater, parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.data = currentItem
        holder.binding.position = position
        holder.binding.clickListener = onTapListener
        holder.binding.executePendingBindings()


    }



    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }


}