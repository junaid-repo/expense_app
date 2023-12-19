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
		client = new MailjetClient("a2ee6a9262b90456d0d1bca5f6a8e936", "b98854ed107194bc54eff09404454727");
		for (int i = 0; i < 5; i++) {
			request = new MailjetRequest(Email.resource).property(Email.FROMEMAIL, "admin"+String.valueOf(i)+"@friendsmobile.store")
					.property(Email.FROMNAME, "Friends Mobile").property(Email.SUBJECT, "We provide a great value!")
					.property(Email.TEXTPART,
							"Dear passenger, welcome to Friends Mobile! May the delivery force be with you!")
					.property(Email.HTMLPART,
							"<h3>Dear passenger, welcome to <a href=\"friendsmobile.store/\">Friends Mobile</a>!<br />May the delivery force be with you!")

					.property(Email.RECIPIENTS,
							new JSONArray().put(new JSONObject().put("Email", "junaidrana926@gmail.com")));

			response = client.post(request);

			System.out.println(response.getStatus());
			System.out.println(response.getData());
		}
	}
}