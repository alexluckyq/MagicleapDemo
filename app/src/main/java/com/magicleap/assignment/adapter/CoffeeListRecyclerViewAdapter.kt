package com.magicleap.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.magicleap.assignment.R
import com.magicleap.assignment.databinding.CoffeeListitemBinding
import com.magicleap.assignment.model.Coffee
import com.magicleap.assignment.utils.loadImageUrl
import kotlinx.android.synthetic.main.coffee_listitem.view.*

class CoffeeListRecyclerViewAdapter(val onClickAction: ((Coffee) -> Unit)) :
    RecyclerView.Adapter<CoffeeListRecyclerViewAdapter.CoffeeViewHolder>(){

    var coffeeDataList = mutableListOf<Coffee>()

    fun updateCoffeeDataList(dataList: MutableList<Coffee>) {
        coffeeDataList.clear()
        coffeeDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = coffeeDataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val dataBinding = DataBindingUtil.inflate<CoffeeListitemBinding>(
            LayoutInflater.from(parent.context), R.layout.coffee_listitem,
            parent, false)
        return CoffeeViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.bindData(coffeeDataList[position])
    }

    inner class CoffeeViewHolder(val binding: CoffeeListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(coffee: Coffee) {
            with(itemView) {
                binding.coffeeData = coffee
                coffeeImageView.loadImageUrl(
                    coffee.imageUrl,
                    R.drawable.default_thumbnail
                )
                binding.executePendingBindings()

                binding.root.setOnClickListener {
                    binding.coffeeData?.let {
                        onClickAction?.invoke(it)
                    }
                }
            }
        }
    }

}