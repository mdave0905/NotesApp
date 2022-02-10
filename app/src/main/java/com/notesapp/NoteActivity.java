package com.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class NoteActivity extends AppCompatActivity {

    String id;
    RelativeLayout bg;
    ImageButton back,save,image,textIcon,color,addText;
    EditText title,text,secondText;
    RelativeLayout imageLayout;
    RelativeLayout textLayout;
    HorizontalScrollView colorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        bg = findViewById(R.id.background);
        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        image = findViewById(R.id.image);
        textIcon = findViewById(R.id.textIcon);
        title = findViewById(R.id.title);
        text = findViewById(R.id.text);
        color = findViewById(R.id.color);
        addText = findViewById(R.id.addText);
        secondText = findViewById(R.id.secondText);
        id = getIntent().getStringExtra("ID");

        imageLayout = findViewById(R.id.imageLayout);
        textLayout = findViewById(R.id.textLayout);
        colorLayout = findViewById(R.id.colorLayout);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteActivity.this,MainActivity.class));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageLayout.getVisibility()==View.GONE){
                    addText.setImageResource(R.drawable.text_editor_black);
                    addText.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                    textIcon.setImageResource(R.drawable.text_disabled);
                    textIcon.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                    image.setImageResource(R.drawable.image_enabled);
                    image.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.black));
                    imageLayout.setVisibility(View.VISIBLE);
                    colorLayout.setVisibility(View.GONE);
                    textLayout.setVisibility(View.GONE);
                }else{
                    imageLayout.setVisibility(View.GONE);
                    image.setImageResource(R.drawable.image_disabled);
                    image.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                }
            }
        });

        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (secondText.getVisibility() == View.GONE) {
                    secondText.setVisibility(View.VISIBLE);
                    addText.setImageResource(R.drawable.text_editor_white);
                    addText.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this, R.color.black));
                }else{
                    secondText.setVisibility(View.GONE);
                    addText.setImageResource(R.drawable.text_editor_black);
                    addText.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this, R.color.white));
                }
            }
                //Another implementation to add Edit Text Dynamically but I left it because the instructions were not clear
                /*
                if(findViewById(R.id.newET)==null) {
                    addText.setImageResource(R.drawable.text_editor_white);
                    addText.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this, R.color.black));
                    EditText editText = new EditText(NoteActivity.this);
                    editText.setHint("Add Text");
                    editText.setId(R.id.newET);
                    editText.setHintTextColor(getResources().getColor(R.color.hint));
                    editText.setTextColor(getResources().getColor(R.color.white));
                    editText.setTextSize(16f);
                    editText.setMinLines(2);
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
                    editText.setBackground(null);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(56, 560, 56, 0);
                    editText.setLayoutParams(params);
                    bg.addView(editText);
                }

                 */
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorLayout.getVisibility()==View.GONE){
                    addText.setImageResource(R.drawable.text_editor_black);
                    addText.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                    colorLayout.setVisibility(View.VISIBLE);
                }else{
                    colorLayout.setVisibility(View.GONE);
                }
            }
        });

        textIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textLayout.getVisibility()==View.GONE){
                    imageLayout.setVisibility(View.GONE);
                    addText.setImageResource(R.drawable.text_editor_black);
                    addText.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                    image.setImageResource(R.drawable.image_disabled);
                    image.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                    textIcon.setImageResource(R.drawable.text_enabled);
                    textIcon.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.black));
                    textLayout.setVisibility(View.VISIBLE);
                }else{
                    textIcon.setImageResource(R.drawable.text_disabled);
                    textIcon.setBackgroundTintList(AppCompatResources.getColorStateList(NoteActivity.this,R.color.white));
                    textLayout.setVisibility(View.GONE);
                    colorLayout.setVisibility(View.GONE);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<>();
                map.put("title",title.getText().toString());
                map.put("text",text.getText().toString());
                FirebaseDatabase.getInstance("https://notes-app-2d2a7-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Notes").child(id).updateChildren(map);
                Toast.makeText(NoteActivity.this, "Saved Note", Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseDatabase.getInstance("https://notes-app-2d2a7-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Notes").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    switch (snapshot.child("color").getValue().toString()){
                        case "Yellow":
                            bg.setBackgroundResource(R.color.yellow);
                            break;
                        case "Blue":
                            bg.setBackgroundResource(R.color.blue);
                            break;
                        case "Orange":
                            bg.setBackgroundResource(R.color.orange);
                            break;
                        case "Pink":
                            bg.setBackgroundResource(R.color.pink);
                            break;
                        case "Purple":
                            bg.setBackgroundResource(R.color.purple);
                            break;
                        case "Green":
                            bg.setBackgroundResource(R.color.green);
                            break;
                    }
                    title.setText(snapshot.child("title").getValue().toString());
                    text.setText(snapshot.child("text").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(NoteActivity.this,MainActivity.class));
    }
}