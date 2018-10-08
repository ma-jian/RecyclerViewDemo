package com.example.m.recyclerviewdemo.custom

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.annotations.NotNull

/*
 * Created by majian
 * Date : 2018/6/11
 * Describe :
 */

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder>() {
    var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        return ViewHolder(LayoutInflater.from(mContext).inflate(itemLayoutId(viewType), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewHolder(holder.itemView, position, holder.itemViewType)
    }

    abstract fun itemLayoutId(viewType: Int): Int

    abstract fun viewHolder(@NotNull itemView: View, position: Int, itemViewType: Int)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}