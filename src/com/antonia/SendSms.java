package com.antonia;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

public class SendSms {

	public static final String ACCOUNT_SID = "AC8cfb5224c804eba7b9f19b446327db67";
	public static final String AUTH_TOKEN = "54ba353e0eb66cf23b73e5a7a45c40a6";
	
	public static void send(String number, String text) throws TwilioRestException{
		 TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN); 
		  
	
		 List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		 params.add(new BasicNameValuePair("To", number)); 
		 params.add(new BasicNameValuePair("From", "+18048356390")); 
		 params.add(new BasicNameValuePair("Body", text));   
	 
		 MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		 Message message = messageFactory.create(params); 
		 System.out.println(message.getSid()); 
		 Account account = client.getAccount(ACCOUNT_SID);
		 System.out.println(account.getDateCreated());	
	}
}
