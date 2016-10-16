package com.ryt.app.util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;   


public class SendSmsUtil {
	
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	/**
	 * 发送验证到手机
	 * @param mobile 手机号码
	 * @param request
	 * @return
	 */
	public static Map<String,Object> sendSms(String mobile,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		
		int mobile_code = (int)((Math.random()*9+1)*100000);

		//System.out.println(mobile);
		
	    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_rytong"), 
			    new NameValuePair("password", "ryt1313"), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
						
			 if("2".equals(code)){
				System.out.println("短信提交成功");
				resultMap.put("success",true);
				resultMap.put("info",String.valueOf(mobile_code));
				
			}else{
				resultMap.put("success",false);
				resultMap.put("info", msg);
			}
			
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 return resultMap;
		
	}
	
	
	
	
	/**
	 * 发送邀请给家长
	 * @param initPwd 初始密码
	 * @param inviateName 邀请人姓名
	 * @param familyName 被邀请人姓名,
	 * @param childName 小孩名字
	 * @param relatvieName 关系
	 * @param mobile 手机号码
	 * @param request
	 * @return
	 */
	public static Map<String,Object> sendSmsToFamily(String initPwd,String inviateName,String familyName,String childName,String relativeName,String mobile){
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    String content = ":"+inviateName+"已将您关联为"+childName+"宝贝的"+relativeName+"，初始化密码为"+initPwd+"，登录后可以查看宝贝相关信息。"; 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_rytong"), 
			    new NameValuePair("password", "ryt1313"), //密码可以使用明文密码或使用32位MD5加密
			    new NameValuePair("mobile", mobile), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
						
			 if("2".equals(code)){
				System.out.println("短信提交成功");
				//验证码放入session,以便用户验证
				resultMap.put("success",true);
				resultMap.put("info", "短信邀请发送成功");
				
			}else{
				resultMap.put("success",false);
				resultMap.put("info", msg);
			}
			
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}	
		 return resultMap;
		
	}
	
	/**
	 * 提现
	 * @param agentName 代理商姓名
	 * @param amt 金额
	 * @param cardNo 卡号
	 * @param companyPhone 公司号码
	 * @param agentPhone   代理商号码
	 * @return
	 */
	public  Map<String,Object> sendSmsToWithdraw(String agentName,String amt,String cardNo,String companyPhone,String agentPhone){
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    String content = "提现：代理商:【"+agentName+"】，您的提现操作（【"+amt+"】人民币），已转入您的账户（卡号：【"+cardNo+"】），请及时查收。如有疑问，请致电公司电话： 电话号码：【"+companyPhone+"】"; 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_rytong"), 
			    new NameValuePair("password", "ryt1313"), //密码可以使用明文密码或使用32位MD5加密
			    new NameValuePair("mobile", agentPhone), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			 if("2".equals(code)){
				System.out.println("短信提交成功");
				resultMap.put("success",true);
				resultMap.put("info", "短信邀请发送成功");
				
			}else{
				resultMap.put("success",false);
				resultMap.put("info", msg);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}	
		 return resultMap;
	}
	
	/**
	 * 推荐代理商
	 * @param trueName 代理商姓名
	 * @param AgentId 代理商ID
	 * @param name 被推荐人姓名
	 * @param id 被推荐人ID
	 * @param agentPhone   代理商号码
	 * @return
	 */
	public  Map<String,Object> sendSmsUtilAgent(String AgentId,String trueName,String name,String id,String agentPhone){
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    //String content = "推荐人：代理商:【"+trueName+"】（ID：【"+AgentId+"】）,您推荐的【"+name+"】（ID：【"+id+"】）,已经成为公司的粉丝合伙人。  电话号码：【"+agentPhone+"】"; 
	    String content ="恭喜您【"+name+"】成为我们的合伙人。 推荐人：【"+trueName+"】--睿眼通（上海）信息科技有限公司。";
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_rytong"), 
			    new NameValuePair("password", "ryt1313"), //密码可以使用明文密码或使用32位MD5加密
			    new NameValuePair("mobile", agentPhone), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			 if("2".equals(code)){
				System.out.println("短信提交成功");
				resultMap.put("success",true);
				resultMap.put("info", "短信邀请发送成功");
				
			}else{
				resultMap.put("success",false);
				resultMap.put("info", msg);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}	
		 return resultMap;
	}
	
	/**
	 * 代理商升级
	 * @param agentName  代理商姓名
	 * @param levelId  代理商等级
	 * @param agentPhone  代理商号码
	 * @return
	 */
	public Map<String,Object> sendSmsUtilUpgrade(String agentName,String levelId,String agentPhone){
		Map<String,Object> resultMap = new HashMap<String,Object>();	
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    String content = "等级提升：恭喜您:【"+agentName+"】，已被睿眼通公司提升为【"+levelId+"】合伙人。   电话号码：【"+agentPhone+"】"; 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_rytong"), 
			    new NameValuePair("password", "ryt1313"), //密码可以使用明文密码或使用32位MD5加密
			    new NameValuePair("mobile", agentPhone), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
			 if("2".equals(code)){
				System.out.println("短信提交成功");
				resultMap.put("success",true);
				resultMap.put("info", "短信邀请发送成功");
				
			}else{
				resultMap.put("success",false);
				resultMap.put("info", msg);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}	
		 return resultMap;
	}
}