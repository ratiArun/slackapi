package com.slack.api.util;

import org.testng.Reporter;

import com.jayway.restassured.response.Response;
import com.slackApi.common.BaseTest;
import com.slackApi.common.Constants;
import com.slackApi.common.Utility;

/**
 * @author arunkumar
 *
 */

public class SlackApiMethods extends BaseTest {

	/**
	 * This method will create a channel
	 * @param channalName
	 * @param Validate
	 * @param token
	 * @return createChannelResponse
	 */
	public static Response createChannel(String channalName, boolean Validate, String token) {
		String createChannelRequest = "{\"name\":\"" + channalName + "\",\"validate\":\"" + Validate + "\"}";
		Reporter.log("createChannelRequest :: " + createChannelRequest);
		Response createChannelResponse = Utility.post(Constants.CREATECHANNEL, createChannelRequest, token);
		
		return createChannelResponse;
	}

	/**
	 * This method will rename a created channel
	 * @param channalId
	 * @param newName
	 * @param token
	 * @return renameChannelResponse
	 */
	public static Response renameChannel(String channalId, String newName, String token) {
		String renameChannelRequest = "{\"channel\":\"" + channalId + "\",\"name\":\"" + newName + "\"}";
		Reporter.log("renameChannelRequest :: " + renameChannelRequest);
		Response renameChannelResponse = Utility.post(Constants.RENAMECHANNEL, renameChannelRequest, token);
		
		return renameChannelResponse;
	}

	/**
	 * This method will fetch a channel based on channelId
	 * @param token
	 * @param channalId
	 * @param startusCode
	 * @return getChannelResponse
	 */
	public static Response getSpecificChannel(String token,String channalId, int startusCode) {
		Response getChannelResponse = Utility.get(startusCode, Constants.GETCHANNELINFO, token,channalId);
		return getChannelResponse;
	}
	
	/**
	 * This method is used for joining a channel
	 * @param channalName
	 * @param validate
	 * @param token
	 * @return joinChannelResponse
	 */
	public static Response joinChannel(String channalName, boolean validate, String token) {
		String joinChannelRequest = "{\"name\":\"" + channalName + "\",\"validate\":\"" + validate + "\"}";
		Reporter.log("renameChannelRequest :: " + joinChannelRequest);
		Response joinChannelResponse = Utility.post(Constants.JOINCHANNEL, joinChannelRequest, token);
		
		return joinChannelResponse;
	}
	
	/**
	 * This method will archive a channel
	 * @param channelId
	 * @param token
	 * @return archiveChannelResponse
	 */
	public static Response archiveChannel(String channelId, String token) {
		String archiveChannelRequest = "{\"channel\":\"" + channelId + "\"}";
		Reporter.log("archiveChannelRequest :: " + archiveChannelRequest);
		Response archiveChannelResponse = Utility.post(Constants.ARCHIVECHANNEL, archiveChannelRequest, token);
		
		return archiveChannelResponse;
	}
	
	/**
	 * This method will fetch all channels
	 * @param token
	 * @param startusCode
	 * @return getAllChannels
	 */
	public static Response getChannels(String token, int startusCode) {
		Response getChannelResponse = Utility.get(startusCode, Constants.GETCHANNELS, token);
		return getChannelResponse;
	}
}
