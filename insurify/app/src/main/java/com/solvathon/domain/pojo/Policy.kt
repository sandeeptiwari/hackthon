package com.solvathon.domain.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.time.LocalDate

@Parcelize
data class Policy(
    val policyId: Long, val premium: BigDecimal, val taxes: BigDecimal,
    val fees: BigDecimal, val effectiveDate: LocalDate,
    val policyExpirationDate: LocalDate, val insuranceType: String,
    val policyYear: Int
): Parcelable
