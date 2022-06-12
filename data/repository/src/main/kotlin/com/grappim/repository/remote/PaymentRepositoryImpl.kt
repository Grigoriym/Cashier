package com.grappim.repository.remote

import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.db.dao.BasketDao
import com.grappim.domain.interactor.payment.MakePaymentParams
import com.grappim.domain.repository.PaymentRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.logger.logE
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.model.payment.CreateOrderDTO
import com.grappim.network.model.payment.CreateOrderRequestDTO
import com.grappim.network.model.payment.OrderItemDTO
import com.grappim.common.asynchronous.doOnError
import com.grappim.common.asynchronous.runOperationCatching
import javax.inject.Inject

@AppScope
class PaymentRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val basketDao: BasketDao,
    private val generalStorage: GeneralStorage
) : PaymentRepository {

    override suspend fun makePayment(
        params: MakePaymentParams
    ): Try<Unit, Throwable> = runOperationCatching {
        val paymentType = params.paymentMethodType

        val totalSum = basketDao.getBasketProducts().map {
            it.sellingPrice * it.amount
        }.sumOf {
            it
        }

        val orderItems = basketDao.getBasketProducts().map {
            OrderItemDTO(
                productId = it.id,
                amount = it.amount,
                sellingPrice = it.sellingPrice,
                purchasePrice = it.sellingPrice,
                barcode = it.barcode,
                name = it.name
            )
        }

        cashierApi.createOrder(
            CreateOrderRequestDTO(
                order = CreateOrderDTO(
                    merchantId = generalStorage.getMerchantId(),
                    stockId = generalStorage.stockId,
                    cashBoxId = generalStorage.cashBoxId,
                    totalSum = totalSum,
                    payType = paymentType.name,
                    orderItems = orderItems
                )
            )
        )
        basketDao.deleteBagProducts()
    }.doOnError { e ->
        logE(e)
    }

}