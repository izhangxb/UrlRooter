package com.example.carl.urlrooter.rooter;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.example.carl.urlrooter.LogUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * Created by Carl on 2017/1/19.
 */
public class Url {

    private Url(){}

    private static HashMap<String, String> urlMap;

    private static LinkedHashMap<String, String> activityMap;

    public static void init(Context context, int urlXmlId, int activityXmlId){
        urlMap = new HashMap<>();
        XmlResourceParser xrp = context.getResources().getXml(urlXmlId);// 产生第一个事件
        try {
            int eventType = xrp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        // 不做任何操作或初开始化数据
                        LogUtil.d("START_DOCUMENT:"+xrp.getName());
                        break;
                    case XmlPullParser.START_TAG:
                        // 解析XML节点数据
                        // 获取当前标签名字
                        if(1 != xrp.getDepth()){//过滤掉root，eg: url
                            urlMap.put(xrp.getName(), xrp.nextText());
                            LogUtil.d("START_TAG:"+xrp.getName()+","+urlMap.get(xrp.getName()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        // 单节点完成，可往集合里边添加新的数据
                        LogUtil.d("END_TAG:"+xrp.getName());
                        break;
                }
                eventType = xrp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        activityMap = new LinkedHashMap<>();
        XmlResourceParser xrp2 = context.getResources().getXml(activityXmlId);// 产生第一个事件
        try {
            int eventType = xrp2.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        // 不做任何操作或初开始化数据
                        LogUtil.d("START_DOCUMENT:"+xrp2.getName());
                        break;
                    case XmlPullParser.START_TAG:
                        // 解析XML节点数据
                        // 获取当前标签名字
                        if(1 != xrp2.getDepth()){//过滤掉root，eg: url
                            activityMap.put(xrp2.getName(), xrp2.nextText());
                            LogUtil.d("START_TAG:"+xrp2.getName()+",hashcode:"+xrp2.getName().hashCode()+","+activityMap.get(xrp2.getName()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        // 单节点完成，可往集合里边添加新的数据
                        LogUtil.d("END_TAG:"+xrp2.getName());
                        break;
                }
                eventType = xrp2.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl(String key) {
        return urlMap.get(key);
    }

    public static String getUrl(String key, String value) {
        return String.format(Locale.getDefault(), getUrl(key), value);
    }

    public static HashMap<String, String> getUrlMap() {
        return urlMap;
    }

    public static LinkedHashMap<String, String> getActivityMap() {
        return activityMap;
    }

}
