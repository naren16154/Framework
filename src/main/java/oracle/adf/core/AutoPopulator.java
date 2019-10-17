package oracle.adf.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import oracle.adf.utilities.ADFLogger;
import oracle.adf.widgetactions.IGUIWidget;
import oracle.adf.widgets.WidgetInfo;

public class AutoPopulator {
	
	public static ADFManager manager = ADFManager.getInstance();
	public static ADFManagerHelper managerHelper = ADFManagerHelper.getInstance();
	public static ADFLogger logger = ADFLogger.getInstance();
	
	public static void populate(Object screenObject, Map<String, String> dataMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Map<String, WidgetInfo> widgetInfoMap = managerHelper.getWidgetInfos(screenObject);
		logger.info("Auto population started... in screen : "+screenObject.getClass().getSimpleName());
		Method method = null;
		for(String fieldName : dataMap.keySet())
		{
			if(widgetInfoMap.containsKey(fieldName))
			{
				WidgetInfo widgetInfo = widgetInfoMap.get(fieldName);
				populateWidget(widgetInfo, fieldName, dataMap.get(fieldName));
			}
			else if(null != (method=methodFound(screenObject, fieldName))){
				logger.method(fieldName+" -- invoked in screen : "+screenObject.getClass().getSimpleName());
				method.invoke(screenObject, dataMap.get(fieldName));
			}
			else
			{
				logger.warning("Widget: "+fieldName+" not found in screenbject "+screenObject.getClass().getSimpleName());
			}
		}
	}
	

	public static void populateWidget(WidgetInfo widgetInfo, String fieldName, String fieldValue)
	{
		IGUIWidget guiObject = null;
		try {
			logger.debug("Widget : "+fieldName);
			 guiObject = (IGUIWidget) widgetInfo.getLocatorType().getDeclaredConstructor(WidgetInfo.class).newInstance(widgetInfo);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			logger.fatal(e.getMessage());
		}
		guiObject.setDisplayValue(fieldValue);
		logger.debug("Locator : "+widgetInfo.getLocatorString());
		logger.debug("Locator Type: "+widgetInfo.getLocatorType());
		logger.debug("Content : "+fieldValue);
		logger.info("widget: "+fieldName+"  ---  "+widgetInfo.getLocatorString()+" , class = "+widgetInfo.getAutomatorName()+" , content: "+fieldValue);
		
	}
	
	public static Method methodFound(Object screenObject, String fieldName) {
		fieldName = fieldName.replaceAll("[0-9]", "");
		Method[] methods= screenObject.getClass().getMethods();
		for(Method method : methods)
		{
			if(fieldName.equals(method.getName()))
				return method;
		}
		return null;
	}

}
