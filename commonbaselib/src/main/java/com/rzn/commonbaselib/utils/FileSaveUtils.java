package com.rzn.commonbaselib.utils;

import android.content.Context;

import com.rzn.commonbaselib.applictaion.BaseApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * 文件保存在本地的工具类
 * Created by zz on 2016/4/26.
 */
public class FileSaveUtils {

    /**
     * 以对象的形式保存到本地
     * @param object
     * @param fileName
     */
    public static void fileSaveObject (Object object, String fileName) {
        // 保存在本地
        // 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
        FileOutputStream fos = null;
        try {
            fos = BaseApplication.getInstance().openFileOutput(fileName, Context.MODE_WORLD_READABLE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);// 写入
            fos.close(); //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从本地读取转成一个对象
     * @param fileName
     * @return
     */
    public static Object readObject(String fileName) {
        Object object = null;
        FileInputStream fis = null; // 获得输入流
        try {
            fis = BaseApplication.getInstance().openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }



    /**
     * 以对象的形式保存到本地
     * @param string
     * @param fileName
     */
    public static void fileSaveString (String string, String fileName){
        // 保存在本地
        // 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
        FileOutputStream fos = null;
        BufferedWriter bufferedWriter = null;
        try {
            fos = BaseApplication.getInstance().openFileOutput(fileName, Context.MODE_WORLD_READABLE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
            bufferedWriter.write(string);
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        System.out.println("Done");
    }

    /**
     * 从本地读取转成一个对象
     * @param fileName
     * @return
     */
    public static String readString(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = BaseApplication.getInstance().openFileInput(fileName); // 获得输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
