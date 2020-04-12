package com.slackApi.common;

/**
 * @author arunkumar
 *
 */
public class Constants extends BaseTest {
	// Slack api's endpoints
	public static final String CREATECHANNEL = BaseTest.BASE_URL + "/api/channels.create";
	public static final String RENAMECHANNEL = BaseTest.BASE_URL + "/api/channels.rename";
	public static final String GETCHANNELINFO = BaseTest.BASE_URL + "/api/channels.info";
	public static final String ARCHIVECHANNEL = BaseTest.BASE_URL + "/api/channels.archive";
	public static final String JOINCHANNEL = BaseTest.BASE_URL +"/api/channels.join";
	public static final String GETCHANNELS = BaseTest.BASE_URL + "/api/channels.list";
}
