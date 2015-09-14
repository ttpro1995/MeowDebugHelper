package com.hahattpro.meowdebughelper;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


/**
 * Create new file.
 * to get that file, use getter
 * contructor take FILE_NAME, what in that file, context of activity
 *
 */
public class CreateTempFile {
    private File file;//contain file
    private String FILE_NAME;//name of file
    private Context context;// context from activity
    public CreateTempFile(String NAME, String file_body, Context appContext) {
        FILE_NAME =NAME;
    context = appContext;

        try{
            file = new File(context.getExternalCacheDir(),FILE_NAME);
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
    }

    public CreateTempFile(String NAME, InputStream file_body, Context appContext) {
        FILE_NAME =NAME;
        context = appContext;

        try{
            file = new File(context.getExternalCacheDir(),FILE_NAME);
            copy(file_body, file);
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){
            //do nothing
        }
    }

    private static File create(String NAME, InputStream file_body, Context appContext){
       File file= null;

        try{
            file = new File(appContext.getExternalCacheDir(),NAME);
            copy(file_body, file);
        }
        catch (FileNotFoundException e){

        }
        catch (IOException e){
            //do nothing
        }
        return file;
    }

    public static File create(String NAME, String file_body, Context appContext) {
        String FILE_NAME =NAME;
        Context context = appContext;
        File file = null;
        try{
            file = new File(context.getExternalCacheDir(),FILE_NAME);
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
        return file;
    }


    public File getFile()
    {//getter
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

    void CleanUp()
    {//delete file on phone so no memory leak
        context.deleteFile(FILE_NAME);
    }

}

//TODO: create List File and readfile
