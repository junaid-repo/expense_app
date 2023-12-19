package com.splitwise.app.suser.dto;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Email;

public class SendEmail2 {
	/**
	 * This call sends an email to one recipient.
	 */
	public static void main(String[] args) throws MailjetException {
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		client = new MailjetClient(System.getenv("MJ_APIKEY_PUBLIC"), System.getenv("MJ_APIKEY_PRIVATE"));
		request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, "pilot@mailjet.com")
				.property(Email.FROMNAME, "Mailjet Pilot").property(Email.SUBJECT, "Your email flight plan!")
				.property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
				.property(Email.HTMLPART,
						"<h3>Dear passenger, welcome to <a href=\"https://www.mailjet.com/\">Mailjet</a>!<br />May the delivery force be with you!");
		/*
		 * .property(Email.RECIPIENTS, new JSONArray().put(new JSONObject().put("Email",
		 * "passenger@mailjet.com")));
		 */
		response = client.post(request);
		System.out.println(response.getStatus());
		System.out.println(response.getData());
	}


}
