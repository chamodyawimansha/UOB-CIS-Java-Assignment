package utils;


import com.ibm.watson.developer_cloud.service.exception.NotFoundException;
import com.ibm.watson.developer_cloud.service.exception.RequestTooLargeException;
import com.ibm.watson.developer_cloud.service.exception.ServiceResponseException;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;


/**
 * Some codes on this class I got from the Tone Analyser documentation. I did some changes to it according to my project
 * https://console.bluemix.net/apidocs/tone-analyzer?language=java
 * 
 * @author chamodya wimansha
 *
 */

public class SentimentAnalysis {
	
	private String tone = "Null";
	
	public SentimentAnalysis(String text) {
		
		try {
			
			IamOptions options = new IamOptions.Builder().apiKey("iyJGHXqpNoHfcP_k7lDcmU5Rik8adSgm8X2Lssry99Fd").build();

			ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", options);

			toneAnalyzer.setEndPoint("https://gateway.watsonplatform.net/tone-analyzer/api");

			ToneOptions toneOptions = new ToneOptions.Builder().text(text).build();

			ToneAnalysis toneAnalysis = toneAnalyzer.tone(toneOptions).execute();	
			
			/**
			 * convert returning Json to a java object and get the tone name
			 */
			tone = (new ToneConvert(toneAnalysis.getDocumentTone().getTones().get(0).toString()).getDocTone()).getTone_id();			
			
		} catch (NotFoundException e) {
		    // Handle Not Found (404) exception
			Helpers.Debug("Not Found (404)");
			
		} catch (RequestTooLargeException e) {
		    // Handle Request Too Large (413) exception
			Helpers.Debug("Too Large (413)");
			
		} catch (ServiceResponseException e) {
		    // Base class for all exceptions caused by error responses from the service
			Helpers.Debug("Service returned status code " + e.getStatusCode() + ": " + e.getMessage());
		}
		
	}
	/*
	 * return the tone
	 */
	public String getTone() {
		return tone;
	}
	
	

}
