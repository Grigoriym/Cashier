package com.grappim.repository.remote

import com.grappim.common.asynchronous.doOnError
import com.grappim.common.asynchronous.mapSuccess
import com.grappim.common.asynchronous.runOperationCatching
import com.grappim.common.di.AppScope
import com.grappim.common.lce.Try
import com.grappim.feature.bag.db.BasketDao
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.payment_method.domain.interactor.MakePaymentParams
import com.grappim.feature.payment_method.domain.repository.PaymentRepository
import com.grappim.logger.logE
import com.grappim.network.api.CashierApi
import com.grappim.network.di.api.QualifierCashierApi
import com.grappim.network.model.payment.CreateOrderDTO
import com.grappim.network.model.payment.CreateOrderRequestDTO
import com.grappim.network.model.payment.OrderItemDTO
import javax.inject.Inject

@AppScope
class PaymentRepositoryImpl @Inject constructor(
    @QualifierCashierApi private val cashierApi: CashierApi,
    private val basketDao: com.grappim.feature.bag.db.BasketDao,
    private val generalStorage: GeneralStorage
) : PaymentRepository {

    override suspend fun makePayment(
        params: MakePaymentParams
    ): Try<Unit, Throwable> = runOperationCatching {
        basketDao.getBasketProducts().map {
            it.sellingPrice * it.amount
        }.sumOf {
            it
        }
    }.mapSuccess { totalSum ->
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

        val paymentType = params.paymentMethodType
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
