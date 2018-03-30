package com.pgzxc.encryption.base64;

import android.util.Base64;
import android.util.Log;

/**
 * Base64加密工具类
 */

public class Base64Utils {
    public static String  encode(String string){
        String encodedString = Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
        Log.e("Base64", "Base64---->" + encodedString);
        return  encodedString;
    }
    public static String decode(String encodedString){
        String decodedString =new String(Base64.decode(encodedString,Base64.DEFAULT));
        Log.e("Base64", "Base64---->" + decodedString);
        return decodedString;
    }
}
