package com.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageButton addButton;
    LinearLayout colorLayout;
    TextView title,subtitle;
    ImageButton pink,yellow,purple,green,orange,blue;
    RecyclerView notesRv;
    NotesAdapter notesAdapter;
    ArrayList<NoteModel> noteList = new ArrayList<>();
    DatabaseReference noteRef = FirebaseDatabase.getInstance("https://notes-app-2d2a7-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Notes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        notesRv = findViewById(R.id.notesRv);
        title = findViewById(R.id.title);
        pink = findViewById(R.id.pink);
        yellow = findViewById(R.id.yellow);
        purple = findViewById(R.id.purple);
        green = findViewById(R.id.green);
        orange = findViewById(R.id.orange);
        blue = findViewById(R.id.blue);
        subtitle = findViewById(R.id.subtitle);
        colorLayout = findViewById(R.id.colorLayout);
        addButton = findViewById(R.id.addButton);

        notesRv.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        notesAdapter = new NotesAdapter(noteList,this);
        notesRv.setAdapter(notesAdapter);
        getNotes();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorLayout.getVisibility()==View.VISIBLE){
                    hideView();
                }else{
                    showView();
                }
            }
        });

        pink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                Map<String,Object> map = new HashMap<>();
                map.put("date",dateString);
                map.put("id",timestamp);
                map.put("color","Pink");
                map.put("title","");
                map.put("text","");
                noteRef.child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getNotes();
                        hideView();
                    }
                });
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                Map<String,Object> map = new HashMap<>();
                map.put("date",dateString);
                map.put("id",timestamp);
                map.put("color","Orange");
                map.put("title","");
                map.put("text","");
                noteRef.child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getNotes();
                        hideView();
                    }
                });
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                Map<String,Object> map = new HashMap<>();
                map.put("date",dateString);
                map.put("id",timestamp);
                map.put("color","Yellow");
                map.put("title","");
                map.put("text","");
                noteRef.child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getNotes();
                        hideView();
                    }
                });
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                Map<String,Object> map = new HashMap<>();
                map.put("date",dateString);
                map.put("id",timestamp);
                map.put("color","Purple");
                map.put("title","");
                map.put("text","");
                noteRef.child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getNotes();
                        hideView();
                    }
                });
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                Map<String,Object> map = new HashMap<>();
                map.put("date",dateString);
                map.put("id",timestamp);
                map.put("color","Green");
                map.put("title","");
                map.put("text","");
                noteRef.child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getNotes();
                        hideView();
                    }
                });
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                Map<String,Object> map = new HashMap<>();
                map.put("date",dateString);
                map.put("id",timestamp);
                map.put("color","Blue");
                map.put("title","");
                map.put("text","");
                noteRef.child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getNotes();
                        hideView();
                    }
                });

            }
        });
    }

    private void hideView() {
        addButton.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
        addButton.setBackgroundTintList(AppCompatResources.getColorStateList(MainActivity.this,R.color.white));
        colorLayout.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        subtitle.setVisibility(View.VISIBLE);
    }

    private void showView(){
        addButton.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
        addButton.setBackgroundTintList(AppCompatResources.getColorStateList(MainActivity.this,R.color.black));
        colorLayout.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
        subtitle.setVisibility(View.GONE);
    }

    private void getNotes() {
        noteList.clear();
        noteRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    NoteModel noteModel = ds.getValue(NoteModel.class);
                    noteList.add(noteModel);
                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}