package com.example.appproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    //instanciar elementos conm id
    EditText reference, description, coste;
    Spinner refType;
    ImageButton save, search, edit, delete, list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // debemos referenciar estos objtos con los id respectivcamnete
        reference = findViewById(R.id.editReference);
        description = findViewById(R.id.editDescription);
        coste = findViewById(R.id.editPrice);
        refType = findViewById(R.id.spRefType);
        save = findViewById(R.id.idSave);
        search = findViewById(R.id.idSearch);
        edit = findViewById(R.id.idEdit);
        delete = findViewById(R.id.idDelete);
        list = findViewById(R.id.idList);

    }
}