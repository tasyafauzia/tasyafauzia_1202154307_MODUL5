package com.tasyafauzia.android.tasyafauzia_1202154307_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
public class ToDoList extends AppCompatActivity {
    Database dbase;
    RecyclerView revi;
    Adapter adapter;
    ArrayList<Data> listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        setTitle("To do list");

        //untuk mengakses recyclerview
        revi = findViewById(R.id.recview);
        //untuk embuat araylist baru
        listdata = new ArrayList<>();
        //untuk membuat database baru
        dbase = new Database(this);
        //untuk memanggil method readdata
        dbase.readdata(listdata);

        //melakukan inisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colour", R.color.white);

        //untuk mengcreate adapter yang baru
        adapter = new Adapter(this,listdata, color);
        revi.setHasFixedSize(true);
        //menampilkan layout linear
        revi.setLayoutManager(new LinearLayoutManager(this));
        //melakukan inisiasi adapter pada recyclerview
        revi.setAdapter(adapter);

        //menjalankan method hapus data pada TodoList
        hapus();
    }

    //membuat method untuk menghapus item
    public void hapus(){
        //membuat touch helper baru untuk digunakan pada recyclerview
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Data current = adapter.getData(position);
                //jika item di swipe ke kiri
                if(direction==ItemTouchHelper.LEFT){
                    //untuk melakukan remove item yang dipilih dan mengenali posisi todonya sebagai primary key
                    if(dbase.removedata(current.getTodo())){
                        //untuk menghapus data
                        adapter.deleteData(position);
                        //membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi 5 sekon
                        Snackbar.make(findViewById(R.id.coor), "Data was Delete", 5000).show();
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(revi);
    }
    //Pada saat menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item
        int id = item.getItemId();
        //apabila item yang dipilih adalah action_settings
        if (id==R.id.action_settings){
            //membuat intent baru dari ToDoList ke Settings
            Intent intent = new Intent(ToDoList.this, Settings.class);
            //memulai intent
            startActivity(intent);
            //menutup aktivitas
            finish();
        }
        return true;
    }

    //method yang dijalankan pada saat tombol add di klik
    public void add(View view) {
        //intent dari list to do ke add to do
        Intent intent = new Intent(ToDoList.this, Addtodo.class);
        //memulai intent
        startActivity(intent);
    }
}
