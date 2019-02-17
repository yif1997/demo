package com.bawei.zhou1.utils;


import android.text.TextUtils;

/**
 * @author 王艺霏
 * @fileName Utils
 * @package com.bawei.zhou1.utils
 **/
public class Utils {
    public static boolean isMobileNO(String mobileNum){
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        if (TextUtils.isEmpty(mobileNum))
            return false;
        else
            return mobileNum.matches(telRegex);
    }
}
