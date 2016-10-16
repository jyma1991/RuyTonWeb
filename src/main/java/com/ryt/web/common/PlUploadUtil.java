/**
 * 
 */
package com.ryt.web.common;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import sun.misc.BASE64Decoder;

/**
 * @author chinavane
 *         <p>
 *         涓婁紶涓嬭浇宸ュ叿绫伙紝浣跨敤鍗曚緥瀹炵幇
 *         </p>
 *         <p>
 *         1.瑙ｆ瀽涓婁紶閰嶇疆鏂囦欢
 *         </p>
 */
@SuppressWarnings("restriction")
public class PlUploadUtil {

    // upload.xml鐩稿浜巜eb鏍圭洰褰曠殑璺緞
    private final static String UPLOAD_FILE_PATH = "assets/plupload/upload.xml";
    private static PlUploadUtil util;
    private Map<String, String> uploadParam;
    private String rootPath;

    private PlUploadUtil(String rootPath) throws Exception {
        this.rootPath = rootPath;
        uploadParam = getUploadParams();
    }

    /**
     * @return 閿�瀵瑰舰寮忓偍瀛樼殑閰嶇疆鏂囦欢鍙傛暟 瑙ｆ瀽upload.xml閰嶇疆鏂囦欢涓洪敭鍊煎褰㈠紡
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getUploadParams() throws Exception {
        Map<String, String> uploadParams = new HashMap<String, String>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(rootPath + "/" + UPLOAD_FILE_PATH));
            Element root = document.getRootElement();

            for (Iterator<Element> rt = root.elementIterator(); rt.hasNext();) {
                Element r_pe = (Element) rt.next();
                uploadParams.put(r_pe.getName(), r_pe.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("璇诲彇upload.xml 鏂囦欢寮傚父");
        }
        return uploadParams;
    }

    public Map<String, String> getUploadParam() {
        return uploadParam;
    }

    /**
     * @param rootPath 椤圭洰缁濆璺緞
     * @return 宸ュ叿绫诲疄渚�鍗曚緥瀹炵幇
     * @throws Exception
     */
    public synchronized static PlUploadUtil getInstance(String rootPath) throws Exception {
        if (util == null) {
            util = new PlUploadUtil(rootPath);
        }
        return util;
    }

    // 灏�s 杩涜 BASE64 缂栫爜
    public static String getBASE64(String s) {
        if (s == null) return null;
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
    }

    // 灏�BASE64 缂栫爜鐨勫瓧绗︿覆 s 杩涜瑙ｇ爜
    public static String getFromBASE64(String s) {
        if (s == null) return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

}
