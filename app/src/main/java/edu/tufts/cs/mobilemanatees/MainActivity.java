package edu.tufts.cs.mobilemanatees;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //https://stackoverflow.com/questions/4186021/how-to-start-new-activity-on-button-click
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchactivity = new  Intent(MainActivity.this, LearnMore.class);
                MainActivity.this.startActivity(launchactivity);
            }
        });

        WebView wv = (WebView)findViewById(R.id.webView1);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.loadUrl("http://calmingmanatee.com/img/xmanatee23.jpg.pagespeed.ic.xjePVG1nt6.webp");

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
        if (id == R.id.action_learn) {
            //call new activity here
            Intent launchactivity = new  Intent(MainActivity.this, LearnMore.class);
            MainActivity.this.startActivity(launchactivity);
        }

        return super.onOptionsItemSelected(item);
    }
//https://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            Log.d("**Trying to load image1", "Trying to load image 1");
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                Log.d("**Trying to load image2", "Trying to load image 2");
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void newManatee(View view) {

        //these photos are too long to fit well in the layout
        List<Integer> manateesToAvoid = Arrays.asList(17, 20, 27, 29);

        Random r = new Random();
        int random = r.nextInt(34) + 1;

        if (manateesToAvoid.contains(random)) {
            random += 1;
        }

        String url = "http://calmingmanatee.com/img/xmanatee" + Integer.toString(random) + ".jpg.pagespeed.ic.xjePVG1nt6.webp";

        WebView wv = (WebView)findViewById(R.id.webView1);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.loadUrl(url);
    }
}