package com.example.carl.urlrooter.rooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.example.carl.urlrooter.BaseWebViewActivity;
import com.example.carl.urlrooter.LogUtil;
import com.example.carl.urlrooter.R;

import java.util.Map;

/**
 * Created by Carl on 2017/1/19.
 */
public class UrlRouter {
    public static Context mContext;

    public static final String Intent_Key_Is_InnerJump = "innerJump";

    public static final String ValidateScheme = "http|https";//配置可用的协议
    public static final String ValidateHost = "carl.com|m.carl.com|www.carl.com";//配置可用的host

    public static void init(Context context){
        mContext = context;
        Url.init(context, R.xml.url, R.xml.activity);
    }

    private UrlRouter(){}

    public static void toUrl(Activity activity, String _url){
        toUrl(activity, _url, null);
    }

    public static void toUrl(Activity activity, String _url, boolean isInnerJump){
        toUrl(activity, _url, null, isInnerJump);
    }

    public static void toUrl(Activity activity, String _url, Bundle bundle){
        toUrl(activity, _url, bundle, true);
    }

    public static void toUrl(Activity activity, String _url, Bundle bundle, boolean isInnerJump){
        Intent intent = getIntentByUrl(_url);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        intent.putExtra(Intent_Key_Is_InnerJump, isInnerJump);
        activity.startActivity(intent);
    }

    public static void toUrlForResult(Activity activity, String _url, Bundle bundle, int requestCode){
        Intent intent = getIntentByUrl(_url);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static void toUrlForResult(Activity activity, String _url, int requestCode){
        toUrlForResult(activity, _url, null, requestCode);
    }

    public static void toUrlForResultFromFragment(Fragment fragment, String _url, int requestCode){
        toUrlForResultFromFragment(fragment, _url, null, requestCode);
    }

    public static void toUrlForResultFromFragment(Fragment fragment, String _url, Bundle bundle, int requestCode){
        Intent intent = getIntentByUrl(_url);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    public static Intent getIntentByUrl(String _url, String defaultTitle) {
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(_url)) {
            Uri uri = Uri.parse(_url);
            String scheme = TextUtils.isEmpty(uri.getScheme())?"":uri.getScheme();
            String host = TextUtils.isEmpty(uri.getHost())?"":uri.getHost();
            String path = TextUtils.isEmpty(uri.getPath())?"":uri.getPath();
            String query = TextUtils.isEmpty(uri.getQuery())?"":uri.getQuery();
            if (isValidateScheme(scheme) && isCMApps(host) && !TextUtils.isEmpty(path)) {
                LogUtil.d("path+query:"+(path+query));
                for (Map.Entry<String, String> entry : Url.getActivityMap().entrySet()) {
                    LogUtil.d("value:"+entry.getValue());
                    if((path+query).matches(entry.getValue())){
                        intent.setData(uri);
                        intent.setClassName(mContext, entry.getKey());
                        return intent;
                    }
                }
            }
        }
        return backupIntent(_url, defaultTitle);
    }

    public static Intent getIntentByUrl(String _url) {
        return getIntentByUrl(_url, "默认title");
    }

    public static boolean resolveUrl(String _url){
        if (!TextUtils.isEmpty(_url)) {
            Uri uri = Uri.parse(_url);
            String scheme = TextUtils.isEmpty(uri.getScheme())?"":uri.getScheme();
            String host = TextUtils.isEmpty(uri.getHost())?"":uri.getHost();
            String path = TextUtils.isEmpty(uri.getPath())?"":uri.getPath();
            String query = TextUtils.isEmpty(uri.getQuery())?"":uri.getQuery();
            if (isValidateScheme(scheme) && isCMApps(host) && !TextUtils.isEmpty(path)) {
                LogUtil.d("path+query:"+(path+query));
                for (Map.Entry<String, String> entry : Url.getActivityMap().entrySet()) {
                    LogUtil.d("value:"+entry.getValue());
                    if((path+query).matches(entry.getValue())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isValidateScheme(String scheme){
        return scheme.matches(ValidateScheme);
    }

    private static boolean isCMApps(String host){
        return host.matches(ValidateHost);
    }

    private static Intent backupIntent(String url, String title) {
        Intent intent = new Intent(mContext, BaseWebViewActivity.class);
        intent.putExtra(BaseWebViewActivity.WEBVIEW_URL, url);
        intent.putExtra(BaseWebViewActivity.WEBVIEW_TITLE, title);
        return intent;
    }
}
