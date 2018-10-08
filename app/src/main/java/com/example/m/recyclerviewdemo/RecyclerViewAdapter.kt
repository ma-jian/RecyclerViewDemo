package com.example.m.recyclerviewdemo

import android.annotation.SuppressLint
import android.view.View
import com.example.m.recyclerviewdemo.custom.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.item_recycler.view.*

/* 
 * Created by majian
 * Date : 2018/10/8
 * Describe :
 */

class RecyclerViewAdapter : BaseRecyclerAdapter() {
    override fun itemLayoutId(viewType: Int): Int = R.layout.item_recycler

    @SuppressLint("SetTextI18n")
    override fun viewHolder(itemView: View, position: Int, itemViewType: Int) {
        itemView.title.text = "${mContext?.getString(R.string.app_name)} $position"
    }

    override fun getItemCount(): Int = 20
}