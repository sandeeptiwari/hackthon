package com.solvathon.ui.locker.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solvathon.R
import com.solvathon.databinding.ActivityUploadDocumentBinding
import com.solvathon.domain.pojo.Doc
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.base.BaseActivity
import com.solvathon.ui.home.adapter.PolicyLobsAdapter
import com.solvathon.ui.locker.adapter.DocAdapter

class UploadDocumentActivity : BaseActivity(), DocAdapter.OnItemClickListener {
    lateinit var binding: ActivityUploadDocumentBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    private val docs: ArrayList<Doc> = ArrayList()

    private val docListAdapter by lazy {
        DocAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //actionbar
        val actionbar = supportActionBar

        //set back button
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        handleUploadDocument()
        val docType = intent.getStringExtra("DOC_TYPE")

        setUpDocRecyclerView()
        //set actionbar title
        actionbar!!.title = "$docType Documents"
    }

    private fun handleUploadDocument() {
        binding.btnaAddDocument.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            val bundle = Bundle()
            bundle.putString("DOC_TYPE", "ID And Address ")
            intent.putExtras(bundle)
            startActivityForResult(intent, 1001)
        }
    }

    override fun findContentView(): Int {
        return R.layout.activity_upload_document
    }

    override fun bindViewWithViewBinding(view: View) {
        binding = ActivityUploadDocumentBinding.bind(view)
    }

    override fun toggleLoader(b: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onItemClick(pos: Int) {
    }

    private fun setUpDocRecyclerView() {
        Log.d("TAG", "doc >" + docs.size)
        linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.rvDocumentScreen.apply {
            layoutManager = linearLayoutManager
            adapter = docListAdapter
        }
        docListAdapter?.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            val doc = data?.getParcelableExtra<Doc>("DOC")
            if (doc != null) {
                docListAdapter.add(doc)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}