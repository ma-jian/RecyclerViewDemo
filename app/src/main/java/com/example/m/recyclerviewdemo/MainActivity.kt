package com.example.m.recyclerviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.m.recyclerviewdemo.custom.RecyclerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = RecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(RecyclerItemDecoration())


        manager_type.text = recyclerView.layoutManager.toString()
        manager_type.setOnClickListener {
            var manager = recyclerView.layoutManager
            if (manager is GridLayoutManager) {
                manager = LinearLayoutManager(this)
            } else if (manager is LinearLayoutManager) {
                manager = GridLayoutManager(this, 3)
            }
            recyclerView.layoutManager = manager
            manager_type.text = recyclerView.layoutManager.toString()
        }
    }
}
