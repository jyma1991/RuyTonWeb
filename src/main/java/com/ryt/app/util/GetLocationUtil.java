package com.ryt.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ryt.app.commom.RuyTonAppContants;
import com.ryt.app.entity.Location;
import com.ryt.app.entity.Poi;

public class GetLocationUtil {
	private static final String ak = "MUU5GipE2DZYTfg48plG4yFV";
	private static final String urlApi = "http://api.map.baidu.com/geocoder/v2/";
	
	public Location GetLocationInfo(String location){
		String jsonString = this.loadJSON(location);
		JSONObject  dataJson=JSON.parseObject(jsonString);
		int status = dataJson.getIntValue("status");
		
		Location localPois = new Location();
		
		if(status ==0){		
			List<Poi> listPoi= new ArrayList<Poi>();
			JSONObject  result=dataJson.getJSONObject("result");
			
			Poi poi1 = new Poi();
			poi1.setAddr(result.getString("formatted_address"));
			
			JSONObject loca =result.getJSONObject("location");
			poi1.setX(loca.getString("lng"));
			poi1.setY(loca.getString("lat"));

	
			
			
			JSONArray jsonArray= result.getJSONArray("pois");
			System.out.println(jsonArray);
			List<Object> pois = result.getJSONArray("pois");
			for (Object object : pois) {
				JSONObject jObject= (JSONObject)object;
				
				Poi poi = new Poi();
				poi.setAddr(jObject.getString("addr"));
				poi.setName(jObject.getString("name"));
				
				JSONObject point =jObject.getJSONObject("point");
				poi.setX(point.getString("x"));
				poi.setY(point.getString("y"));
				
				listPoi.add(poi);
			}
			localPois.setPois(listPoi);
		}
		
		return localPois;
	}

	private String loadJSON(String location) {
		String requsetUrl = this.getUrl(location);
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();

		try {
			URL url = new URL(requsetUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			connection.disconnect();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getUrl(String location) {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("?ak=" + ak);
		sbBuffer.append("&location=" + location);
		sbBuffer.append("&output=json&pois=" + 2);
		sbBuffer.append("&qq-pf-to=pcqq.group");

		return urlApi + sbBuffer.toString();
	}
}
