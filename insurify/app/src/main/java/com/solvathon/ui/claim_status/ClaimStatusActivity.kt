package com.solvathon.ui.policy
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity
import com.solvathon.ui.claim_status.ClaimStatusItem
import java.util.ArrayList

class ClaimStatusActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val claimStatus = getClaimStatus()

        val claimsRecyclerView:RecyclerView = findViewById<RecyclerView>(R.id.recycler_claim_status)
        claimsRecyclerView.adapter = MyClaimStatusAdapter(claimStatus)
        claimsRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun findContentView(): Int {
        return R.layout.activity_policy
    }

    override fun bindViewWithViewBinding(view: View) {

    }

    private fun getClaimStatus(): List<ClaimStatusItem>{
        val claimStatus = ArrayList<ClaimStatusItem>()

        val claimStatus1 = ClaimStatusItem("abc", "dbiuh9283")
        val claimStatus2 = ClaimStatusItem("abc", "dbiuh9283")
        val claimStatus3 = ClaimStatusItem("abc", "dbiuh9283")
        val claimStatus4= ClaimStatusItem("abc", "dbiuh9283")

        claimStatus.add(claimStatus1)
        claimStatus.add(claimStatus2)
        claimStatus.add(claimStatus3)
        claimStatus.add(claimStatus4)

        return claimStatus
    }

}