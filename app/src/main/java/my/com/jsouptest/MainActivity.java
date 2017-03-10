package my.com.jsouptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document doc = Jsoup.connect("http://www.qiushibaike.com/8hr/page/1/").get();
                    Elements els = doc.select("a.contentHref");
                    for (int i = 0; i < els.size(); i++) {
                        Element el = els.get(i);
                        String href = el.attr("href");
                        Document doc_det = Jsoup.connect("http://www.qiushibaike.com/" + href).get();
                        Elements els_det = doc_det.select(".content");
                        String a;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
