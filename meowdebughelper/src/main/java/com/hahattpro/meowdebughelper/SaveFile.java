package com.hahattpro.meowdebughelper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;



/**
 * Create new file.
 * to get that file, use getter
 * contructor take FILE_NAME (ex: my_note_about_cat.txt), what in that file (ex: there is a cat), context of activity (ex: MainActivity.this)
 * File will be save into Download Folder
 * Search for "SaveFile path" log tag to know where your file
 */
public class SaveFile {
    private File file;//contain file
    private String FILE_NAME;//name of file
    private Context context;// context from activity
    private String path;


    public SaveFile(String NAME, String file_body, Context appContext) {
        FILE_NAME =NAME;
    context = appContext;
    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/" +FILE_NAME;
        try{
            file = new File(path);
            FileOutputStream out = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(file_body);
            writer.close();
            out.close();
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){
            //do nothing
        }
        Log.i("SaveFile path","path : "+path);
    }

    public static File save(String NAME, String file_body) {
        String FILE_NAME =NAME;

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ "/" +FILE_NAME;
        File file = null;
        try{
            file = new File(path);
            FileOutputStream out = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(file_body);
            writer.close();
            out.close();
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){
            //do nothing
        }
        Log.i("SaveFile path","path : "+path);
        return file;
    }

    private static File save(String NAME, InputStream file_body){
        File file= null;

        try{
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),NAME);
            copy(file_body, file);
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){
            //do nothing
        }
        return file;
    }

    public static void copy(InputStream in, File dst) throws IOException {

        OutputStream out = new FileOutputStream(dst);

        // Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    public File getFile()
    {//getter
        return file;
    }

    void CleanUp()
    {//delete file on phone so no memory leak
        context.deleteFile(FILE_NAME);
    }

}

//TODO: create List File and readfile
