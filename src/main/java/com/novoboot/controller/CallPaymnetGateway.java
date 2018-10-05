package com.novoboot.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CallPaymnetGateway {

	public static void main(String[] args) throws IOException {
		
		final String uri = "https://www.instamojo.com/api/1.1/payment-requests";
		 
		  String XApiKey = "94e7d6efe33f172196a822679527f4bb";
		  String XAuthToken ="a5cc7d3559e54d217c7cab8109ee6519";
//		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		  headers.setContentType( MediaType.APPLICATION_JSON );

//		String parameters = "allow_repeated_payments=False&amount=2500&buyer_name=John+Doe&purpose=FIFA+16&redirect_url=http%3A%2F%2Fwww.example.com%2Fredirect%2F&phone=9999999999&send_email=True&webhook=http%3A%2F%2Fwww.example.com%2Fwebhook%2F&send_sms=True&email=foo%40example.com";
		
		/*MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("allow_repeated_payments", "false");
		map.add("amount", "250");
		map.add("buyer_name", "manish kumar mandal");
		map.add("purpose", "NAMASTE");
		map.add("redirect_url", "www.hhh.com");
		map.add("phone", "8744046090");
		map.add("send_email", "8744046090");
		map.add("phone", "True");
		map.add("webhook", "www.hhh.com");
		map.add("send_sms", "True");
		map.add("email", "manish263160@gmail.com");*/
		
		  
		  try {
			URL myURL = new URL(uri);
			 HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			 conn.setRequestProperty("Content-Type", "application/json");
			 conn.setRequestProperty("X-Api-Key", XApiKey);
			 conn.setRequestProperty("X-Auth-Token", XAuthToken);
			 conn.setDoOutput(true);
			    conn.setRequestMethod("POST");
			    
			    int code = conn.getResponseCode(); // 200 = HTTP_OK
			    System.out.println("Response    (Code):" + code);
			    System.out.println("Response (Message):" + conn.getResponseMessage());
			    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		     
//		    System.out.println(result);
		  
	}
}
