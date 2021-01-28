package com.tsvetomir.tonchev.tweet.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tsvetomir.tonchev.tweet.R
import com.tsvetomir.tonchev.tweet.ui.util.addItemDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private lateinit var mAdapter: TweetsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        initRecyclerView()
        mViewModel.observeTweetsListLiveData().observe(this, Observer {
            mAdapter.loadData(it)
        })
        search_view.setOnSearchClickListener(View.OnClickListener {

        })

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mViewModel.getTweetByKeyword(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun initAdapter() {
        mAdapter = TweetsAdapter()
    }

    private fun initRecyclerView() {
        with(tweets_rv) {
            addItemDivider(this.context, R.drawable.divider)
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }
}