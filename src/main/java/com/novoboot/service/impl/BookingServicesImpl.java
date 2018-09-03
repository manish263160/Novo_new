package com.novoboot.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novoboot.Enums.CommonEnums;
import com.novoboot.dao.BookingDetailsDao;
import com.novoboot.dao.ServicesDao;
import com.novoboot.model.BookingDetails;
import com.novoboot.model.DateTimeSlots;
import com.novoboot.model.Mail;
import com.novoboot.model.ServiceCategory;
import com.novoboot.model.ServiceCost;
import com.novoboot.model.ServiceEnquire;
import com.novoboot.model.ServiceModel;
import com.novoboot.model.TimeDurationMaster;
import com.novoboot.service.BookingService;
import com.novoboot.service.MailerService;
import com.novoboot.service.VelocityEmailTemplateService;

@Service
public class BookingServicesImpl implements BookingService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7044444829085541776L;
	@Autowired
	private ServicesDao servicesDao;
	@Autowired
	private VelocityEmailTemplateService velocityService;
	@Autowired
	private MailerService mailerService;

	private static final Logger logger = Logger.getLogger(BookingServicesImpl.class);

	@Value("${sms.url}")
	private String smsServiceUrl;

	@Value("${sms.authkey}")
	private String smsauthkey;

	public List<ServiceCategory> getAllServicesDetails() {
		return servicesDao.getAllServicesDetails();
	}

	public List<ServiceCategory> getAllServiceCategories() {
		return servicesDao.getAllServiceCategories();
	}

	public List<ServiceModel> getAllServicsByCatId(long categoryId) {
		return servicesDao.getAllServicesByCatId(categoryId);
	}

	public List<ServiceModel> getAllServices() {
		List<ServiceModel> services = servicesDao.getAllServices();
		if (services != null && services.size() > 0) {
			for (ServiceModel service : services) {
				if (CommonEnums.ServiceType.BOOK.type() == service.getServiceType()) {
					service.setServiceCosts(getServicesCostById(service.getId()));
				}
			}
		}
		return services;
	}

	public List<ServiceCost> getServicesCostById(long serviceId) {
		return servicesDao.getServicesCostById(serviceId);
	}

	@Transactional(rollbackFor = Throwable.class)
	public void bookOrEnquireService(ServiceEnquire enquire) {
		servicesDao.bookOrEnquireService(enquire);
		String sender = "NOVOWS";
		if (enquire.getId() > 0) {

			String serviceDat = "";
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date curdate = new Date();
				serviceDat = sdf.format(enquire.getServiceDate());
			} catch (Exception e1) {
				logger.info("exception on msg making =" + e1);
			}

			com.novoboot.model.ServiceModel service = servicesDao.getServiceById(enquire.getServiceId());
			String serviceHeading = null;
			String insideHeading = null;
			int catId = enquire.getCatId();

			if (catId == 1 || catId == 5) {
				serviceHeading = "Booking";
				insideHeading = "booking";
			} else {
				serviceHeading = "Request";
				insideHeading = "requesting";
			}
			String sms = "Thank you for " + insideHeading + " a service with Novowash. Your " + serviceHeading + " for "
					+ service.getServiceName() + "  service is confirmed at " + serviceDat
					+ " time and our team will get back to you shortly. Kindly call on 011-45073279 for any queries. ";

			if (null != service) {

				try {
					// Your authentication key
					String authkey = smsauthkey;
					// Multiple mobiles numbers separated by comma
					String mobiles = enquire.getPhone();
					// Sender ID,While using route4 sender id should be 6 characters long.
					String senderId = sender;
					// Your message to send, Add URL encoding here.
					String message = sms;
					// define route
					String route = "4";
					URLConnection myURLConnection = null;
					URL myURL = null;
					BufferedReader reader = null;
					String encoded_message = URLEncoder.encode(message);
					StringBuilder sbPostData = new StringBuilder(smsServiceUrl);

					sbPostData.append("authkey=" + authkey);
					sbPostData.append("&mobiles=" + mobiles);
					sbPostData.append("&message=" + encoded_message);
					sbPostData.append("&route=" + route);
					sbPostData.append("&sender=" + senderId);
					myURL = new URL(sbPostData.toString());
					logger.info("url printed is===" + myURL.toString());
					myURLConnection = myURL.openConnection();
					myURLConnection.connect();
					reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
					String response;
					while ((response = reader.readLine()) != null)
						// print response
						logger.info("sms ent is done===" + response);

					// finally close connection
					reader.close();
				} catch (Exception e) {
					logger.info("Exception on sms sending" + e);
				}

				Map<String, Object> model = new HashMap<String, Object>();
				logger.info("catId==" + catId);
				if (catId == 1 || catId == 5) {
					model.put("heading", "Booking");
					model.put("innerHeading", "booking");
				} else {
					model.put("heading", "Request");
					model.put("innerHeading", "requesting");
				}

				model.put("serviceName", service.getServiceName());
				if (enquire.getServiceDate() != null) {
					model.put("bookDate", "at " + enquire.getServiceDate());
				} else {
					model.put("bookDate", "");
				}
				String emailMessageBody = velocityService.geContentFromTemplate(model,
						"email_Templates/bookService.vm");
				Mail mail = new Mail();
				mail.setMailTo(enquire.getEmail());
				mail.setMailSubject(serviceHeading + " Confirmation");
				mail.setMailContent(emailMessageBody);
				mail.setContentType("text/html");
				mailerService.sendEmail(mail);
			}
		}
	}

	@Autowired
	private BookingDetailsDao bookingDao;

	public List<BookingDetails> getAllBooking() {
		return bookingDao.getAllBooking();

	}

	/**
	 * I this method we set the service date and time slot according to the service
	 * available to next date and time slot is available next from from current time
	 * 
	 * @param serviceId
	 * @return
	 */
	public DateTimeSlots getServiceTimeSlot(long serviceId) {
		DateTimeSlots data = bookingDao.getServiceTimeSlot(serviceId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		List<String> dateList = new ArrayList<>();
		List<String> timeSlotsList = new ArrayList<>();

		Long timeDurationId= data.getTimeDurationId();
		if(timeDurationId != null) {
			TimeDurationMaster timeduration = bookingDao.getTimeDurationMaster(timeDurationId);
		}
		return data;
	}

	/*
	 * public static URI appendUri(String uri, String appendQuery) throws
	 * URISyntaxException { URI oldUri = new URI(uri);
	 * 
	 * String newQuery = oldUri.getQuery(); if (newQuery == null) { newQuery =
	 * appendQuery; } else { newQuery += "&" + appendQuery; }
	 * 
	 * URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
	 * oldUri.getPath(), newQuery, oldUri.getFragment());
	 * 
	 * return newUri; }
	 */
}
