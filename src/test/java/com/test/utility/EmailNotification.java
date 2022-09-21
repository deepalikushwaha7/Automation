package com.joseph.utility;

import java.io.File;
import java.util.Map;

import org.apache.commons.mail.*;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

public class EmailNotification {

	public void email(ITestContext testContext) throws EmailException {

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();

		email.addTo("joseph.j.mickole@apisero.com", "Joe Mj");
		email.addCc("deepali.p.kushwaha@apisero.com", "Deepali");
		email.setFrom("joseph.j.mickole@apisero.com", "Me");
		email.setSubject("Test Report");

		email.setMsg(String.format("Total tests : %d\nSuccessful tests : %d \nFailed tests : %d",
				testContext.getAllTestMethods().length, testContext.getPassedTests().getAllResults().size(),
				testContext.getFailedTests().getAllResults().size()));

		// Create the attachment
		String pathname = System.getProperty("user.dir") + "/test-output/SparkReport/Spark.html";
		File f = new File(pathname);
		if (f.exists()) {

			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath(pathname);
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setName("SparkReport.html");

			// add the attachment
			email.attach(attachment);
		}

		// send the email
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("joseph.j.mickole@apisero.com", "gqgfejanpozyjvio"));
		email.setSSLOnConnect(true);
		email.send();
	}

}
