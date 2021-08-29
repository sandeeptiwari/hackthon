package com.solvathon.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.solvathon.core.AppPreferences
import com.solvathon.domain.pojo.Policy
import com.solvathon.ui.home.HomeViewModel
import java.math.BigDecimal
import java.time.LocalDate
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    companion object {
        const val TAG = "BaseFragment"
    }

    val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(createLayout(), container, false)
        bindViewWithViewBinding(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
    }

    protected abstract fun bindData()

    protected abstract fun createLayout(): Int

    /**
     * This method is used for binding view with your binding
     */
    protected abstract fun bindViewWithViewBinding(view: View)

    fun onError(throwable: Throwable) {
        throwable.message?.let { Log.i(TAG, it) }
    }

    fun toggleLoader(isLoader: Boolean) {}

    abstract fun showLoader(b: Boolean)

    fun fetchPolicyBasedOnLob(pos: Int):List<Policy>?{
        when(pos){
            0->{
                //fetch health insurance by login id
                var policies = listOf(
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Health Insurance", 2000
                    ),
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Health Insurance", 2000
                    ),)
                return policies
            }
            1->{
                //fetch health insurance by login id
                var policies = listOf(
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Car Insurance", 2000
                    ),
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Car Insurance", 2000
                    ),)
                return policies
            }
            2->{
                //fetch health insurance by login id
                var policies = listOf(
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Travel Insurance", 2000
                    ),
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Travel Insurance", 2000
                    ),)
                return policies
            }
            3->{
                //fetch health insurance by login id
                var policies = listOf(
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Travel Insurance", 2000
                    ),
                    Policy(
                        1000, BigDecimal(1750), BigDecimal(150), BigDecimal(100),
                        LocalDate.now(), LocalDate.now(), "Travel Insurance", 2000
                    ),)
                return policies
            }
        }
        return null
    }
}