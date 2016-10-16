package com.ryt.app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class OpenfireUtil {
	public static short IsUserOnLine(String strUrl) {
		//返回值 : 0 - 用户不存在; 1 - 用户在线; 2 - 用户离线
		short shOnLineState = 0; // -不存在-

		try {
			URL oUrl = new URL(strUrl);
			URLConnection oConn = oUrl.openConnection();
			if (oConn != null) {
				BufferedReader oIn = new BufferedReader(new InputStreamReader(oConn.getInputStream()));
				if (null != oIn) {
					String strFlag = oIn.readLine();
					//System.out.println(strFlag);
					oIn.close();

					if (strFlag.indexOf("type=\"unavailable\"") >= 0) {
						shOnLineState = 2;
					}
					else if (strFlag.indexOf("type=\"error\"") >= 0) {
						shOnLineState = 0;
					}
					//else if (strFlag.indexOf("priority") >= 0 || strFlag.indexOf("id=\"") >= 0 || strFlag.indexOf("")) {
//						shOnLineState = 1;
//					}
					else{
						shOnLineState = 1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return shOnLineState;
	}
}
