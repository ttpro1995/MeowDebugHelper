package com.hahattpro.meowdebughelperdemo;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hahattpro.meowdebughelper.CreateTempFile;
import com.hahattpro.meowdebughelper.Mailer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    InputStream is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailFromAssets();
    }

    private void mailFromAssets(){
        AssetManager assetManager = getAssets();
        try {
            is = assetManager.open("winter_is_coming.jpg");
            CreateTempFile tempFile = new CreateTempFile("winter_is_coming.jpg",is,MainActivity.this);
            File file = tempFile.getFile();
            Mailer mailer = new Mailer(this);
            mailer.sendMail("testing.ttpro1995@yahoo.com.vn","winter_is_coming","meow meow",file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
