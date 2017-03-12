package my.com.jsouptest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "";
    TextView tv;
    private Document doc, doc_del;
    private Elements els, els_del;
    int i = 0;
    private Element el, el_del;
    private String str;
    private Document d;
    private String url;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        Asyc asyc = new Asyc();
        asyc.execute("http://daily.zhihu.com/");
    }

    class Asyc extends AsyncTask<String, Integer, Bitmap> {
        Bitmap bitmap;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                doc = Jsoup.connect(params[0]).get();
                els = doc.getElementsByClass("row").get(0).getElementsByClass("col-lg-4").get(0).getElementsByClass("wrap");
                el = els.get(0);
                el_del = el.getElementsByClass("box").get(0);
                str = el_del.getElementsByClass("link-button").attr("href");
                url = el_del.getElementsByClass("link-button").get(0).getElementsByClass("preview-image").attr("src");
                URL u = new URL(url);
                InputStream in = u.openStream();
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            tv.setText(el_del.text() + str + "url" + url);
            iv.setImageBitmap(bitmap);
        }
    }
}
