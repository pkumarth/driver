package com.queue.client.asynchronous;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.asynchttpclient.Dsl.*;
import org.asynchttpclient.*;
import org.asynchttpclient.request.body.generator.InputStreamBodyGenerator;

import io.netty.handler.codec.http.HttpHeaders;

public class Asynchronous {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		Asynchronous asyn = new Asynchronous();
		// asyn.getData("abc", "part0");
		//asyn.postData();
		asyn.getData();
	}

	public void postData() throws InterruptedException, ExecutionException, IOException {
		final String POST_PARAMS = "{\n" + "\"topic\": \"abc\",\r\n" + "    \"part\": \"part0\",\r\n"
				+ "    \"msg\": \"Test Body2\"" + "\n}";
		System.out.println(POST_PARAMS);
		AsyncHttpClientConfig config = config()//
				.setConnectTimeout(100)//
				.setMaxConnections(50)//
				.setRequestTimeout(5 * 60 * 1000) // 5 minutes
				.build();

		try (AsyncHttpClient client = asyncHttpClient(config)) {
			RequestBuilder requestBuilder = post("http://localhost:8080/topic/")
					.setHeader("Content-Type", "application/json")
					.setBody(new InputStreamBodyGenerator(new ByteArrayInputStream(POST_PARAMS.getBytes())));

			Future<Response> future = client.executeRequest(requestBuilder.build());

			System.out.println("waiting for response");
			Response response = future.get();
			System.out.println(response.getStatusCode());
			System.out.println(response.getResponseBody());
		}

	}

	public void getData() throws InterruptedException, ExecutionException, IOException {
		AsyncHttpClientConfig config = config()
				.setConnectTimeout(100)
				.setMaxConnections(50)
				.setRequestTimeout(5 * 60 * 1000) 
				.build();

		try (AsyncHttpClient asyncHttpClient = asyncHttpClient(config)) {
			asyncHttpClient.prepareGet("http://localhost:8080/topic/abc/part0")
			               .execute()
			               .toCompletableFuture()
					       .thenApply(Response::getResponseBody)
					       .thenAccept(System.out::println)
					       .join();
		}

	}

}
