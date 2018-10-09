package com.novoboot.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novoboot.Enums.CommonEnums;
import com.novoboot.dao.BookingDetailsDao;
import com.novoboot.dao.ServicesDao;
import com.novoboot.model.BookingDetails;
import com.novoboot.model.Mail;
import com.novoboot.model.ServiceCategory;
import com.novoboot.model.ServiceCost;
import com.novoboot.model.ServiceDateSlot;
import com.novoboot.model.ServiceEnquire;
import com.novoboot.model.ServiceModel;
import com.novoboot.model.ServiceTimeSlot;
import com.novoboot.service.BookingService;
import com.novoboot.service.MailerService;
import com.novoboot.service.VelocityEmailTemplateService;
import com.novoboot.utils.GenUtilities;

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

	/*public List<ServiceCategory> getAllServicesDetails() {
		return servicesDao.getAllServicesDetails();
	}*/

	public List<ServiceCategory> getAllServiceCategories() {
		return servicesDao.getAllServiceCategories();
	}

	public List<ServiceModel> getAllServicsByCatId(long categoryId) {
		return servicesDao.getAllServicesByCatId(categoryId);
	}

	public List<ServiceModel> getAllServices() {
		List<ServiceModel> services = servicesDao.getAllServices(null);
		/*if (services != null && services.size() > 0) {
			for (ServiceModel service : services) {
				if (CommonEnums.ServiceType.BOOK.type() == service.getServiceType()) {
					service.setServiceCosts(getServicesCostById(service.getId()));
				}
			}
		}*/
		return services;
	}

	public List<ServiceCost> getServicesCostById(long serviceId) {
		List<ServiceCost> list= servicesDao.getServicesCostById(serviceId);
		
		return list;
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
	public Map<String, Map<String, Boolean>> getServiceDateTimeSlot(long serviceId) {

		Map<String, Map<String, Boolean>> returnObject = new LinkedHashMap<>();

		ServiceDateSlot serviceDateSlotData = bookingDao.getServiceDateSlot(serviceId);

		 List<ServiceTimeSlot> timeSlotList= bookingDao.getAllTImeSlot();
		 
		List<String> timeSlotsList = new ArrayList<>();

		try {
			if (serviceDateSlotData != null) {
				Calendar cal = Calendar.getInstance();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				SimpleDateFormat timeFormatAmPm = new SimpleDateFormat("hh a");
				
				SimpleDateFormat timeFormat24 = new SimpleDateFormat("HH:mm:ss");
				

				logger.info("cal===" + sdf.format(cal.getTime()));

				Long nextNumberOfDays = serviceDateSlotData.getNextNoOfDayAvailable();

//				List<String> dateList = new ArrayList<>();

				List<String> nextDisabledDateNumberList = new ArrayList<>();

				if (serviceDateSlotData.getNextDisabledDates() != null
						|| !serviceDateSlotData.getNextDisabledDates().isEmpty()) {
					
					String nextDisabledDateNumberString = serviceDateSlotData.getNextDisabledDates();
					
					if(nextDisabledDateNumberString != null)
					nextDisabledDateNumberList = Arrays.asList(nextDisabledDateNumberString.trim().replaceAll(" ", "").split("##"));
				}
				
				logger.debug("nextDisabledDateNumberList ="+nextDisabledDateNumberList.toString());
				Date currentDate= new Date();
				if(!nextDisabledDateNumberList.contains(sdf.format(currentDate))) {
				fetchTimeSlots(returnObject, timeSlotList, timeFormatAmPm, timeFormat24, sdf.format(currentDate) /*, timeFormat24.format(currentDate)*/);
				}
				
				for (int i = 1; i <= nextNumberOfDays.longValue(); i++) {

					cal.add(Calendar.DATE, 1);
					
					String newDate = sdf.format(cal.getTime());
					if(nextDisabledDateNumberList != null && !nextDisabledDateNumberList.isEmpty() && nextDisabledDateNumberList.contains(newDate)) {
						
//						dateList.add(newDate);
						returnObject.put(newDate+"##false", null);
						continue;
					}else {
//						dateList.add(newDate);
//						String currentTime = timeFormat24.format(new Date());
						fetchTimeSlots(returnObject, timeSlotList, timeFormatAmPm, timeFormat24, newDate /*, currentTime*/);
					}
				}
				
					logger.debug("dateList ===" + returnObject);

			}
		} catch (DataAccessException | ParseException e) {
			logger.error(e.getMessage());
		}
		return returnObject;
	}

	private void fetchTimeSlots(Map<String, Map<String, Boolean>> returnObject, List<ServiceTimeSlot> timeSlotList,
			SimpleDateFormat timeFormatAmPm, SimpleDateFormat timeFormat24, String newDate /*,String currentTime*/) throws ParseException {
		Map<String, Boolean> map = new LinkedHashMap<>();
		for (ServiceTimeSlot serviceTimeSlot : timeSlotList) {
			String startTime= serviceTimeSlot.getStartTime();
			String endTime = serviceTimeSlot.getEndTime();
			String key = timeFormatAmPm.format(timeFormat24.parse(startTime))+" - "+ timeFormatAmPm.format(timeFormat24.parse(endTime)); 
			try {
				String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				boolean bool = true;
				if(newDate.equals(currentDate))
					bool= 	GenUtilities.isTimeBetweenTwoTime(startTime , endTime /*, currentTime*/);
				
			map.put(key, bool);
			} catch (ParseException e) {
				logger.error("error :: "+ e.getMessage());
			}
			
		}
		Set<Boolean> setValue= new HashSet<Boolean>(map.values());
		if(setValue.size() == 1 && setValue.contains(false)) {
			returnObject.put(newDate+"##false", map);	
		}else {
			returnObject.put(newDate+"##true", map);
		}
//		 returnObject.put(newDate, map);
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
