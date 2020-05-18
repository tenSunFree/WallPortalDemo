package com.home.wallportaldemo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.home.wallportaldemo.R
import kotlinx.android.synthetic.main.fragment_shopping_design.*
import com.home.wallportaldemo.view.adapter.ShoppingDesignAdapter
import com.home.wallportaldemo.viewmodel.ShoppingDesignViewModel

class ShoppingDesignFragment : Fragment() {

    private lateinit var adapter: ShoppingDesignAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_shopping_design, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val postViewModel = ViewModelProvider(this).get(ShoppingDesignViewModel::class.java)
        postViewModel.pagedListLiveData?.observe(viewLifecycleOwner,
            Observer { postList ->
                adapter.submitList(postList)
            })
        adapter = ShoppingDesignAdapter(object : ShoppingDesignAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext(), "click $position", Toast.LENGTH_LONG).show()
            }
        })
        recycler_view.adapter = adapter
    }
}
