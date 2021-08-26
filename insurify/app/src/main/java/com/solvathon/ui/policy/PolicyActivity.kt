package com.solvathon.ui.policy
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity
import java.util.ArrayList

class PolicyActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = getPolicy()

        val claimsRecyclerView:RecyclerView = findViewById<RecyclerView>(R.id.recycler_view_my_policy)
        claimsRecyclerView.adapter = MyPolicyAdapter(policy)
        claimsRecyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun findContentView(): Int {
        return R.layout.policy
    }

    override fun bindViewWithViewBinding(view: View) {

    }

    private fun getPolicy(): List<PolicyItem>{
        val policy = ArrayList<PolicyItem>()

        val policy1 = PolicyItem("abcd", "dbiuh9283", " 21 Jan", "39480","Health")
        val policy2 = PolicyItem("abcd", "pjef99203", " 21 Dec", "39480","Health")
        val policy3 = PolicyItem("abcd", "nwekh2312", " 21 March", "39480","Health")
        val policy4 = PolicyItem("abcd", "wdfwei334", " 21 Feb", "39480","Health")

        policy.add(policy1)
        policy.add(policy2)
        policy.add(policy3)
        policy.add(policy4)

        return policy
    }

}