package com.example.m.recyclerviewdemo.util

import android.content.Context

/* 
 * Created by majian
 * Date : 2018/10/9
 * Describe :
 */

fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()