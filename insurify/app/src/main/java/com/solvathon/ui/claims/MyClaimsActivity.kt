package com.solvathon.ui.claims

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity
import java.util.ArrayList

class MyClaimsActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val claims = getClaims()

        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val claimsRecyclerView:RecyclerView = findViewById<RecyclerView>(R.id.recycler_view_my_claims)
        claimsRecyclerView.adapter = MyClaimsAdapter(claims)
        claimsRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun findContentView(): Int {
        return R.layout.my_claims
    }

    override fun bindViewWithViewBinding(view: View) {

    }

    private fun getClaims(): List<ClaimItem>{
        val claims = ArrayList<ClaimItem>()

        val claim1 = ClaimItem("Rishabh Gupta", "9238929", " 21 Jan", "39480")
        val claim2 = ClaimItem("Rishabh Gupta", "2378388", " 21 Dec", "39480")
        val claim3 = ClaimItem("Rishabh Gupta", "7483789", " 21 March", "39480")
        val claim4 = ClaimItem("Rishabh Gupta", "2498292", " 21 Feb", "39480")

        claims.add(claim1)
        claims.add(claim2)
        claims.add(claim3)
        claims.add(claim4)

        return claims
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}