package com.example.cz_jjq.baselibrary.util;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.Objects;

/**
 * Created by ztxs on 15-11-24.
 */
public class FileUtil {

    /**
     * write object to file ,through Serialize interface
     * @param context
     * @param obj
     * @param filename
     * @return
     */
    public static boolean SaveObjToFile(Context context,Object obj,String filename){
        boolean ret=false;
        FileOutputStream out=null;
        ObjectOutputStream stream=null;
        try{
            //stream=new FileOutputStream(filename);
            out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            stream = new ObjectOutputStream(out);
            stream.writeObject(obj);
            stream.flush();
            //out=new ObjectOutputStream(stream);
            ret=true;
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(stream!=null){
                try{
                    stream.close();
                }catch(IOException e) {

                }
            }
            if(out!=null){
                try{
                    out.close();
                }catch(IOException e) {

                }
            }

        }
        return ret;
    }

    /**
     * read object from file,via Serialize interface
     * @param context
     * @param filename
     * @return
     */
    public static <T>T loadObjFromFile(Context context,String filename,Class<T> cls){
        T ret=null;
        FileInputStream in=null;
        ObjectInputStream stream=null;
        try{
            in = context.openFileInput(filename);
            stream = new ObjectInputStream(in);
            Object obj=stream.readObject();
            if(obj!=null)
                ret=(T)obj;
        }catch(IOException|ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try{
                    in.close();
                }catch(IOException e) {

                }
            }
            if(stream!=null){
                try{
                    stream.close();
                }catch(IOException e) {

                }
            }
        }
        return ret;
    }

    /**
     * save string data to file
     * @param context
     * @param data
     * @param filename
     * @return
     */
    public static boolean saveDataToFile(Context context,String data,String filename){
        boolean ret=false;
        FileOutputStream out=null;
        BufferedWriter writer=null;

        try{
            out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
            writer.flush();
            ret=true;
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try{
                    writer.close();
                }catch(IOException e) {

                }
            }
            if(out!=null){
                try{
                    out.close();
                }catch(IOException e) {

                }
            }

        }
        return ret;
    }

    /**
     * read string data from file
     * 对返回结果可以多使用TextUtils工具，比如TextUtils.isEmpty(inputText)可以同时判断null和“”
     * @param context
     * @param filename
     * @return
     */
    public static String loadDataFromFile(Context context,String filename){
        String ret=null;
        FileInputStream in=null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            ret=content.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try{
                    in.close();
                }catch(IOException e) {

                }
            }
            if(reader!=null){
                try{
                    reader.close();
                }catch(IOException e) {

                }
            }
        }
        return ret;
    }

}
