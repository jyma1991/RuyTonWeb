package com.ryt.web.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
    public static String getUserAgent() {
        String javaVersion = "Java/" + System.getProperty("java.version");
        String os = System.getProperty("os.name") + " " 
                + System.getProperty("os.arch") + " " + System.getProperty("os.version");
        String sdk = Config.USER_AGENT + Config.SDK_VERSION;
        return sdk + " (" + os + ") " + javaVersion;
    }

    /*
     * check the arg.
     * 1. arg == null, return false, treat as empty situation
     * 2. arg == "", return false, treat as empty situation
     * 3. arg == " " or arg == "   ", return false, treat as empty situation
     * 4. return true, only if the arg is a illegal string
     *
     * */
    public static boolean isArgNotEmpty(String arg) {
        return arg != null && !arg.trim().isEmpty();
    }

    public static String getPath(String streamId) {
        String[] res = streamId.split("\\.");
        // res[1] -> hub, res[2] -> title
        return String.format("/%s/%s", res[1], res[2]);
    }
    /**************************************************************************
     * 日期工具
     * 
     **************************************************************************/
    /**************************日期工具start**************************************/
    /**
     * 距离周一的天数
     * @return
     */
    private static int getMondayPlus() {  
        Calendar cd = Calendar.getInstance();  
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......  
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-1;         //因为按中国礼拜一作为第一天所以这里减1  
        if (dayOfWeek == 1) {  
            return 0;  
        } else {  
            return 1 - dayOfWeek;  
        }  
    } 
    /**
     * 获得本周一的日期   
     * @return
     */
    public static Date getMondayOFWeek(){  
         int mondayPlus = getMondayPlus();  
         GregorianCalendar currentDate = new GregorianCalendar();  
         currentDate.add(GregorianCalendar.DATE, mondayPlus-1);  
         Date monday = currentDate.getTime();  
         return monday; 
    } 
    
    /**
     *  获得本周星期日的日期    
     * @return
     */
    public static Date getCurrentWeekday() {    
        int mondayPlus = getMondayPlus();  
        GregorianCalendar currentDate = new GregorianCalendar();  
        currentDate.add(GregorianCalendar.DATE, mondayPlus+6);  
        Date monday = currentDate.getTime();  
         return monday;
    }
    
    /**
     * 获取本月第一天
     * @return
     */
    public static Date getFirstDayOfMonth(){          
        Calendar lastDate = Calendar.getInstance();  
        lastDate.set(Calendar.DATE,1);//设为当前月的1号  
        return lastDate.getTime(); 
     } 
    
    /**
     * 获取上月最后一天 
     * @return
     */
    public static Date getPreviousMonthEnd(Integer month){     
      Calendar lastDate = Calendar.getInstance();  
      lastDate.set(Calendar.MONTH,month-1);//减一个月  
      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天   
      lastDate.roll(Calendar.DATE, -1);//日期回滚一天，也就是上月最后一天   
      return lastDate.getTime();  
    } 
    
    //获得下个月第一天的日期  
    public static Date getNextMonthFirst(Integer month){    
      Calendar lastDate = Calendar.getInstance(); 
      lastDate.set(Calendar.MONTH, month+1); 
      lastDate.set(Calendar.DATE, 1);//把日期设置为当月第一天   
      return lastDate.getTime();     
    }  

    public static Date getFirstDayOfMonth(Integer year,Integer month){          
        Calendar lastDate = Calendar.getInstance();  
        lastDate.set(year, month, 1, 0,0 ,0);
        lastDate.roll(Calendar.MILLISECOND, 1);//设为当前月的1号  
        return lastDate.getTime(); 
     } 
    
    //获得下个月第一天的日期  
    public static Date getNextMonthFirst(Integer year,Integer month){    
    	Calendar lastDate = Calendar.getInstance();  
        lastDate.set(year, month+1, 1, 0,0 ,0);
        return lastDate.getTime(); 
    }  
    
    //获取某天的第一秒日期
    public static Date getFirstSecondOfDay(Date day){
    	day.setHours(0);
    	day.setMinutes(0);
    	day.setSeconds(0);
    	return day;
    }
    
    //获取明天第一秒
    public static Date getFirstSecondOfNextDay(Date day) {
    	Calendar lastDate = Calendar.getInstance();  
    	getFirstSecondOfDay(day);
        lastDate.setTime(day);
        lastDate.add(Calendar.DAY_OF_MONTH, 1);
        return lastDate.getTime(); 
	}
    
    public static Map<String, Object>  excuteByFunctionName(Object object ,String strings  ){
		 Class clazz =object.getClass();
	     Method[] methods = clazz.getMethods();
	     String [] full = strings.split(",");
	     List l = Arrays.asList(full);
	     Map<String, Object> returnvalue = new HashMap<String, Object>();
		for (int i = 0; i < methods.length; i++) {
			try 
			{
				if(l.contains(methods[i].getName()))
				{
					returnvalue.put(methods[i].getName(),methods[i].invoke(object));
				}
				
			}catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
			
		 return returnvalue;
		 
	}
	
	public static String fixFeildNamesTofunctionNames(String strings){
		String[] stringArr = strings.split(",");
		String returnvalue = "";
		for (int i = 0; i < stringArr.length; i++) {
			if(i==stringArr.length-1)
			{
				returnvalue += "get"+stringArr[i].substring(0,1).toUpperCase() +  stringArr[i].substring(1)+"";
			}
			else 
			{
				returnvalue += "get"+stringArr[i].substring(0,1).toUpperCase() +  stringArr[i].substring(1)+",";
			}
		}
		return returnvalue;
	}
	
	static boolean debug = false;
	public static boolean canntBeNull(Object entity ,String fields){
	if(debug)
	{
		String strings = fixFeildNamesTofunctionNames(fields);
		  Map<String, Object>  value = excuteByFunctionName(entity  ,strings  );
		  Iterator iter = value.entrySet().iterator(); 
		  boolean notNull = true;
		  while (iter.hasNext()) { 
			    Map.Entry entry = (Map.Entry) iter.next(); 
			    Object key = entry.getKey(); 
			    if (value.get(key)==null) {
			    	System.out.println("field: "+key+" is null");
				}
			    notNull = false;
			} 
		  return notNull;
		}
	 return true;
	}
    
    /**************************日期工具End**************************************/
    
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
    {
            File convFile = new File( multipart.getOriginalFilename());
            multipart.transferTo(convFile);
            return convFile;
    }
    
    
}
