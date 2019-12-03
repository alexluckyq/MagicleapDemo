package com.magicleap.assignment.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.magicleap.assignment.R
import com.magicleap.assignment.adapter.CoffeeListRecyclerViewAdapter
import com.magicleap.assignment.viewmodels.CoffeeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var coffeeViewModel: CoffeeViewModel
    private lateinit var coffeListAdapter: CoffeeListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coffeeViewModel = ViewModelProviders.of(this).get(CoffeeViewModel::class.java)
        coffeListAdapter = CoffeeListRecyclerViewAdapter {
            CoffeeDetailActivity.openActivity(it.id, this@MainActivity)
        }
        coffeeRecyclerView.layoutManager = LinearLayoutManager(this)
        coffeeRecyclerView.adapter = coffeListAdapter

        coffeeViewModel.getCoffeeLiveData().observe( this, Observer {
            swipeLayout.isRefreshing = false
                it?.let {
                if (it.isEmpty()) {
                    noResultTextView.visibility = View.VISIBLE

                } else {
                    noResultTextView.visibility = View.GONE
                    coffeListAdapter.updateCoffeeDataList(it.toMutableList())
                }
            }
        })
        swipeLayout.isRefreshing = true
        swipeLayout.setOnRefreshListener {
            coffeeViewModel.queryCoffeeList(this)
        }
        noResultTextView.visibility = View.GONE
        coffeeViewModel.queryCoffeeList(this)
    }

}
