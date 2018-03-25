package com.tasyafauzia.android.tasyafauzia_1202154307_modul5;

/**
 * Created by Tasya Fauzia on 3/24/2018.
 */

public class Data {
    //Mendeklarasi variable
    String todo, desc, prior;


    public Data(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //Merupakan method setter dan getter untuk to do , description dan priority
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
