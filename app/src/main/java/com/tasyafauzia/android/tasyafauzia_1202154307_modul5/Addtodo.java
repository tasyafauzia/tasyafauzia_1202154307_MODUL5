package com.tasyafauzia.android.tasyafauzia_1202154307_modul5;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Addtodo extends AppCompatActivity {
    //Mendeklarasikan variable yang akan digunakan
    EditText ToDo, Description, Priority;
    Database dtbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtodo);
        //Men-set title dengan tulisan Add To Do
        setTitle("Add To Do");

        //Mengakses/mengambil id sebuah ediText yang sudah disetting sebelumnya pada xml
        ToDo = (EditText) findViewById(R.id.edittodo);
        Description = (EditText) findViewById(R.id.editdescrip);
        Priority = (EditText) findViewById(R.id.editprio);
        dtbase = new Database(this);
    }

    @Override
    public void onBackPressed() {
        //Intent from Addtodo to ToDoList
        Intent intent = new Intent(Addtodo.this,ToDoList.class);
        //memulai intent
        startActivity(intent);
        //Menutup aktivitas pada saat setelah intent dijalankan
        this.finish();
    }

    //Method yang akan dijalankan pada saat tombol (+) to do di klik
    public void tambah(View view) {
        //logika jika kolom padatodo, description, dan priority di isikan
        if (dtbase.inputdata(new Data(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()))){
            //Toast dimunculkan jika data yang dimasukan sudah berhasil
            Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
            //akan berpindah dari Addtodo menuju ListToDo
            startActivity(new Intent(Addtodo.this, ToDoList.class));
            //Menutup aktivitas
            this.finish();
        }else {
            //jika edit text/kolomnya kosong maka akan memunculkan toast dibawah ini
            Toast.makeText(this, "Cannot add the list", Toast.LENGTH_SHORT).show();
            //mensetting semua edit text menjadi empty
            ToDo.setText(null);
            Description.setText(null);
            Priority.setText(null);
        }
    }

}
