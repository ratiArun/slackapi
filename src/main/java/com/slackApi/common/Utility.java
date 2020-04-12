package com.slackApi.common;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

/**
 * @author arunkumar
 *
 */
public class Utility extends BaseTest {

	/**
	 * Get method which will fetch specific channel based ion channel Id
	 * @param responseCode
	 * @param url
	 * @param token
	 * @param channellId
	 * @return getResponse
	 */
	public static Response get(int responseCode, String url, String token, String channellId) {
		Response getResponse = given().contentType(ContentType.URLENC)
				.header(new Header("Authorization", "Bearer " + token)).formParameter("channel", channellId).expect()
				.statusCode(responseCode).when().get(url);
		return getResponse;
	}

	/**
	 * @param url
	 * @param body
	 * @param token
	 * @return PostResponse
	 */
	public static Response post(String url, String body, String token) {
		Response PostResponse = given().contentType(ContentType.JSON)
				.header(new Header("Authorization", "Bearer " + token)).body(body).expect().log().ifError().when()
				.post(url);
		return PostResponse;
	}

	/**
	 * @param responseCode
	 * @param url
	 * @param token
	 * @return getResponse
	 */
	public static Response get(int responseCode, String url, String token) {
		Response getResponse = given().contentType(ContentType.JSON)
				.header(new Header("Authorization", "Bearer " + token)).expect().statusCode(responseCode).when()
				.get(url);
		return getResponse;
	}

	/**
	 * Generate random number of 9 digits
	 */
	public static String getRandom9DigitNumber() {
		long timeSeed = System.nanoTime(); 

		double randSeed = Math.random() * 1000; 

		long midSeed = (long) (timeSeed * randSeed);

		String s = midSeed + "";
		String subStr = s.substring(0, 9);

		return subStr;
	}
}
