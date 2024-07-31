package com.example.reddittoppublications.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittoppublications.databinding.ItemPublicationBinding
import com.example.reddittoppublications.domain.models.Children
import java.util.concurrent.TimeUnit

class TopPublicationsAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Children, TopPublicationsAdapter.TopPublicationsViewHolder>(DiffUtil()) {

    class TopPublicationsViewHolder(
        private var rawItemPublicationBinding: ItemPublicationBinding,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(rawItemPublicationBinding.root) {
        fun bind(children: Children) {
            setContent(children)

            if (!children.dataX.isVideo) {
                rawItemPublicationBinding.ivImageOfPublication.setOnClickListener {
                    onItemClickListener.onImageClicked(adapterPosition, children)
                }
            }
        }

        private fun setContent(children: Children) {
            rawItemPublicationBinding.apply {
                tvAuthorOfPublication.text = children.dataX.author
                tvDateOfPublication.text = getTimeAgo(children)
                tvCountOfComments.text = "${children.dataX.numComments} comments"

                if (!children.dataX.isVideo) {
                    Glide.with(root)
                        .load(children.dataX.thumbnail)
                        .into(ivImageOfPublication)
                }
            }
        }

        private fun getTimeAgo(children: Children): String {
            val created = children.dataX.created
            val currentTime = System.currentTimeMillis()
            val createdTime = (created * 1000).toLong()
            val timeDifference = currentTime - createdTime

            val hoursAgo = TimeUnit.MILLISECONDS.toHours(timeDifference)
            val minutesAgo = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60

            return if (hoursAgo > 0) {
                "$hoursAgo hr. ago"
            } else {
                "$minutesAgo min. ago"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPublicationsViewHolder {
        val binding =
            ItemPublicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPublicationsViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: TopPublicationsViewHolder, position: Int) {
        val children = getItem(position)
        holder.bind(children)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Children>() {
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.dataX.id == newItem.dataX.id
        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onImageClicked(position: Int, children: Children)
    }
}