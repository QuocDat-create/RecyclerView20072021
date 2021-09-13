package com.quocdat.recyclerview20072021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    List<Food> list;
    FoodAdapter adapter;
    FloatingActionButton fbAdd;
    int mCurrentPage = 1;
    int mTotalPage = 10;
    boolean mLoading = false;
    boolean mLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        fbAdd = findViewById(R.id.fbAdd);
        list = Food.getMock();
        adapter = new FoodAdapter(list);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);

        adapter.setOnItemListener(new OnItemListener() {
            @Override
            public void onItemClickListener(int position) {
                Toast.makeText(MainActivity.this,
                        "Delete Item "+list.get(position).getName(),
                        Toast.LENGTH_SHORT)
                        .show();
                list.remove(list.get(position));
                adapter.notifyItemRemoved(position);
            }
        });

        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });

        rv.addOnScrollListener(new PaginationScrollListener((LinearLayoutManager) rv.getLayoutManager()) {
            @Override
            public void loadMoreItem() {
                mLoading = true;
                mCurrentPage += 1;

                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return mLoading;
            }

            @Override
            public boolean isLastPage() {
                return mLastPage;
            }
        });
    }

    //Load information from new data
    private void loadNextPage(){
        new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (mCurrentPage > 2){
                    adapter.removeLoading();
                }
                list.addAll(Food.getMock());
                adapter.notifyDataSetChanged();
                mLoading = false;

                if (mCurrentPage < mTotalPage){
                    adapter.addFooterLoading();
                }else {
                    mLastPage = true;
                }
            }
        }.start();
    }

    private void openDialogAdd(){

        //Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.add_item, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        //Init
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtPrice = view.findViewById(R.id.edtPrice);
        Button btnAdd = view.findViewById(R.id.btnAdd);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                long mPrice = Long.parseLong(price);
                dialog.dismiss();
                Food food = new Food(name, mPrice);
                list.add(food);
                adapter.notifyDataSetChanged();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "You canceled this window!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}