package com.solvathon.ui.policy
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.ActivityPolicyBinding
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PolicyActivity() :BaseActivity(){
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var binding: ActivityPolicyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Policy"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val policies = intent.getParcelableArrayListExtra<Policy>("POLICY_DATA")
        setUpRecycleView(policies)
    }

    fun setUpRecycleView(policies: ArrayList<Policy>?) {
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerViewMyPolicy.apply {
            layoutManager = linearLayoutManager
            adapter = policies?.let { MyPolicyAdapter(it) }
        }
    }

    override fun findContentView(): Int {
        return R.layout.activity_policy
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityPolicyBinding.bind(view)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}