package com.ryt.web.util;

import java.util.UUID;

public class CommonUtil {

	public String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // È¥µô"-"·ûºÅ  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    }  
}
