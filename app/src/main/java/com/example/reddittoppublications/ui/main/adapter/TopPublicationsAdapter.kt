package com.example.reddittoppublications.ui.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddittoppublications.R
import com.example.reddittoppublications.databinding.ItemPublicationBinding
import com.example.reddittoppublications.domain.models.Children
import java.util.concurrent.TimeUnit

class TopPublicationsAdapter(
    private val onItemClickListener: OnItemClickListener
) :
    PagingDataAdapter<Children, TopPublicationsAdapter.TopPublicationsViewHolder>(differCallback) {

    class TopPublicationsViewHolder(
        private var rawItemPublicationBinding: ItemPublicationBinding,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(rawItemPublicationBinding.root) {
        fun bind(children: Children) {
            Log.d("TopPublicationsViewHolder", "Binding Children: $children")
            setContent(children)

            if (!children.dataX.isVideo) {
                rawItemPublicationBinding.ivImageOfPublication.setOnClickListener {
                    onItemClickListener.onImageClicked(absoluteAdapterPosition, children)
                }
            }
        }

        private fun setContent(children: Children) {
            rawItemPublicationBinding.apply {
                tvAuthorOfPublication.text = children.dataX.author
                tvDateOfPublication.text = getTimeAgo(tvDateOfPublication.context, children)
                tvCountOfComments.text = tvCountOfComments.context.getString(R.string.comments, children.dataX.numComments)

                if (!children.dataX.isVideo) {
                    Glide.with(root)
                        .load(children.dataX.thumbnail)
                        .into(ivImageOfPublication)
                }
            }
        }

        private fun getTimeAgo(context: Context, children: Children): String {

            val created = children.dataX.created
            val currentTime = System.currentTimeMillis()
            val createdTime = (created * 1000).toLong()
            val timeDifference = currentTime - createdTime

            val hoursAgo = TimeUnit.MILLISECONDS.toHours(timeDifference)
            val minutesAgo = TimeUnit.MILLISECONDS.toMinutes(timeDifference) % 60

            return if (hoursAgo > 0) {
                context.getString(R.string.hours_ago, hoursAgo)
            } else {
                context.getString(R.string.minutes_ago, minutesAgo)
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
        Log.d("TopPublicationsAdapter", "Binding item at position $position: $itemCount")
        children?.let {
            holder.bind(it)
        }
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Children>() {
            override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
                return oldItem.dataX.id == newItem.dataX.id
            }

            override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickListener {
        fun onImageClicked(position: Int, children: Children)
    }
}

