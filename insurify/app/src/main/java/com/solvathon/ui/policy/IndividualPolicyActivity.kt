package com.solvathon.ui.policy
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.IndividualPolicyPageBinding
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class IndividualPolicyActivity() :BaseActivity(){
    lateinit var binding: IndividualPolicyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Policy"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        val policy = intent.getParcelableExtra<Policy>("INDIVIDUAL_POLICY_DATA")
        findViewById<TextView>(R.id.individualpolicytype).text = policy.insuranceType
        findViewById<TextView>(R.id.policyId).text=policy.policyId.toString()
        findViewById<TextView>(R.id.individualpolicyPremium).text=policy.premium.toString()
        findViewById<TextView>(R.id.taxes).text=policy.taxes.toString()
        findViewById<TextView>(R.id.fees).text=policy.fees.toString()
        findViewById<TextView>(R.id.effectiveDate).text=policy.effectiveDate.toString()



    }



    override fun findContentView(): Int {
        return R.layout.individual_policy_page
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = IndividualPolicyPageBinding.bind(view)
    }

    override fun toggleLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}