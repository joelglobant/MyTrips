package com.glob.mytrips.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glob.mytrips.R
import com.glob.mytrips.adapters.BasicAdapterPlace
import com.glob.mytrips.data.mocks.MockPlaces
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    private lateinit var adapterState: BasicAdapterPlace
    val recyclerView by lazy {
        this.findViewById<RecyclerView>(R.id.recyclerView4Test)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        setupRecyclerView()
    }

    fun setupRecyclerView() {

        adapterState = BasicAdapterPlace(MockPlaces.places())
        recyclerView4Test.apply {
            setHasFixedSize(true)
            adapter = adapterState
            layoutManager = LinearLayoutManager(this@RecyclerActivity)
        }

    }
}