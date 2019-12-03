package com.magicleap.assignment.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magicleap.assignment.R
import com.magicleap.assignment.databinding.ActivityDetailBinding
import com.magicleap.assignment.model.Coffee
import com.magicleap.assignment.utils.loadImageUrl
import com.magicleap.assignment.viewmodels.CoffeeDetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class CoffeeDetailActivity: AppCompatActivity() {

    companion object {
        val COFFEE_ID = "coffeeId"

        fun openActivity(coffeeId: String, context: Context?) {
            val intent = Intent(context, CoffeeDetailActivity::class.java)
            intent.putExtra(COFFEE_ID, coffeeId)
            context?.let {
                context.startActivity(intent)
            }
        }
    }

    private lateinit var detailViewModel: CoffeeDetailViewModel
    private lateinit var activityBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_detail)

        detailViewModel = ViewModelProviders.of(this).get(CoffeeDetailViewModel::class.java)
        detailViewModel.getCoffeeDetailLiveData().observe(this, Observer {
            it?.let {
                init(it)
            }
        })

        val coffeeId = intent.getStringExtra(COFFEE_ID)
        if (!coffeeId.isNullOrEmpty()) {
            detailViewModel.queryCoffeeDetailById(this@CoffeeDetailActivity, coffeeId)
        }

    }

    private fun init(coffee: Coffee) {
        activityBinding.coffeeDetailData = coffee
        coffeeImageView.loadImageUrl(coffee.imageUrl)
    }
}