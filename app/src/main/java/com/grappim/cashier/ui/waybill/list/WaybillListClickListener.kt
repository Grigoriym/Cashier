package com.grappim.cashier.ui.waybill.list

import com.grappim.cashier.domain.waybill.Waybill

interface WaybillListClickListener {

    fun onWaybillClick(waybill: Waybill)

}