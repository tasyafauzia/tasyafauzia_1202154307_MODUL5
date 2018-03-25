package com.tasyafauzia.android.tasyafauzia_1202154307_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    TextView clr;
    int colorid;
    AlertDialog.Builder alert;
    SharedPreferences.Editor shp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setTitle("Settings");

        //untuk membuat alert dialog yang baru
        alert = new AlertDialog.Builder(this);

        //melakukan inisialisasi sharedPreferences
        SharedPreferences shared = getApplicationContext().getSharedPreferences("Preferences", 0);
        shp = shared.edit();
        colorid = shared.getInt("Colour", R.color.white);
        //melakukan akses textview pada layout
        clr = findViewById(R.id.color);
        //mensetting color dengan color yang dipilih dan tentunya yang sudah ditentukan
        clr.setText(getShapeColor(colorid));
    }

    //pada saat tombol back diklik
    @Override
    public void onBackPressed() {
        //intent dari setting menuju ToDoList class
        Intent intent = new Intent(Settings.this, ToDoList.class);
        //memulai intent
        startActivity(intent);
        //menutup aktivity
        finish();
    }

    //Methode yang digunakan pada saat menu akan dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //pertintah untuk menjalankan method
            this.onBackPressed();
        }
        return true;
    }

    //mendapatkan string warna
    public String getShapeColor(int i){
        if (i==R.color.red){
            return "Red";
        }else if (i==R.color.green){
            return "Green";
        }else if (i==R.color.blue){
            return "Blue";
        }else{
            return "Default";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.red){
            return R.id.red;
        }else if (i==R.color.green){
            return R.id.green;
        }else if (i==R.color.blue){
            return R.id.blue;
        }else{
            return R.id.white;
        }
    }

    public void choosecolor(View view) {
        //set title dari alert dialog
        alert.setTitle("Color");
        //untuk membuat view baru
        View view1 = getLayoutInflater().inflate(R.layout.color, null);
        //untuk menampilkan view yang telah dibuat
        alert.setView(view1);

        //untuk mengakses radiogrup pada layout
        final RadioGroup radG = view1.findViewById(R.id.radiocolor);
        radG.check(getColorid(colorid));

        //Pada saat klik Ok pada alert dialog yang muncul
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id radio button yang di pilih
                int a = radG.getCheckedRadioButtonId();
                switch (a){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.white:
                        colorid = R.color.white;
                        break;
                }
                //set color dengan colorid yang ada
                clr.setText(getShapeColor(colorid));
                //menyimpan sharedpreference
                shp.putInt("Colour", colorid);
                //commit shared preference
                shp.commit();
            }
        });

        //pada saat klik cancel pada Alert Dialog
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //untuk menampilakn serta membuat alert dialog
        alert.create().show();
    }
}
