package oracle.adf.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.adf.scripts.ADFScriptRunner;
import oracle.adf.utilities.ADFLogger;

public class Verify {

	public static ADFLogger logger = ADFLogger.getInstance();

	public static void verifyEquals(String message, String expectedValue,
			String actualValue) {
		logger.debug("Expected Value : "+expectedValue);
		logger.debug("Actual Value : "+actualValue);
		Pattern pattern = Pattern.compile(expectedValue, Pattern.LITERAL);
		Matcher matcher = pattern.matcher(actualValue);
		boolean matchFound = matcher.matches();
		logger.verificationPoint(message+" result="+matchFound+" expected="+expectedValue+" actual="+actualValue, matchFound);
		if(matchFound)
			ADFScriptRunner.success++;
		else
			ADFScriptRunner.fail++;
	}

	public static void verifyEquals(String expectedValue, String actualValue) {
		logger.debug("Expected Value : "+expectedValue);
		logger.debug("Actual Value : "+actualValue);
		Pattern pattern = Pattern.compile(expectedValue);
		Matcher matcher = pattern.matcher(actualValue);
		boolean matchFound = matcher.matches();
		logger.verificationPoint("result="+matchFound+" expected="+expectedValue+" actual="+actualValue, matchFound);
		if(matchFound)
			ADFScriptRunner.success++;
		else
			ADFScriptRunner.fail++;
	}
	
	public static void verifyEquals(String message, Boolean result) {
		logger.verificationPoint("result="+result+" "+message, result);
		if(result)
			ADFScriptRunner.success++;
		else
			ADFScriptRunner.fail++;
	}

}
