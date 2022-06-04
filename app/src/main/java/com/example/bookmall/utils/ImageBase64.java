package com.example.bookmall.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.IOException;
import java.io.InputStream;

public class ImageBase64 {
    public static String imageToBase64(Context context, String path){
        AssetManager assetManager = context.getAssets();
        String result = null;
        try{
            InputStream is = assetManager.open(path);
            byte[] data = null;
            try{
                //创建一个字符流大小的数组。
                data = new byte[is.available()];
                //写入数组
                is.read(data);
                //用默认的编码格式进行编码
                result = Base64.encodeToString(data,Base64.DEFAULT);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(null !=is){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static Bitmap base64ToBitmap(String base64Str) {
        byte[] data = Base64.decode(base64Str,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
}
