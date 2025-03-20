package com.fetch.hiring.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.fetch.hiring.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(MainFragment())
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, fragment, "MainFragment")
            .disallowAddToBackStack()
            .commit()
    }

}