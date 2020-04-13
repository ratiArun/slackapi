package com.slackApi.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.slack.api.util.SlackApiMethods;
import com.slackApi.common.BaseTest;
import com.slackApi.common.Utility;
import com.slackApi.pojo.SlackApiTestsPojo;

/**
 * @author arunkumar
 *
 */
public class ChannelsTests extends BaseTest {
	// Declaring the variables
	public static String channelName ;
	public static String renameChannelName ;
	public static String channelId;

	@BeforeClass
	public void setup() {
		channelName = Utility.getRandom9DigitNumber();
		renameChannelName = "6" + Utility.getRandom9DigitNumber();
	}
	// This method will give the input to the test method
	@DataProvider(name = "SlackApiTestsDataSuppliers")
	public static Object[] SlackApiTestsDataSuppliers() throws Exception {
		Object[] data = new Object[] { new SlackApiTestsPojo(channelName, true, 200) };
		return data;
	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 0)
	public void createChannelTests(SlackApiTestsPojo requestData) {

		// creating a channel
		Response createChannelResponse = SlackApiMethods.createChannel(requestData.getChannelName(),
				requestData.isValidate(), BaseTest.token);
		Reporter.log("createChannelResponse :: " + createChannelResponse.asString());
		// getting the channelId and channel name
		 channelId = createChannelResponse.jsonPath().getString("channel.id");
		String channel_Name = createChannelResponse.jsonPath().getString("channel.name");
		Reporter.log("channelId :: " + channelId);

		Assert.assertEquals(createChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(channel_Name, requestData.getChannelName(), "Channel name is mismatch");
	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 1)
	public void joinChannelTests(SlackApiTestsPojo requestData) {

		// joining the channel
		Response joinChannelResponse = SlackApiMethods.joinChannel(requestData.getChannelName(),
				requestData.isValidate(), BaseTest.token);
		Reporter.log("joinChannelResponse :: " + joinChannelResponse.asString());
		Assert.assertEquals(joinChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(joinChannelResponse.jsonPath().getString("channel.name"), requestData.getChannelName(),
				"Channel name is mismatch");
	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 2)
	public void renameChannelTests(SlackApiTestsPojo requestData) {

		// renaming the channel name
		Response renameChannelResponse = SlackApiMethods.renameChannel(channelId, renameChannelName, BaseTest.token);
		String renamedChannelName = renameChannelResponse.jsonPath().getString("channel.name");
		Reporter.log("renamedChannelName :: " + renamedChannelName);
		Assert.assertEquals(renameChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(renamedChannelName, renameChannelName, "Channel name is mismatch");

	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 3)
	public void validateRenameChannelTests(SlackApiTestsPojo requestData) {

		// fetching all the channels
		Response getAllChannelsResponse = SlackApiMethods.getChannels(BaseTest.token, requestData.getStatusCode());
		Reporter.log("getAllChannelsResponse :: " + getAllChannelsResponse.asString());
		Assert.assertEquals(getAllChannelsResponse.getStatusCode(), requestData.getStatusCode());
		JSONArray channels = new JSONObject(getAllChannelsResponse.asString()).getJSONArray("channels");
		for (int channel = 0; channel < channels.length(); channel++) {
			String renamedChannelIdActual = channels.getJSONObject(channel).getString("id");
			if (renamedChannelIdActual.equalsIgnoreCase(channelId)) {
				Assert.assertEquals(channels.getJSONObject(channel).getString("name"), renameChannelName,
						"Channel name is mismatch");
			}
		}
	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 4)
	public void archiveChannelTests(SlackApiTestsPojo requestData) {

		// archiving the channel
		Response archiveChannelResponse = SlackApiMethods.archiveChannel(channelId, BaseTest.token);
		Reporter.log("archiveChannelResponse :: " + archiveChannelResponse.asString());
		Assert.assertEquals(archiveChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(archiveChannelResponse.jsonPath().getBoolean("ok"), true);
	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 5)
	public void validateArchiveChannelTests(SlackApiTestsPojo requestData) {

		// validating the channel is archived
		Response getSpecificChannelResponse = SlackApiMethods.getSpecificChannel(BaseTest.token, channelId,
				requestData.getStatusCode());
		Reporter.log("getSpecificChannelResponse :: " + getSpecificChannelResponse.asString());
		Assert.assertEquals(getSpecificChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(getSpecificChannelResponse.jsonPath().getBoolean("channel.is_archived"), true,
				"Channel is not archived");
	}

	@Test(dataProvider = "SlackApiTestsDataSuppliers", dataProviderClass = ChannelsTests.class, priority = 6)
	public void SlackApiEndToEndTests(SlackApiTestsPojo requestData) {
		
		//Creating new channel name
		channelName = Utility.getRandom9DigitNumber();
		renameChannelName = "6" + Utility.getRandom9DigitNumber();
		// creating a channel
		Response createChannelResponse = SlackApiMethods.createChannel(requestData.getChannelName(),
				requestData.isValidate(), BaseTest.token);
		Reporter.log("createChannelResponse :: " + createChannelResponse.asString());
		// getting the channelId and channel name
		String channelId = createChannelResponse.jsonPath().getString("channel.id");
		String channelName = createChannelResponse.jsonPath().getString("channel.name");
		Reporter.log("channelId :: " + channelId);

		Assert.assertEquals(createChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(channelName, requestData.getChannelName(), "Channel name is mismatch");

		// joining the channel
		Response joinChannelResponse = SlackApiMethods.joinChannel(requestData.getChannelName(),
				requestData.isValidate(), BaseTest.token);
		Reporter.log("joinChannelResponse :: " + joinChannelResponse.asString());
		Assert.assertEquals(joinChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(joinChannelResponse.jsonPath().getString("channel.name"), requestData.getChannelName(),
				"Channel name is mismatch");

		// renaming the channel name
		Response renameChannelResponse = SlackApiMethods.renameChannel(channelId, renameChannelName, BaseTest.token);
		String renamedChannelName = renameChannelResponse.jsonPath().getString("channel.name");
		Reporter.log("renamedChannelName :: " + renamedChannelName);
		Assert.assertEquals(joinChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(renamedChannelName, renameChannelName, "Channel name is mismatch");

		// fetching all the channels
		Response getAllChannelsResponse = SlackApiMethods.getChannels(BaseTest.token, requestData.getStatusCode());
		Reporter.log("getAllChannelsResponse :: " + getAllChannelsResponse.asString());
		Assert.assertEquals(joinChannelResponse.getStatusCode(), requestData.getStatusCode());
		JSONArray channels = new JSONObject(getAllChannelsResponse.asString()).getJSONArray("channels");
		for (int channel = 0; channel < channels.length(); channel++) {
			String renamedChannelIdActual = channels.getJSONObject(channel).getString("id");
			if (renamedChannelIdActual.equalsIgnoreCase(channelId)) {
				Assert.assertEquals(channels.getJSONObject(channel).getString("name"), renameChannelName,
						"Channel name is mismatch");
			}
		}

		// archiving the channel
		Response archiveChannelResponse = SlackApiMethods.archiveChannel(channelId, BaseTest.token);
		Reporter.log("archiveChannelResponse :: " + archiveChannelResponse.asString());
		Assert.assertEquals(joinChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(joinChannelResponse.jsonPath().getBoolean("ok"), true);

		// validating the channel is archived
		Response getSpecificChannelResponse = SlackApiMethods.getSpecificChannel(BaseTest.token, channelId,
				requestData.getStatusCode());
		Reporter.log("getSpecificChannelResponse :: " + getSpecificChannelResponse.asString());
		Assert.assertEquals(getSpecificChannelResponse.getStatusCode(), requestData.getStatusCode());
		Assert.assertEquals(getSpecificChannelResponse.jsonPath().getBoolean("channel.is_archived"), true,
				"Channel is not archived");
	}
}
