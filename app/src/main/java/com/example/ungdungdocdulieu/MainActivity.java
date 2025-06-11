package com.example.ungdungdocdulieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    Button btnParse;
    ListView lvEmployees;
    List<String> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnParse = findViewById(R.id.btnParse);
        lvEmployees = findViewById(R.id.lvEmployees);

        employeeList = new ArrayList<>();

        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeList.clear(); // Clear trước khi parse mới
                parseXML();
                EmployeeAdapter adapter = new EmployeeAdapter(MainActivity.this, employeeList);
                lvEmployees.setAdapter(adapter);
            }
        });
    }

    private void parseXML() {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("employee.xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            parser.parse(new InputSource(inputStream), new DefaultHandler() {

                String id = "", title = "", name = "", phone = "";
                boolean inName = false, inPhone = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("employee")) {
                        id = attributes.getValue("id");
                        title = attributes.getValue("title");
                    } else if (qName.equalsIgnoreCase("name")) {
                        inName = true;
                        name = "";
                    } else if (qName.equalsIgnoreCase("phone")) {
                        inPhone = true;
                        phone = "";
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (inName) {
                        name += new String(ch, start, length);
                    } else if (inPhone) {
                        phone += new String(ch, start, length);
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("name")) {
                        inName = false;
                    } else if (qName.equalsIgnoreCase("phone")) {
                        inPhone = false;
                    } else if (qName.equalsIgnoreCase("employee")) {
                        String info = id + "-" + title + "-" + name + "-" + phone;
                        employeeList.add(info);
                        Log.d("EMPLOYEE", info);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
