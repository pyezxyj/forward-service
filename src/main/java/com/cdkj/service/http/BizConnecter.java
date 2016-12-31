/**
 * @Title BizConnecter.java 
 * @Package com.ibis.pz.http 
 * @Description 
 * @author miyb  
 * @date 2015-5-12 下午9:44:59 
 * @version V1.0   
 */
package com.cdkj.service.http;

import java.util.Properties;

import com.cdkj.service.exception.BizException;
import com.cdkj.service.util.PropertiesUtil;
import com.cdkj.service.util.RegexUtils;

/** 
 * @author: miyb 
 * @since: 2015-5-12 下午9:44:59 
 * @history:
 */
public class BizConnecter {
    public static final String YES = "0";

    public static final String USER_URL = PropertiesUtil.Config.USER_URL;

    public static final String ACCOUNT_URL = PropertiesUtil.Config.ACCOUNT_URL;

    public static final String SMS_URL = PropertiesUtil.Config.SMS_URL;

    public static final String MALL_URL = PropertiesUtil.Config.MALL_URL;

    public static final String RIDE_URL = PropertiesUtil.Config.RIDE_URL;

    public static final String LOAN_URL = PropertiesUtil.Config.LOAN_URL;

    public static String getBizData(String code, String json) {
        String data = null;
        String resJson = null;
        try {
            Properties formProperties = new Properties();
            formProperties.put("code", code);
            formProperties.put("json", json);
            resJson = PostSimulater.requestPostForm(getPostUrl(code),
                formProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 开始解析响应json
        String errorCode = RegexUtils.find(resJson, "errorCode\":\"(.+?)\"", 1);
        String errorInfo = RegexUtils.find(resJson, "errorInfo\":\"(.+?)\"", 1);
        System.out.println("request:" + code + " with parameters " + json
                + "\nresponse:" + errorCode + "<" + errorInfo + ">.");
        if (YES.equalsIgnoreCase(errorCode)) {
            data = RegexUtils.find(resJson, "data\":(.*)\\}", 1);
        } else {
            throw new BizException("Biz000", errorInfo);
        }
        return data;
    }

    public static String getPostUrl(String code) {
        String postUrl = null;
        if (code.startsWith("805") || code.startsWith("806")
                || code.startsWith("807")) {
            postUrl = USER_URL;
        } else if (code.startsWith("802")) {
            postUrl = ACCOUNT_URL;
        } else if (code.startsWith("804")) {
            postUrl = SMS_URL;
        } else if (code.startsWith("808")) {
            postUrl = MALL_URL;
        } else if (code.startsWith("616")) {
            postUrl = RIDE_URL;
        } else if (code.startsWith("617")) {
            postUrl = LOAN_URL;
        }
        return postUrl;
    }
}