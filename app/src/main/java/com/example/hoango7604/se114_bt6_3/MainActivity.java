package com.example.hoango7604.se114_bt6_3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button btnRead;
    Button btnWrite;
    EditText edtRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        handleClick();
    }

    private void initView() {
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        edtRead = findViewById(R.id.edtRead);
    }

    private void handleClick() {
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
        
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile();
            }
        });
    }

    private void writeFile() {
        try {
            OutputStream out = openFileOutput("myfile.txt", Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-16LE");
            writer.write(edtRead.getText().toString());
            writer.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(){
        try {
            String data;
            InputStream in = openFileInput("myfile.txt");
            InputStreamReader reader = new InputStreamReader(in, "UTF-16LE");
            BufferedReader buffer = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();

            while ((data = buffer.readLine()) != null){
                builder.append(data);
                builder.append("\n");
            }

            in.close();
            edtRead.setText(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
