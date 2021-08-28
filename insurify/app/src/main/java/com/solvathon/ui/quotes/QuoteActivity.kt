package com.solvathon.ui.quotes
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.ActivityQuotesBinding
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class QuoteActivity() :BaseActivity(){
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var binding: ActivityQuotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Quotes"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val quotes = intent.getParcelableArrayListExtra<Policy>("QUOTES_DATA")
        setUpRecycleView(quotes)
    }

    fun setUpRecycleView(quotes: ArrayList<Policy>?) {
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerViewMyQuote.apply {
            layoutManager = linearLayoutManager
            adapter = quotes?.let { MyQuotesAdapter(it) }
        }
    }

    override fun findContentView(): Int {
        return R.layout.activity_quotes
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityQuotesBinding.bind(view)
    }

    override fun toggleLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}