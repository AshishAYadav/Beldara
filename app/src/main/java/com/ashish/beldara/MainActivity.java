package com.ashish.beldara;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int id = 0;
    private EditText Name, Mobile;
    private Spinner Model;
    private Button Add, Remove;
    private RecyclerView Display;
    private ArrayList<Beldara> data, RemovalList;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.name);
        Mobile = findViewById(R.id.mobile_number);
        Model = findViewById(R.id.mobile_company);
        Add = findViewById(R.id.add);
        Display = findViewById(R.id.display);
        Remove = findViewById(R.id.remove);

        RemovalList = new ArrayList<>();

        List<String> model = new ArrayList<>();
        model.add("Samsung");
        model.add("Vivo");
        model.add("iPhone");

        Model.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, model));
        data = new ArrayList<>();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(new Beldara(++id, Name.getText().toString(), Mobile.getText().toString(), Model.getSelectedItem().toString()));
                Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                Display.setAdapter(adapter);
                Remove.setVisibility(View.VISIBLE);
                for(int i = 0; i<data.size(); i++)
                    Log.d("data", String.valueOf(data.get(i).getId()));

            }
        });

        adapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Context context = parent.getContext();
                LayoutInflater inflater = LayoutInflater.from(context);

                View contactView = inflater.inflate(R.layout.adapter, parent, false);

                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(contactView) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

                final Beldara currentData = data.get(position);

                TextView TxtId = holder.itemView.findViewById(R.id.txtid);
                TxtId.setText(String.valueOf(currentData.getId()));

                TextView TxtName = holder.itemView.findViewById(R.id.txtName);
                TxtName.setText(currentData.getName().toString());

                TextView TxtMobile = holder.itemView.findViewById(R.id.txtMobile);
                TxtMobile.setText(currentData.getNumber().toString());

                TextView TxtModel = holder.itemView.findViewById(R.id.txtModel);
                TxtModel.setText(currentData.getModel().toString());

                CheckBox removeBox = holder.itemView.findViewById(R.id.checkbox);

                removeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            RemovalList.add(currentData);
                            Log.d("removeid", "Added "+currentData.getId());
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };

        Display.setLayoutManager(new LinearLayoutManager(this));

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RemovalList.size()==0){
                    Toast.makeText(MainActivity.this, "Please select atleast 1 item to delete",Toast.LENGTH_SHORT).show();
                } else {

                    data.removeAll(RemovalList);
                    if(data.size()==0){
                        Remove.setVisibility(View.INVISIBLE);
                    }
                    RemovalList = new ArrayList<>();
                    Display.setAdapter(adapter);
                    for(int i = 0; i<data.size(); i++)
                        Log.d("data", String.valueOf(data.get(i).getId()));
                }

            }
        });
    }
}