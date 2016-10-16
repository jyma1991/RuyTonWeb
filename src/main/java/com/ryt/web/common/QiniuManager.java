package com.ryt.web.common;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class QiniuManager {
	private static final String AK = "ax8ZCnud8Sf5FSi3y3iuTNakQ1zoiT9T67RKbd-I";
	private static final String SK = "zgSpkWyFdLnfkwNgWuXbAD0Yhfa_xvvbunhagnTL";
	private static final String publicBucket = "ruytonwebdisk";
	public static final String domain = "http://source.ruiyantong.com/";

	public String getQiniuPublicToken() {
		return Auth.create(AK, SK).uploadToken(publicBucket, null, 3600, getReturnBody());
	}
	
	/**
	 * 覆盖更新图片
	 * @return
	 */
	public String getQiniuPublicUpdateToken(String key) {
		Auth auth =Auth.create(AK, SK);
		return auth.uploadToken(publicBucket, key, 3600, getReturnBody());
	}
	
	private StringMap getReturnBody(){
		return new StringMap().putNotEmpty("returnBody", "{\"key\": $(key), \"fname\": $(fname), \"fsize\": $(fsize), \"endUser\": $(endUser),\"ext\": $(ext),\"imageInfo\": $(imageInfo),\"avinfo\": $(avinfo)}");
	}
	
	/**
	 * 上传文件
	 * @param file
	 * @return
	 * @throws QiniuException
	 */
   public HashMap<String, Object> upload(File file) throws QiniuException{
	   UploadManager uploader = new UploadManager();
		
       Response res =uploader.put(file,UUID.randomUUID().toString().replaceAll("-", ""), getQiniuPublicToken());
       if(res.isOK()){
    	   return  res.jsonToObject(HashMap.class);
       }
       else{
    	   return null;
       }
   }
   
   /**
    * 根据key删除文件
    * @param key
    * @throws QiniuException
    */
   public void delete(String key) throws QiniuException{
	   BucketManager bucketManager = new BucketManager(Auth.create(AK, SK));
	   bucketManager.delete(publicBucket, key);
   }
}
