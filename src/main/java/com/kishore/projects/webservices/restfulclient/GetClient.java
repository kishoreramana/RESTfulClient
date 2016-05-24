package com.kishore.projects.webservices.restfulclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetClient {

	/*private static final String GOOGLE_TIMEZONE_API_URL = "https://maps.googleapis.com/maps/api/timezone/json?location=39.6034810,-119.6822510&timestamp=1331161200&key=YOUR_API_KEY";
	private static final String COUNTRY_REQUEST_URL = "http://services.groupkt.com/state/get/ind/all";
	private static final String GEO_REQUEST_URL = "http://geo.groupkt.com/ip/192.168.0.4/json";
	*/
	private static final String MKTDATA_GET_HISTORY_REQUEST_URL = "http://marketdata.websol.barchart.com/getHistory.json"
			+ "?key=67f723c12b7a75d733b8eca95fd70cf2&symbol=IBM&type=daily&startDate=20150428000000";
	private static final String MKTDATA_GET_DATA_REQUEST_URL = "http://marketdata.websol.barchart.com/getHistory.json"
			+ "?key=67f723c12b7a75d733b8eca95fd70cf2&symbol=IBM&type=daily&startDate=20150428000000";
	private static final String LOCAL_REST_SERVICE_URL = "http://localhost:8080/RESTfulService/webapi/messages/all";
	
	public static void main(String[] args) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(MKTDATA_GET_DATA_REQUEST_URL);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
