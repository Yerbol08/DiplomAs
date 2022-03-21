package com.atu.diplomas.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.atu.diplomas.R;

public class ConferenceActivity extends AppCompatActivity {
    TextView nameTv, formaTv, languagesTv, locationTv, textView, organizer, dateTv;
    String nameConf, formaConf, languagesConf, locationConf, textConf, organizerConf, dateConf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);
        nameTv = findViewById(R.id.tvName);
        formaTv = findViewById(R.id.formatv);
        languagesTv = findViewById(R.id.languagesConf);
        locationTv = findViewById(R.id.locationTv);
        textView = findViewById(R.id.textView);
        organizer = findViewById(R.id.organizerTv);
        dateTv = findViewById(R.id.dateTv);
        nameConf = getIntent().getStringExtra("name");
        formaConf = getIntent().getStringExtra("forma");
        languagesConf = getIntent().getStringExtra("languages");
        locationConf = getIntent().getStringExtra("location");
        textConf = getIntent().getStringExtra("text");
        organizerConf = getIntent().getStringExtra("organizer");
        dateConf = getIntent().getStringExtra("date");
        nameTv.setText(nameConf);
        formaTv.setText("Форма участия: " + formaConf);
        languagesTv.setText("Язык информации: "+languagesConf);
        locationTv.setText(locationConf);
        textView.setText(textConf);
        organizer.setText("Организаторы: " + organizerConf);
        dateTv.setText("Последний день подачи заявки: " + dateConf);


    }
}