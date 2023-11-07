package com.elias_shallouf.example.androidtest.views.activities

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.*
import com.elias_shallouf.example.androidtest.R
import com.elias_shallouf.example.androidtest.models.ListItem
import com.elias_shallouf.example.androidtest.view_models.SimpleViewModel
import com.elias_shallouf.example.androidtest.views.adapters.CarouselAdapter
import com.elias_shallouf.example.androidtest.views.adapters.ListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SimpleViewModel
    private val inputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    private lateinit var listAdapter: ListAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = SimpleViewModel((1..100).map {
            ListItem(
                "Item $it",
                ""
            )
        } as MutableList, "")
        viewModel.liveData.observe(this, Observer {
            listAdapter.apply {
                searchText = viewModel.searchText
                updateList(it as MutableList<ListItem>)
            }
        })

        listAdapter = ListAdapter(mutableListOf())

        list.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        search_et.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when(actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    performSearch()
                    return@setOnEditorActionListener true
                }
                else -> false
            }
        }

        carouselAdapter = CarouselAdapter(this, mutableListOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3
        ))
        carousel.adapter = carouselAdapter
    }

    private fun performSearch() {
        search_et.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(search_et.windowToken, 0)
        viewModel.search(search_et.text.toString())
    }
}
