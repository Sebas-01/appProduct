package com.example.appproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLData;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //instanciar elementos conm id
    EditText reference, description, coste;
    Spinner refType;
    ImageButton save, search, edit, delete, list;
    TextView message;
    //definir el arreglo para llenar el spinner
    String [] arrayTypeRef = {"Comestible"," No comestible"};

    //instanciar la clase donde esta la base de datos para actualizarla
    classDB oDB = new classDB(this, "dbProduct",null,1);

    //objeto para manipular la tabla de producto
    Product oProduct = new Product();
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

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mRef= reference.getText().toString();
                String mDesc = description.getText().toString();
                int mPrice = Integer.parseInt (coste.getText().toString());
                int mRefType = refType.getSelectedItem().toString().equals("Comestible") ? 1 : 0;
                if(checkData(mRef,mDesc, String.valueOf(mPrice))){
                    //actualizar el producto con todos los datos
                    SQLiteDatabase osdbWrite = oDB.getWritableDatabase();
                    osdbWrite.execSQL("Update product SET description ='"+ mDesc +"', price = " +mPrice+", typeRef = " +mRefType+" where reference = '"+mRef+"' ");

                    message.setTextColor(Color.GREEN);
                    message.setText("¡EL PRODUCTO HA SIDO ACTUALIZADO CON EXITO!");
                    
                }else {
                    message.setTextColor(Color.RED);
                    message.setText("Llena por favor todos los campos para actualizar...!");
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!reference.getText().toString().isEmpty())
                {
                    //buscar la refencia del producto a travez del metodo searchReference
                    if(searchReference(reference.getText().toString()).size()>0){
                        //tomar los datos del objeto oProduct l a cual esta global
                        //asingar el contenido de cada atributo a cada editText y spinner
                        description.setText(oProduct.getDescription());

                        //convertir de integrer a texto
                        coste.setText(String.valueOf(oProduct.getPrice()));

                        //seleccionar la opcion del spinner correspondiente para:
                        //1: comestible
                        //0: No comestible
                        refType.setSelection(oProduct.getTypeRef() == 1 ? 0 : 1);
                    }
                    else {
                        message.setTextColor(Color.RED);
                        message.setText("La referencia no existe, intenta con otra...");
                    }
                }
                else {
                    message.setTextColor(Color.RED);
                    message.setText("Ingrese la referencia a buscar");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mRef = reference.getText().toString(); //toma el contenido del edit text
                String mDesc = description.getText().toString();
                String mPrice = coste.getText().toString();
                String mTypeRef = refType.getSelectedItem().toString();
                if (checkData(mRef,mDesc,mPrice)){
                    if(searchReference(mRef).size()==0){
                        //Codigo para guardar el registro en la base de datos
                        SQLiteDatabase osdbWrite = oDB.getWritableDatabase();

                        //crear un contentValues para agregar el registro
                        ContentValues cvProduct = new ContentValues();

                        //asignar el contenido de cada campo con los valores ingresados
                        cvProduct.put("reference",mRef);
                        cvProduct.put("description",mDesc);
                        cvProduct.put("price",mPrice);
                        cvProduct.put("typeRef",mTypeRef.equals("Comestibe") ? 1 : 0);

                        //agregar  uevo producto a la tabla
                        osdbWrite.insert("product", null, cvProduct);
                        //cerrar la conexión
                        osdbWrite.close();
                        message.setTextColor(Color.GREEN);
                        message.setText("El producto se agrego correctamente");

                    }
                    else {
                        message.setTextColor(Color.GREEN);
                        message.setText("Este producto ya se encuentra en la base de datos...");
                    }
                }
                else{
                    message.setTextColor(Color.RED);
                    message.setText("LLENE TODOS LOS CAMPOS...");
                }
            }
        });

    }

    private ArrayList<Product> searchReference(String mRef) {

        //crear el objetdo de tipo array list que se retorna
        ArrayList<Product> arrProduct = new ArrayList<Product>();

        //crear un objeto de la clase sqliteDataBase
        SQLiteDatabase osdbRead = oDB.getReadableDatabase();
        String query = "select description, price, typeRef from product where reference = '"+mRef+"'";

        //Generar una tabla cursor para almacenar los datos del query
        Cursor cProduct = osdbRead.rawQuery(query, null);

        //chequear como quedo la tabla cursor
        if(cProduct.moveToFirst()){
            oProduct.setReference(mRef);
            oProduct.setDescription(cProduct.getString(0));
            oProduct.setPrice(cProduct.getInt(1));
            oProduct.setTypeRef(cProduct.getInt(2));
            arrProduct.add(oProduct);
        }
        return arrProduct;
    }

    private Boolean checkData(String mRef, String mDesc, String mPrice) {
        return !mRef.isEmpty() && !mDesc.isEmpty() && !mPrice.isEmpty() ;
    }
}