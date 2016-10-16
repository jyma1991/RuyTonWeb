/**
 * 
 */
package com.ryt.web.common;

/**
 * @author xiaoren
 *
 */

public class MemCachedTest {

	public static void main(String[] args) {
		// String k = "test";
		// String v = "testMemCached";
		// System.out.println("-------------------MemCached set
		// begin--------------");
		// MemcacheManager.set(k, v);
		// System.out.println("setValue:" + v);
		// System.out.println("-------------------MemCached set
		// end--------------");
		//
		// System.out.println("-------------------MemCached get
		// begin--------------");
		// String r = (String)MemcacheManager.get(k);
		// System.out.println("getValue:" + r);
		// System.out.println("-------------------MemCached get
		// end--------------");
//		MemcacheManager.flushAll();
//		String a = "test@conference.192.168.1.93/123456";
//		System.out.println(a.split("/")[1]);
		MemcacheManager.delete("WIKI_145_2");
		
		//b610e3472035df55da91633735226cb150344f7b7e1f5df9


	}
	
	
	public String getEncryPWD(String noEncryPWD) {    
        String resultPWD = null;    
        String passWordKey = null; //passwordKey,从openfire数据库中读取    
        /*  
         *  下面这段是从ofProperty表中查询得到passwordKey的值。  
        OfProperty ofProperty = ofPropertyMapper.selectByPrimaryKey("passwordKey");  
        if (ofProperty != null) {  
            passWordKey = ofProperty.getPropvalue();  
        } */ 
        
//        Blowfish blowFish = new Blowfish(passWordKey); //根据加密key初始化    
//        passWordKey = blowFish.encryptString(noEncryPWD); //加密    
        return resultPWD; //返回加密后的结果    
    } 
}
