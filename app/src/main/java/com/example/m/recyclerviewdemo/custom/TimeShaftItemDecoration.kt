package com.example.m.recyclerviewdemo.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.m.recyclerviewdemo.util.dp2px

/* 
 * Created by majian
 * Date : 2018/9/20
 * Describe : 时间轴
 */

class TimeShaftItemDecoration() : RecyclerView.ItemDecoration() {
    var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mOffsetLeft = 0 //左侧轴宽度
    var mRadius = 0 // 圆点半径

    constructor(context: Context, radius: Int = 5, color: Int = Color.RED) : this() {
        mPaint.color = color
        this.mRadius = context.dp2px(radius)
        mPaint.strokeWidth = 4f
        this.mOffsetLeft = context.dp2px(30)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent?.getChildAdapterPosition(view) == 0) {
            outRect?.top = mOffsetLeft / 2
        }
        outRect?.bottom = mOffsetLeft / 2
        outRect?.left = mOffsetLeft
    }


    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val childCount = parent?.childCount ?: 0
        for (i in 0 until childCount) {
            val view = parent?.getChildAt(i)!!
            val dividerLeft = parent.paddingLeft

            val centerX = dividerLeft.times(1.0) + mOffsetLeft / 2
            val centerY = view.top + (view.bottom - view.top) / 2
            //上边线
            c?.drawLine(centerX.toFloat(), (view.top - mOffsetLeft).toFloat(), centerX.toFloat(), (centerY - mRadius).toFloat(), mPaint)

            //圆点
            mPaint.style = Paint.Style.STROKE
            c?.drawCircle(centerX.toFloat(), centerY.toFloat(), mRadius.toFloat(), mPaint)

            //下边线
            mPaint.style = Paint.Style.FILL
            c?.drawLine(centerX.toFloat(), (centerY + mRadius).toFloat(), centerX.toFloat(), view.bottom.toFloat(), mPaint)

        }
    }
}