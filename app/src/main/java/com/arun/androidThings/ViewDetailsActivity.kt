package com.arun.androidThings


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.arun.androidThings.data.Details
import com.arun.androidThings.databinding.ActivityViewDetailsBinding
import com.arun.androidThings.viewModel.MainActivityViewModel
import com.bumptech.glide.Glide


class ViewDetailsActivity : AppCompatActivity(){

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var binding: ActivityViewDetailsBinding

    private var list: MutableList<Details> = ArrayList()

    private var selectedString: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_view_details
        )

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        list = listData(mainActivityViewModel.getUser()!!.value!!.thingsDetails) as MutableList<Details>


        binding.backgroundPage.actionButton.setOnClickListener {
            finish()
        }
        setUpDetailsList()
    }


    private val listData:(List<Details>)->List <Details> = {
        val list: MutableList<Details> = ArrayList()
        for (userList in it) {
            if (userList.isChecked) {
                list.add(userList)
            }

        }
        list
    }
    private fun createButton(name: String) {
        val dynamicButton = Button(this)
        dynamicButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val layoutParams = dynamicButton.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(0, 10, 0, 10)
        dynamicButton.text = name
        dynamicButton.setBackgroundColor(ContextCompat.getColor(this, R.color.grey))
        binding.dataitems.addView(dynamicButton)

        dynamicButton.setOnClickListener {
            selectedString = dynamicButton.text.toString()
            setUpDetailsList()
        }
    }

    private fun setUpDetailsList() {
        binding.dataitems.removeAllViews()
        for (userDetails in list) {
            if (userDetails.firstName.equals(selectedString)) {
                setUpUserDetails(userDetails)
            } else
                createButton(userDetails.firstName)
        }

    }

    private val setUpUserDetails : (Details) -> Unit = {
        binding.userDetailsLayout.visibility = View.VISIBLE
        binding.firstName.text = it.firstName
        binding.lastName.text = it.lastName
        binding.email.text = it.email
        Glide.with(this)
            .load(it.avatar)
            .into(binding.avatar)
    }

}