package com.tasyafauzia.android.tasyafauzia_1202154307_modul5;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
/**
 * Created by Tasya Fauzia on 3/24/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    //Mendeklarasikan variable yang akan digunakan
    private Context cntx;
    private List<Data> list;
    int color;

    //Mndeklarasikan konstruktor
    public Adapter(Context cntx, List<Data> list, int color){
        this.cntx=cntx;
        this.list=list;
        this.color=color;
    }

    //Menentukan viewholder untuk diterapkan pada recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //untuk membuat view yang baru
        View view = LayoutInflater.from(cntx).inflate(R.layout.cardview, parent, false);
        holder hldr = new holder(view);
        return hldr;
    }

    //mensetting nilai yang didapat dari viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        Data data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(cntx.getResources().getColor(this.color));
    }

    //untuk get jumlah list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //mendapatkan list dari adapter
    public Data getData(int position){
        return list.get(position);
    }

    //menghapus list pada toolist
    public void deleteData(int i){
        //merupakan perintah untuk melakukan remove pada item yang telah dipilih
        list.remove(i);
        //memunculkan notifikasi pada item yang telah di remove
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //Mendeklarasikan variable yang akan digunakan
        public TextView ToDo, Description, Priority;
        public CardView cardv;
        public holder(View itemView){
            super(itemView);


            ToDo = itemView.findViewById(R.id.headline);
            Description = itemView.findViewById(R.id.explanation);
            Priority = itemView.findViewById(R.id.number);
            cardv = itemView.findViewById(R.id.cardlist);
        }
    }
}
