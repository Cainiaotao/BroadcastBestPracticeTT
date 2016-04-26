package com.example.tantao.broadcastbestpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tantao.broadcastbestpractice.handler.MyHandler;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class XmlpullActivity extends AppCompatActivity {


    @InjectView(R.id.responseText)
    TextView responseText;

    private static final int SHOWONE = 1;
    @InjectView(R.id.getdataBtn)
    Button getdataBtn;
    @InjectView(R.id.saxdataBtn)
    Button saxdataBtn;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOWONE:
                    String response = (String) msg.obj;
                    responseText.setText(response);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlpull);
        ButterKnife.inject(this);


    }

    private void sendResponseWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://10.10.1.131:8080/getxml.xml");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOWONE;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                    preasResponsPullwithxml(response.toString());
                    preasSaxWtihxml(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null)
                        connection.disconnect();

                }
            }
        }).start();
    }

    /**
     * Pull解析XML
     * */
    private void preasResponsPullwithxml(String data) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(new StringReader(data));

            String id = "";
            String name = "";
            String version = "";

            StringBuilder response = new StringBuilder();
            int evenType = pullParser.getEventType();
            while (evenType != pullParser.END_DOCUMENT) {
                String nodename = pullParser.getName();

                switch (evenType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodename)) {
                            id = pullParser.nextText();
                        } else if ("name".equals(nodename)) {
                            name = pullParser.nextText();
                        } else if ("version".equals(nodename)) {
                            version = pullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodename)) {
                            String result = "Result: " + id + "\t" + name + "\t" + version + "\n";
                            response.append(result);
                        }
                        break;
                    default:
                        break;
                }
                evenType = pullParser.next();
            }

            Message message = new Message();
            message.what = SHOWONE;
            message.obj = response.toString();
            handler.sendMessage(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * SAX解析xml
     * */
    private void preasSaxWtihxml(String xmldata){

        try {
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            MyHandler myHandler=new MyHandler();
            xmlReader.setContentHandler(myHandler);
            //开始解析
            xmlReader.parse(new InputSource(new StringReader(xmldata)));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnClick({R.id.getdataBtn, R.id.saxdataBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getdataBtn:
                sendResponseWithHttpURLConnection();
                break;
            case R.id.saxdataBtn:
                sendResponseWithHttpURLConnection();
                break;
        }
    }


    //@OnClick(R.id.getdataBtn)
    // public void onClick() {
    //     sendResponseWithHttpURLConnection();
    // }
}
