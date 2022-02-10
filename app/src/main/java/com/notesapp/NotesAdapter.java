package com.notesapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<NoteModel> noteList = new ArrayList<>();
    private Context context;


    public NotesAdapter(ArrayList<NoteModel> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        NoteModel model = noteList.get(position);
        if(model.getTitle().equals("")){
            holder.title.setText("Title");
        }else{
            holder.title.setText(model.getTitle());
        }

        holder.date.setText(model.getDate());
        holder.text.setText(model.getText());

        switch (model.getColor()){
            case "Yellow":
                holder.noteCard.setCardBackgroundColor(context.getResources().getColor(R.color.yellow));
                break;
            case "Blue":
                holder.noteCard.setCardBackgroundColor(context.getResources().getColor(R.color.blue));
                break;
            case "Orange":
                holder.noteCard.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
                break;
            case "Pink":
                holder.noteCard.setCardBackgroundColor(context.getResources().getColor(R.color.pink));
                break;
            case "Purple":
                holder.noteCard.setCardBackgroundColor(context.getResources().getColor(R.color.purple));
                break;
            case "Green":
                holder.noteCard.setCardBackgroundColor(context.getResources().getColor(R.color.green));
                break;
        }


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,NoteActivity.class).putExtra("ID",model.getId()));
            }
        });

        holder.noteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.delete.getVisibility()==View.VISIBLE){
                    holder.delete.setVisibility(View.GONE);
                    holder.deletePrompt.setVisibility(View.VISIBLE);
                }else{
                    context.startActivity(new Intent(context,NoteActivity.class).putExtra("ID",model.getId()));
                }
            }
        });

        holder.deletePrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.delete.setVisibility(View.VISIBLE);
                holder.deletePrompt.setVisibility(View.GONE);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.delete.setVisibility(View.GONE);
                holder.deletePrompt.setVisibility(View.VISIBLE);
                FirebaseDatabase.getInstance("https://notes-app-2d2a7-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("Notes").child(model.getId()).removeValue();
                noteList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,noteList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton edit,delete,deletePrompt;
        TextView date,text,title;
        CardView noteCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            deletePrompt = itemView.findViewById(R.id.deletePrompt);
            date = itemView.findViewById(R.id.date);
            text = itemView.findViewById(R.id.text);
            title = itemView.findViewById(R.id.title);
            noteCard = itemView.findViewById(R.id.noteCard);

        }
    }
}
