package com.grappim.cashier.domain.waybill

import android.os.Parcelable
import com.grappim.cashier.ui.waybill.WaybillStatus
import com.grappim.cashier.ui.waybill.WaybillType
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Waybill(
    val id: Int,
    val createdOn: String,
    val merchantId: String,
    val number: String,
    val status: WaybillStatus,
    val stockId: String,
    val totalCost: BigDecimal,
    val type: WaybillType,
    val updatedOn: String,
    var reservedTime: String?,
    val comment: String,
    val updateOnToDemonstrate: String
) : Parcelable