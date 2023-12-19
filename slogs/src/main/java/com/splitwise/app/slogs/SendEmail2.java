package com.splitwise.app.slogs;

import com.mailjet.client.errors.MailjetException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Email;

public class SendEmail2 {
	/**
	 * This call sends an email to one recipient.
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws MailjetException {
		MailjetClient client;
		MailjetRequest request;
		MailjetResponse response;
		client = new MailjetClient("318c84dd71aef348d900be5cacf99be9", "2d06252cc41a476fe0c7a0bbc6ca601d");
		request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, "shop@ranchinovelstore.com")
				.property(Email.FROMNAME, "Mailjet Pilot").property(Email.SUBJECT, "Your email flight plan!")
				.property(Email.TEXTPART, "Dear passenger, welcome to Mailjet! May the delivery force be with you!")
				.property(Email.HTMLPART,
						"<h3>Dear passenger, welcome to <a href=\"https://www.mailjet.com/\">Mailjet</a>!<br />May the delivery force be with you!")

				.property(Email.RECIPIENTS,
						new JSONArray().put(new JSONObject().put("Email", "junaidrana926@gmail.com")));

		response = client.post(request);
		System.out.println(response.getStatus());
		System.out.println(response.getData());
	}
}