package com.slackApi.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SlackApiTestsPojo {

	// Request
	String channelName;
	boolean validate;
	
	// Response
	int statusCode;

}
