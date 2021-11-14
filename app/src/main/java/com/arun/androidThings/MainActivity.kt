package com.arun.androidThings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arun.androidThings.adapter.ThingsAdapter
import com.arun.androidThings.data.Details
import com.arun.androidThings.databinding.ActivityMainBinding
import com.arun.androidThings.viewModel.MainActivityViewModel
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding

    var details: List<Details> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        context = this@MainActivity

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getUser()!!.observe(this, Observer { thingsData ->
            details = thingsData.thingsDetails
            setRecycleView(details)
        })

        binding.backgroundPage.actionButton.setOnClickListener {
            if (!validateUser(details)) {
                Toast.makeText(
                    applicationContext,
                    "Minimum One item need to selected",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                moveViewDetails()
            }
        }
    }


    private fun setRecycleView(details: List<Details>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ThingsAdapter(details, context as MainActivity)
    }


    private val validateUser: (List<Details>) -> Boolean = {
        var checked = false
        for (user in it) {
            if (user.isChecked) {
                checked = user.isChecked
                break
            }
        }
        checked
    }


    private fun moveViewDetails() {
        val intent = Intent(this, ViewDetailsActivity::class.java)
        startActivity(intent)

    }
}