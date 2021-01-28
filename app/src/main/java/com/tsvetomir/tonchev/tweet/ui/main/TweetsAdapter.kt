package com.tsvetomir.tonchev.tweet.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tsvetomir.tonchev.tweet.R
import com.tsvetomir.tonchev.tweet.data.models.local.Tweet

class TweetsAdapter :
    RecyclerView.Adapter<TweetsAdapter.TweetViewHolder>() {

    private val mTweetList = mutableListOf<Tweet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val item = mTweetList[position]
        holder.mInfo.text = item.info
    }

    override fun getItemCount(): Int {
        return mTweetList.size
    }

    fun loadData(dataList: List<Tweet>) {
        mTweetList.clear()
        mTweetList.addAll(dataList)
        notifyDataSetChanged()
    }

    class TweetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mInfo: TextView = view.findViewById(R.id.tweet_info_tx)
    }
}