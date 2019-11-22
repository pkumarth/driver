package com.queue.client.synchronous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Synchronous {
	String baseUrl = "http://localhost:8080/topic/";

	public String getData(String topic, String part) throws IOException {
		URL url = new URL(baseUrl + topic + "/" + part);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) url.openConnection();
		conection.setRequestMethod("GET");
		// conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample
		// here
		int responseCode = conection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();
			System.out.println("JSON String Result " + response.toString());
			return response.toString();
			// GetAndPost.POSTRequest(response.toString());
		} else {
			System.out.println("GET NOT WORKED");
		}
		return null;

	}

	public static void POSTRequest() throws IOException {
		final String POST_PARAMS = "{\n" + "\"topic\": \"101\",\r\n" + "    \"part\": \"part1\",\r\n"
				+ "    \"msg\": \"Test Body\"" + "\n}";
		System.out.println(POST_PARAMS);
		URL obj = new URL("http://localhost:8080/topic/");
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod("POST");
		//postConnection.setRequestProperty("userId", "a1bcdefgh");
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = postConnection.getResponseCode();
		System.out.println("POST Response Code :  " + responseCode);
		System.out.println("POST Response Message : " + postConnection.getResponseMessage());
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST NOT WORKED");
		}
	}

	public static void main(String[] args) throws IOException {
		Synchronous syn = new Synchronous();
		//syn.getData("abc", "part0");
		syn.POSTRequest();
	}

}
