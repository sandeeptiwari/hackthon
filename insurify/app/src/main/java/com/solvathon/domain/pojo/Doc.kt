package com.solvathon.domain.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doc(val id: Int, val docType: String?,
               val path: String?, val name: String?): Parcelable
