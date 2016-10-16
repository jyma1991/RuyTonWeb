package com.ryt.app.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class PictureUtil {
	/**
	 * 把base64图片数据转为本地图片
	 * @param base64ImgData
	 * @param filePath
	 * @throws IOException
	 */
	public static void convertBase64DataToImage(String base64ImgData,String filePath) throws IOException {
		byte[] bs =  Base64.decodeBase64(base64ImgData);
		FileOutputStream os = new FileOutputStream(filePath);
		os.write(bs);
		os.close();
	}
}
