package com.example.appproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //instanciar elementos conm id
    EditText reference, description, coste;
    Spinner refType;
    ImageButton save, search, edit, delete, list;
    TextView message;
    //definir el arreglo para llenar el spinner
    String [] arrayTypeRef = {"Comestible"," No comestible"};
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
        message = findViewById(R.id.tvMessage);

        //definir el array dapater
        ArrayAdapter<String>adpTypeRef = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,arrayTypeRef);

        //asiganar el adaptador al spiner
        refType.setAdapter(adpTypeRef);

        //eventos de cada boton
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mRef = reference.getText().toString(); //toma el contenido del edit text
                String mDesc = description.getText().toString();
                String mPrice = coste.getText().toString();
                if (checkData(mRef,mDesc,mPrice)){
                    searchReference(mRef);
                }
                else{
                    message.setTextColor(Color.RED);
                    message.setText("LLENE TODOS LOS CAMPOS...");
                }
            }
        });

    }

    private boolean searchReference(String mRef) {

        
    }

    private Boolean checkData(String mRef, String mDesc, String mPrice) {
        return !mRef.isEmpty() && !mDesc.isEmpty() && !mPrice.isEmpty() ;
    }
}