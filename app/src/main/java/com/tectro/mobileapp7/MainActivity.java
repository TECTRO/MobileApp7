package com.tectro.mobileapp7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.tectro.mobileapp7.Adapters.ListAdapter;
import com.tectro.mobileapp7.Dialogs.AddDialog;
import com.tectro.mobileapp7.Model.Model;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity implements Runnable {

    private Model model;
    private ListAdapter Adapter2;
    private ListAdapter Adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = Model.CreateInstance();
        model.Subscribe(this);

        Adapter1 = new ListAdapter(this, model.getList1(),getSupportFragmentManager());
        Adapter2 = new ListAdapter(this, model.getList2(),getSupportFragmentManager());
        ((RecyclerView)findViewById(R.id.list_holder_1)).setAdapter(Adapter1);
        ((RecyclerView)findViewById(R.id.list_holder_2)).setAdapter(Adapter2);
    }

    public void AddItem(View view) {
        AddDialog.GetDialog().show(getSupportFragmentManager(),"cst");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.Unsubscribe(this);
    }

    @Override
    public void run() {
        Adapter1.notifyDataSetChanged();
        Adapter2.notifyDataSetChanged();
    }
}