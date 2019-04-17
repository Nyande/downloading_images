package com.example.downloading_images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
Button button;

    public void download(View view){
        Image task= new Image();
        Bitmap bitmap;
        try {
            bitmap=task.execute("https://nick-intl.mtvnimages.com/uri/mgid:file:gsp:kids-assets:/nick/properties/alvin-and-the-chipmunks/characters/alvin-character-web-desktop.png?height=0&width=480&matte=true&crop=false").get();
            imageView.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        button=findViewById(R.id.button);


    }
    public class  Image extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);

                return bitmap;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
