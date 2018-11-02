package com.example.zjf.mp3toaac;

import android.content.Context;
import android.os.Environment;

import java.io.File;
/**
*@description 创建目录相关
*
*@author zjf
*@date 2018/11/1 15:32
*/
public class Constants {

    /**
     * 获取工作目录，如果没有的话创建工作目录
     * @param context
     * @return
     */
    public static String getBaseFolder(Context context){
        String baseFolder = Environment.getExternalStorageDirectory() + "/CodecFile/";
        File file = new File(baseFolder);
        if (!file.exists()){
            boolean b = file.mkdirs();
            if (!b){
                baseFolder = MyApplication.getContext().getExternalFilesDir(null).getAbsolutePath() + "/";
            }
        }
        return baseFolder;
    }

    /**
     *获取媒体文件的路径,不同的音频文件放在不同的文件夹中
     * @param context
     * @param path       文件所在路径
     * @param fileName  文件名
     * @return
     */
    public static String getPath(Context context,String path,String fileName){
        String p = getBaseFolder(context) + path;//获取媒体文件的目录
        File file = new File(p);
        if (!file.exists() && !file.mkdirs()){
            return getBaseFolder(context) + fileName;
        }
        return p + fileName;
    }

    /**
     *
     * @param path
     * @return
     */
    public static String getPath(Context context,String path){
        String p = getBaseFolder(MyApplication.getContext()) + path;
        File file = new File(p);
        if (!file.exists() && !file.mkdirs()){
            return getBaseFolder(MyApplication.getContext());
        }
        return p;
    }
}
