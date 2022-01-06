package com.tsvetomir.tonchev.tweet.ui.main

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsvetomir.tonchev.tweet.R
import com.tsvetomir.tonchev.tweet.ui.tweet.TweetActivity
import com.tsvetomir.tonchev.tweet.ui.util.addItemDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private lateinit var _adapter: TweetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        initRecyclerView()
        viewModel.observeTweetsListLiveData().observe(this, {
            _adapter.loadData(it)
        })

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.getTweetByKeyword(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initAdapter() {
        _adapter = TweetsAdapter { tweet ->
            TweetActivity.start(this, tweet)
        }
    }

    private fun initRecyclerView() {
        with(tweets_rv) {
            addItemDivider(this.context, R.drawable.divider)
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = _adapter
        }
    }
}