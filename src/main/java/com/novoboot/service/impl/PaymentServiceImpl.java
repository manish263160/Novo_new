package com.novoboot.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.Enums.CommonEnums.BOOKINGSTATUS;
import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.PaymentDao;
import com.novoboot.model.User;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.service.PaymentService;
import com.novoboot.service.UserService;
import com.novoboot.utils.ApplicationProperties;
import com.novoboot.wraper.model.Payment;
import com.novoboot.wraper.model.PaymentOrder;
import com.novoboot.wraper.model.WebHookModel;
import com.novoboot.wrapper.api.Instamojo;
import com.novoboot.wrapper.api.InstamojoImpl;
import com.novoboot.wrapper.exception.ConnectionException;
import com.novoboot.wrapper.exception.InvalidPaymentOrderException;
import com.novoboot.wrapper.response.CreatePaymentOrderResponse;
import com.novoboot.wrapper.response.PaymentOrderDetailsResponse;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);

	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	PaymentDao paymentDao;

	@Autowired
	UserService userService;
	/**
	 * This method will get the api related details with authtoken
	 */
	@Override
	public Instamojo getApi() {
		Instamojo api = null;
		try {
			// gets the reference to the instamojo api

			String clientId = applicationProperties.getProperty("instamojo_clientid");
			String clientSecret = applicationProperties.getProperty("instamojo_clientSecret");
			String apiEndPOint = applicationProperties.getProperty("instamojo_apiEndPoint");
			String authEndPoint = applicationProperties.getProperty("instamojo_authEndPoint");
			api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPOint, authEndPoint);
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return api;
	}

	@Override
	public CreatePaymentOrderResponse createNewPaymentOrder(Instamojo api, PaymentOrder order) {
		try {
			CreatePaymentOrderResponse createPaymentOrderResponse = api.createNewPaymentOrder(order);
			// print the status of the payment order.
			logger.info("createPaymentOrderResponse :: " + createPaymentOrderResponse.getPaymentOrder().getStatus());
			return createPaymentOrderResponse;
		} catch (InvalidPaymentOrderException e) {
			logger.error(e.toString());

			if (order.isTransactionIdInvalid()) {
				logger.error("Transaction id is invalid. This is mostly due to duplicate transaction id.");
			}
			if (order.isCurrencyInvalid()) {
				logger.error("Currency is invalid.");
			}
		} catch (ConnectionException e) {
			logger.error(e.toString(), e);
		}
		return null;
	}

	@Override
	@Transactional
	public void inserstPaymentSuccessFull(WebHookModel webHookModel) {
		
		
		String paymentId = webHookModel.getPayment_id();
		String PaymentReqstId = webHookModel.getPayment_request_id();
		logger.info("payment Id and paymentRequest id =="+paymentId +" & "+PaymentReqstId);
		PaymentOrderDetailsResponse paymentOrderDetailsResponse =null;
		try {
			paymentOrderDetailsResponse = getApi().getPaymentOrderDetails(PaymentReqstId);
			
			logger.info("paymentOrderDetailsResponse==="+paymentOrderDetailsResponse.toString());
			String orderStatus = paymentOrderDetailsResponse.getStatus();
			webHookModel.setStatus(orderStatus);
			String userMobile = paymentOrderDetailsResponse.getPhone();
			logger.info("mobile number before=="+userMobile);
			if(userMobile != null) {
				if(userMobile.contains("+91")) {
					logger.info("mobile number contains +91");
					userMobile = userMobile.substring(3);
					webHookModel.setBuyer_phone(userMobile);
				}
				logger.info("mobile number after=="+userMobile);
					User user= userService.findUserByMobile(userMobile);
					if(user != null) {
						webHookModel.setUserId(user.getId());
					}
			}
			Payment[] payment = paymentOrderDetailsResponse.getPayments();
			if (paymentOrderDetailsResponse.getId() != null) {
				// print the status of the payment order.
				logger.info(paymentOrderDetailsResponse.getStatus());
				if(payment != null) {
					paymentDao.updateUserBookingDetails(paymentId,PaymentReqstId,orderStatus);
				}
//				paymentOrderDetailsResponse.get
			} else {
				logger.error(" order id is invalid.");
			}
			webHookModel.setBuyer(paymentOrderDetailsResponse.getEmail());
			webHookModel.setBuyer_name(paymentOrderDetailsResponse.getName());
		} catch (ConnectionException e) {
			logger.error(e.toString(), e);
		}
		
		paymentDao.inserstPaymentSuccessFull(webHookModel);

	}

	@Override
	public CreatePaymentOrderResponse userBooking(String paymentOrder) {
		CreatePaymentOrderResponse createPaymentOrderResponse = null;
		JSONObject json = null;
		try {
			json = new JSONObject(paymentOrder);
			if (json != null) {
				String name = json.getString("name");
				String email = json.getString("email");
				String phone = json.getString("phone");
				String currency = json.getString("currency");
				double amount = json.getDouble("amount");
				String description = json.getString("description");
				String successUrl = json.getString("successUrl");
				String failUrl = json.getString("failUrl");
				List<String> serviceCostIdList = new ArrayList<>();

				JSONObject selectedServices = (JSONObject) json.get("selectedServices");
				Long serviceCatId = ((JSONObject) selectedServices.get("mainService")).getLong("id");
				String serviceCatName = ((JSONObject) selectedServices.get("mainService")).getString("catName");
				Long serviceMasterId = ((JSONObject) selectedServices.get("subService")).getLong("id");
				String serviceName = ((JSONObject) selectedServices.get("subService")).getString("serviceName");
				Map<String, Integer> serviceComboPackage = new LinkedHashMap<>();
				Map<String, Integer> extraservices = new LinkedHashMap<>();
				if (selectedServices != null) {
					JSONArray mainPackages = null;
					JSONArray extraPackages = null;
					if (serviceCatId != 6) {
						mainPackages = selectedServices.getJSONArray("mainPackages");
						extraPackages = selectedServices.getJSONArray("extraPackages");
						extracted(mainPackages, serviceComboPackage, serviceCostIdList);
						extracted(extraPackages, extraservices, serviceCostIdList);

						logger.info("serviceComboPackage===" + serviceComboPackage.toString());
						logger.info("extraservices===" + extraservices.toString());
					} else {
						mainPackages = new JSONArray();
						mainPackages.put(selectedServices.getJSONArray("type"));
						mainPackages.put(selectedServices.getJSONArray("subType"));
						extracted(mainPackages, serviceComboPackage, serviceCostIdList);
					}
				}
//				logger.info("servicCat==="+cost);

				String couponApplied = selectedServices.getString("couponApplied");
				Long userId = selectedServices.getLong("userid");
				JSONObject dateUserDetails = (JSONObject) selectedServices.get("dateUserDetails");
				String userAddress = dateUserDetails.getString("address");
				int pinCode = ((JSONObject) dateUserDetails.get("address")).getInt("pincode");
				String city = dateUserDetails.getString("city");
				String bookingDate = dateUserDetails.getString("date");
				String bookingTime = dateUserDetails.getString("time");
				JSONObject contactDetails = (JSONObject) dateUserDetails.get("contactDetails");

				logger.info("request param ::" + paymentOrder.toString());

				PaymentOrder order = new PaymentOrder();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
				String transactionId = phone + "_" + sdf.format(new Date());

//				-----------------------------Below this line the code for instamojo payment URl creation--------------------------------------------------------------------------------------
				order.setName(name);
				order.setEmail(email);
				order.setPhone(phone);
				order.setCurrency(currency);
				order.setAmount(10.0d);
				order.setDescription(description);
//				order.setRedirectUrl(applicationProperties.getProperty("instamojo_redirecturl"));
				order.setWebhookUrl(applicationProperties.getProperty("instamojo_webhookurl"));
				order.setTransactionId(transactionId);

				Instamojo api = getApi();

				boolean isOrderValid = order.validate();
				if (isOrderValid) {
					try {
						createPaymentOrderResponse = createNewPaymentOrder(api, order);
					} catch (Exception e) {
						logger.error(e.toString());
						if (order.isTransactionIdInvalid()) {
							logger.error("Transaction id is invalid. This is mostly due to duplicate transaction id.");
						}
						if (order.isCurrencyInvalid()) {
							logger.error("Currency is invalid.");
						}
					}
				} else {
					if (order.isTransactionIdInvalid()) {
						logger.error("Transaction id is invalid.");
					}
					if (order.isAmountInvalid()) {
						logger.error("Amount can not be less than 9.00.");
					}
					if (order.isCurrencyInvalid()) {
						logger.error("Please provide the currency.");
					}
					if (order.isDescriptionInvalid()) {
						logger.error("Description can not be greater than 255 characters.");
					}
					if (order.isEmailInvalid()) {
						logger.error("Please provide valid Email Address.");
					}
					if (order.isNameInvalid()) {
						logger.error("Name can not be greater than 100 characters.");
					}
					if (order.isPhoneInvalid()) {
						logger.error("Phone is invalid.");
					}
					if (order.isRedirectUrlInvalid()) {
						logger.error("Please provide valid Redirect url.");
					}

					if (order.isWebhookInvalid()) {
						logger.error("Provide a valid webhook url");
					}
				}
				if (createPaymentOrderResponse != null && createPaymentOrderResponse.getPaymentOrder() != null) {
					String PaymnetRequestId = createPaymentOrderResponse.getPaymentOrder().getId();
					UserBookingDetails userBookingDetails = new UserBookingDetails(PaymnetRequestId, transactionId,
							userId, name, phone, email, description, successUrl, failUrl, serviceCatId, serviceMasterId,
							serviceCostIdList.toString(), serviceCatName, serviceName, serviceComboPackage.toString(),
							extraservices.toString(), STATUS.INACTIVE.ID, amount, couponApplied, userAddress, pinCode,
							city, bookingDate, bookingTime, BOOKINGSTATUS.INACTIVE.getBookingStatus());

					paymentDao.insertUserBooking(userBookingDetails);
				}

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createPaymentOrderResponse;
	}

	public static void extracted(JSONArray mainPackages, Map<String, Integer> comboPackage,
			List<String> serviceCostIdList) throws JSONException {
		for (int i = 0; i < mainPackages.length(); i++) {
			JSONObject objectInArray = mainPackages.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			logger.info("ELEMENTS IN CURRENT OBJECT:" + elementNames.length);
			String key = "";
			Integer value = null;
			for (String elementName : elementNames) {

				if (elementName.equals("userInputs")) {
					key = objectInArray.getString(elementName);
				}
				if (elementName.equals("selectedValue")) {
					value = objectInArray.getInt(elementName);
				}
				if (elementName.equals("id")) {
					serviceCostIdList.add(objectInArray.getString(elementName));
				}
				logger.info("key=" + key + " , value=" + value);
			}
			comboPackage.put(key, value);
		}
	}

	@Override
	public boolean onPaymentSuccessHandler(String paymentId, String status) {
		if(paymentId != null) {
			
		}
		return false;
	}

	@Override
	public boolean onPaymentFailureHandler(String paymentId, String status) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
