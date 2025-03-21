package com.fetch.hiring.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fetch.hiring.databinding.FragmentMainBinding
import com.fetch.hiring.model.Item

class MainFragment : Fragment() {

    private lateinit var adapter: ExpandableItemListAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater)

        adapter = ExpandableItemListAdapter(inflater)
        expandableListView.setAdapter(adapter)

        presenter = MainPresenter(this)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    fun updateModel(model: Map<String, List<String>>) {
        errorView.visibility = View.GONE
        expandableListView.visibility = View.VISIBLE
        adapter.update(model)
    }

    fun displayError() {
        errorView.visibility = View.VISIBLE
        expandableListView.visibility = View.GONE
    }

    private val expandableListView: ExpandableListView
        get() = binding.expandableListView

    private val errorView: TextView
        get() = binding.errorView


}