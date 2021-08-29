package com.solvathon.ui.policy
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity
import java.util.ArrayList

class QuoteActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quote = getQuote()

        val claimsRecyclerView:RecyclerView = findViewById<RecyclerView>(R.id.recycler_view_my_quote)
        claimsRecyclerView.adapter = MyQuoteAdapter(quote)
        claimsRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun findContentView(): Int {
        return R.layout.quotes
    }

    override fun bindViewWithViewBinding(view: View) {

    }

    private fun getQuote(): List<QuoteItem>{
        val quote = ArrayList<QuoteItem>()

        val quote1 = QuoteItem("abcd", "dbiuh9283", " 21 Jan", "39480","Health")
        val quote2 = QuoteItem("abcd", "pjef99203", " 21 Dec", "39480","Health")
        val quote3 = QuoteItem("abcd", "nwekh2312", " 21 March", "39480","Health")
        val quote4 = QuoteItem("abcd", "wdfwei334", " 21 Feb", "39480","Health")

        quote.add(quote1)
        quote.add(quote2)
        quote.add(quote3)
        quote.add(quote4)

        return quote
    }

}