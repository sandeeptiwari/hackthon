package com.solvathon.domain.pojo

import java.math.BigDecimal
import java.time.LocalDate

data class Policy(
    val policyId: Long, val premium: BigDecimal, val taxes: BigDecimal,
    val fees: BigDecimal, val effectiveDate: LocalDate,
    val policyExpirationDate: LocalDate, val insuranceType: String,
    val policyYear: Int
)
