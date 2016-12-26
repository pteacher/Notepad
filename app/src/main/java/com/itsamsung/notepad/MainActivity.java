package com.itsamsung.notepad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText note;
    EditText filename;
    Button save, load;
    private static final String TAG = "Notepad_app";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        note = (EditText) findViewById(R.id.editText);
        filename = (EditText) findViewById(R.id.editText2);
        save = (Button) findViewById(R.id.button);
        load = (Button) findViewById(R.id.button2);
        loadFile("hello2.txt");

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFile(filename.getText().toString());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile(filename.getText().toString());
            }
        });
    }

    private void saveFile(String fileName) {
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(note.getText().toString());
            osw.close();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this,
                    "Exception" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFile(String fileName) {
        try {
            Log.i(TAG, "file:" + fileName);
            InputStream fin = openFileInput(fileName);

            if (fin != null) {
                InputStreamReader isr = new InputStreamReader(fin);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                fin.close();
                note.setText(builder.toString());
            }
        }
        catch (Exception e) {
            Toast.makeText(
                    MainActivity.this,
                    e.getMessage(),
                    Toast.LENGTH_SHORT).show();
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
