package com.grappim.repository.remote

import com.grappim.cashier.common.di.AppScope
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.common.lce.doOnError
import com.grappim.cashier.common.lce.mapSuccess
import com.grappim.cashier.common.lce.runOperationCatching
import com.grappim.cashier.feature.paymentmethod.domain.interactor.MakePaymentParams
import com.grappim.cashier.feature.paymentmethod.domain.repository.PaymentRepository
import com.grappim.domain.storage.GeneralStorage
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

    override suspend fun makePayment(params: MakePaymentParams): Try<Unit, Throwable> =
        runOperationCatching {
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
