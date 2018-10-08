package com.example.m.recyclerviewdemo.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/* 
 * Created by majian
 * Date : 2018/9/20
 * Describe :
 */

class RecyclerItemDecoration(var padding: Float = 15f, var color: Int = android.R.color.transparent) : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = ContextCompat.getColor(parent!!.context, color)
        val childCount = parent.childCount
        val manager = parent.layoutManager
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            var left = view.left
            var top = view.top
            var right = view.right
            var bottom = view.bottom
            if (manager is GridLayoutManager) {
                top = bottom
                right += parent.context.px2dp(padding)
                bottom += parent.context.px2dp(padding)
                c?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint) //横线
                left = view.right
                top = view.top
                right = view.right + parent.context.px2dp(padding)
                bottom = view.bottom + parent.context.px2dp(padding)
                c?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint) //竖线
            } else if (manager is LinearLayoutManager) {
                when (manager.orientation) {
                    LinearLayoutManager.VERTICAL -> {
                        top = view.bottom
                        bottom = view.bottom + parent.context?.px2dp(padding)!!
                    }
                    LinearLayoutManager.HORIZONTAL -> {
                        left = view.right
                        right = view.right + parent.context?.px2dp(padding)!!
                    }
                }
                c?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent?.getChildAdapterPosition(view) ?: 0
        val manager = parent?.layoutManager

        if (manager is GridLayoutManager) {
            val spanCount = manager.spanCount //列数
            val line = position.div(spanCount) //行数
            val list = position % spanCount
            when (manager.orientation) {
                LinearLayoutManager.VERTICAL -> {
                    outRect?.top = if (line == 0) parent.context.px2dp(padding) else 0
                    outRect?.left = if (list == 0) parent.context.px2dp(padding) else 0
                    outRect?.bottom = parent.context?.px2dp(padding)
                    outRect?.right = parent.context?.px2dp(padding)
                }
                LinearLayoutManager.HORIZONTAL -> {
                    outRect?.top = if (list == 0) parent.context.px2dp(padding) else 0
                    outRect?.left = if (line == 0) parent.context.px2dp(padding) else 0
                    outRect?.bottom = parent.context?.px2dp(padding)
                    outRect?.right = parent.context?.px2dp(padding)
                }
            }
        } else if (manager is LinearLayoutManager) {
            when (manager.orientation) {
                LinearLayoutManager.VERTICAL -> {
                    if (position == 0) {
                        outRect?.top = parent.context.px2dp(padding)
                    } else {
                        outRect?.top = 0
                    }
                    outRect?.bottom = parent.context?.px2dp(padding)
                }
                LinearLayoutManager.HORIZONTAL -> {
                    if (position == 0) {
                        outRect?.left = parent.context.px2dp(padding)
                    } else {
                        outRect?.left = 0
                    }
                    outRect?.right = parent.context?.px2dp(padding)
                }
            }
        }
    }

    fun Context.px2dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
    fun Context.px2dp(value: Float): Int = (value * resources.displayMetrics.density).toInt()
}