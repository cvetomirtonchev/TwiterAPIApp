package com.tsvetomir.tonchev.tweet.ui.tweet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.tsvetomir.tonchev.tweet.R
import com.tsvetomir.tonchev.tweet.ui.main.model.Tweet
import com.tsvetomir.tonchev.tweet.ui.util.CircleTransform
import kotlinx.android.synthetic.main.activity_tweet.*
import kotlinx.android.synthetic.main.item_tweet.*

class TweetActivity : AppCompatActivity() {

    private val viewModel: TweetActivityViewModel by lazy {
        ViewModelProvider(this).get(TweetActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        val tweet: Tweet =
            intent.getParcelableExtra(EXTRA_TWEET_ID)
                ?: throw IllegalStateException("Tweet is not provided!")

        viewModel.getTweetUser(tweet)
        viewModel.observeUserLiveData().observe(this, {
            tv_username.text = it.username
            tv_tweet_info.text = it.tweet.info
            Picasso.get().load(it.profileImageUrl).transform(CircleTransform())
                .into(iv_profile_image)
        })
    }

    companion object {
        private const val EXTRA_TWEET_ID = "EXTRA_TWEET_ID"

        fun start(from: Activity, tweet: Tweet) {
            Intent(from, TweetActivity::class.java)
                .apply { putExtra(EXTRA_TWEET_ID, tweet) }
                .also { from.startActivity(it) }
        }
    }
}