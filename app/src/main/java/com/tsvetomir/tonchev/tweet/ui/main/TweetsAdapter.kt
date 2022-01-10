package com.tsvetomir.tonchev.tweet.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tsvetomir.tonchev.tweet.R
import com.tsvetomir.tonchev.tweet.ui.main.model.Tweet

class TweetsAdapter(private val onTweetSelected: (usLimitData: Tweet) -> Unit) :
    RecyclerView.Adapter<TweetsAdapter.TweetViewHolder>() {

    private val _tweetList = mutableListOf<Tweet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val item = _tweetList[position]
        holder.bind(item, onTweetSelected)
    }

    override fun getItemCount(): Int = _tweetList.size

    fun loadData(dataList: List<Tweet>) {
        _tweetList.clear()
        _tweetList.addAll(dataList)
        notifyDataSetChanged()
    }

    class TweetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val info: TextView = view.findViewById(R.id.tv_tweet_info)
        private val container: CardView = view.findViewById(R.id.card_container)

        fun bind(tweet: Tweet, onTweetSelected: (usLimitData: Tweet) -> Unit) {
            info.text = tweet.info
            container.setOnClickListener {
                onTweetSelected(tweet)
            }
        }
    }
}