package com.solvathon.ui.claims

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity

class ClaimActivity: BaseActivity() {

    lateinit var documentView: RecyclerView
    lateinit var claimView: CardView
    lateinit var historyView: RecyclerView
    lateinit var billingView: RecyclerView
    lateinit var patientView: CardView
    lateinit var toggleButton: MaterialButtonToggleGroup

    override fun findContentView(): Int {
        return R.layout.claim_screen
    }

    override fun bindViewWithViewBinding(view: View) {

    }

    override fun toggleLoader(b: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        findViewById<TextView>(R.id.text_view_name).text = intent.getStringExtra("name")
        findViewById<TextView>(R.id.text_view_claim_id).text = intent.getStringExtra("claimId")
        findViewById<TextView>(R.id.text_view_date_of_admission).text = intent.getStringExtra("dateOfAdmission")
        findViewById<TextView>(R.id.text_view_claim_amount).text = intent.getStringExtra("claimAmount")

        documentView = findViewById(R.id.recycler_view_document)
        claimView = findViewById(R.id.card_view_claim_info)
        historyView = findViewById(R.id.recycler_view_history)
        billingView = findViewById(R.id.recycler_view_billing)
        patientView = findViewById(R.id.card_view_patient_info)

        toggleButton =   findViewById(R.id.materialButtonToggleGroup)
        toggleButton.check(R.id.document_btn)
        toggleButton.addOnButtonCheckedListener{ toggleButton, checkedId, isChecked ->
            if(isChecked){
                switchTab(checkedId)
            }
        }

        val documents = getDocuments()
        val historyList = getHistory()
        val bills = getBills()

        val documentRecyclerView:RecyclerView = findViewById(R.id.recycler_view_document)
        documentRecyclerView.adapter = DocumentAdapter(documents)
        documentRecyclerView.layoutManager = LinearLayoutManager(this)

        val historyRecyclerView:RecyclerView = findViewById(R.id.recycler_view_history)
        historyRecyclerView.adapter = HistoryAdapter(historyList)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        val billingRecyclerView : RecyclerView = findViewById(R.id.recycler_view_billing)
        billingRecyclerView.adapter = BillingAdapter(bills)
        billingRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun getDocuments():List<String>{
        return listOf("Aadhar card", "Passport", "Driving license")
    }

    fun getHistory():List<HistoryItem>{
        return listOf(
            HistoryItem("21 Jan 2020", R.drawable.ic_checkbox_checked,"issued"),
        HistoryItem("3 March 2020", R.drawable.ic_checkbox_checked, "verrified"),
        HistoryItem("21 April", R.drawable.ic_checkbox_unchecked, "processed")
        )
    }

    fun getBills() : List<BillingItem>{
        return listOf(
            BillingItem("Pathology (Biopsy)","0", "8347", "8347"),
            BillingItem("Room Rent","0", "4800", "4800"),
        )
    }


    private fun switchTab(id : Int){
        when(id){
            R.id.document_btn -> documentBtnClicked()
            R.id.claim_btn -> claimBtnClicked()
            R.id.history_btn -> historyBtnClicked()
            R.id.bill_btn -> billingBtnClicked()
            R.id.patient_btn -> patientBtnClicked()
        }
    }

    private fun patientBtnClicked() {
        toggleButton.check(R.id.patient_btn)
        documentView.visibility = View.GONE
        claimView.visibility = View.GONE
        historyView.visibility = View.GONE
        billingView.visibility = View.GONE
        patientView.visibility = View.VISIBLE

    }

    private fun billingBtnClicked() {
        toggleButton.check(R.id.bill_btn)
        documentView.visibility = View.GONE
        claimView.visibility = View.GONE
        historyView.visibility = View.GONE
        billingView.visibility = View.VISIBLE
        patientView.visibility = View.GONE
    }

    private fun historyBtnClicked() {
        toggleButton.check(R.id.history_btn)
        documentView.visibility = View.GONE
        claimView.visibility = View.GONE
        historyView.visibility = View.VISIBLE
        billingView.visibility = View.GONE
        patientView.visibility = View.GONE
    }

    private fun claimBtnClicked() {
        toggleButton.check(R.id.claim_btn)
        documentView.visibility = View.GONE
        claimView.visibility = View.VISIBLE
        historyView.visibility = View.GONE
        billingView.visibility = View.GONE
        patientView.visibility = View.GONE
    }

    private fun documentBtnClicked() {
        toggleButton.check(R.id.document_btn)
        documentView.visibility = View.VISIBLE
        claimView.visibility = View.GONE
        historyView.visibility = View.GONE
        billingView.visibility = View.GONE
        patientView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}