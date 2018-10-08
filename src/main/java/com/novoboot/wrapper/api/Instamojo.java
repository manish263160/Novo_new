package com.novoboot.wrapper.api;

import com.novoboot.wraper.model.PaymentOrder;
import com.novoboot.wraper.model.PaymentOrderFilter;
import com.novoboot.wraper.model.Refund;
import com.novoboot.wrapper.exception.ConnectionException;
import com.novoboot.wrapper.exception.InvalidPaymentOrderException;
import com.novoboot.wrapper.exception.InvalidRefundException;
import com.novoboot.wrapper.response.CreatePaymentOrderResponse;
import com.novoboot.wrapper.response.CreateRefundResponse;
import com.novoboot.wrapper.response.PaymentOrderDetailsResponse;
import com.novoboot.wrapper.response.PaymentOrderListResponse;
import com.novoboot.wrapper.response.PaymentReceiveModel;

/**
 * The Interface Instamojo.
 */
public interface Instamojo {

    /**
     * Initiates a payment order with Instamojo.
     * There can only be one Instamojo payment order for a transaction in your system (transaction in your system being identified by transaction_id)
     *
     * @param paymentOrder the payment order
     * @return the creates the payment order response
     * @throws ConnectionException          the connection exception
     * @throws InvalidPaymentOrderException the invalid payment order exception
     */
    CreatePaymentOrderResponse createNewPaymentOrder(PaymentOrder paymentOrder) throws ConnectionException, InvalidPaymentOrderException;

    /**
     * Get the details of the specified order (identified by id).
     *
     * @param id the id
     * @return the payment order details
     * @throws ConnectionException the connection exception
     */
    PaymentReceiveModel getPaymentOrderDetails(String id) throws ConnectionException;

    /**
     * Get the details of the specified order (identified by transaction id).
     *
     * @param transactionId the transaction id
     * @return the payment order details by transaction id
     * @throws ConnectionException the connection exception
     */
    PaymentOrderDetailsResponse getPaymentOrderDetailsByTransactionId(String transactionId) throws ConnectionException;

    /**
     * Gets the payment order list.
     * This endpoint returns paginated results of all your payment orders. This endpoint also supports filtering by some parameters.
     *
     * @param paymentOrderFilter the payment order filter
     * @return the payment order list
     * @throws ConnectionException the connection exception
     */
    PaymentOrderListResponse getPaymentOrderList(PaymentOrderFilter paymentOrderFilter) throws ConnectionException;

	/**
	 * Creates the new refund.
	 *
	 * @param refund the refund
	 * @return the creates the refund response
	 * @throws ConnectionException the connection exception
	 * @throws InvalidRefundException the invalid refund exception
	 */
	CreateRefundResponse createNewRefund(Refund refund) throws ConnectionException, InvalidRefundException;
}
