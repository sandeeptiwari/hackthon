package com.solvathon.ui.quotes

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity
import com.solvathon.ui.claims.ClaimItem
import com.solvathon.ui.claims.MyClaimsAdapter
import java.util.ArrayList

class QuoteActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quotes = getQuotes()

        val claimsRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler_view_my_quotes)
        claimsRecyclerView.adapter = MyQuotesAdapter(quotes)
        claimsRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun findContentView(): Int {
        return R.layout.my_quotes
    }

    override fun bindViewWithViewBinding(view: View) {

    }

    override fun toggleLoader(b: Boolean) {

    }

    private fun getQuotes(): List<QuoteItem>{
        val quotes = ArrayList<QuoteItem>()

        val q1 = QuoteItem("1,00,000", "45 years", "80%", "2,000")
        val q2 = QuoteItem("1,00,000", "40 years", "50%", "2,000")
        val q3 = QuoteItem("1,00,000", "55 years", "60%", "2,000")
        val q4 = QuoteItem("1,00,000", "25 years", "40%", "2,000")

        quotes.add(q1)
        quotes.add(q2)
        quotes.add(q3)
        quotes.add(q4)

        return quotes
    }

}