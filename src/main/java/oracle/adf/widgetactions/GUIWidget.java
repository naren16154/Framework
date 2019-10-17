package oracle.adf.widgetactions;

import oracle.adf.core.ADFManager;
import oracle.adf.widgets.WidgetInfo;

import org.openqa.selenium.WebElement;

public class GUIWidget implements IGUIWidget{
	
	public static WidgetInfo widgetInfo = null;
	public static ADFManager adfManager = ADFManager.getInstance();
	
	public GUIWidget(WidgetInfo widgetInfo)
	{
		GUIWidget.widgetInfo = widgetInfo;
	}
	
	public void setDisplayValue(String value)
	{
		WebElement element = managerHelper.getWebElement(widgetInfo);
		element.click();
	}
	
	public String getDisplayValue()
	{
		WebElement element = managerHelper.getWebElement(widgetInfo);
		return element.getText().trim();
	}
	
	public void click()
	{
		WebElement element = managerHelper.getWebElement(widgetInfo);
		element.click();
		logger.widgetAction("Widget : "+widgetInfo.getName()+" is clicked");
	}
	
	public void jsClick()
	{
		WebElement element = managerHelper.getWebElement(widgetInfo);
		adfManager.getJSExecutor().executeScript("arguments[0].click();", element);
		logger.widgetAction("Widget : "+widgetInfo.getName()+" is clicked using javaScript");
	}
	
	public void clickJS(WebElement element)
	{
		adfManager.getJSExecutor().executeScript("arguments[0].click();", element);
		logger.widgetAction("Widget : "+widgetInfo.getName()+" is clicked using javaScript");
	}
	
	public void click(WebElement element)
	{
		element.click();
		logger.widgetAction("Widget : "+widgetInfo.getName()+" is clicked");
	}
	
	public String getText()
	{
		WebElement element = managerHelper.getWebElement(widgetInfo);
		return element.getText();
	}
	public String getAttribute(String attributeName)
	{
		WebElement element = managerHelper.getWebElement(widgetInfo);
		return element.getAttribute(attributeName);
	}
}
