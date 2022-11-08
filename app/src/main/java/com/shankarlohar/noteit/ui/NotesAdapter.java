package com.shankarlohar.noteit.ui;


import static com.shankarlohar.noteit.utilities.Constants.NOTE_ID;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shankarlohar.noteit.EditorActivity;
import com.shankarlohar.noteit.data.model.NoteEntity;
import com.shankarlohar.noteit.data.viewmodel.MainViewModel;
import com.shankarlohar.noteit.databinding.NoteListItemBinding;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final List<NoteEntity> mNotes;
    private final Context context;
    private final MainViewModel mainViewModel;

    public NotesAdapter(List<NoteEntity> mNotes, Context context, MainViewModel mainViewModel) {
        this.mNotes = mNotes;
        this.context = context;
        this.mainViewModel = mainViewModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(NoteListItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NoteEntity note = mNotes.get(position);
        holder.binding.taskItemNote.setText(note.getText());
        holder.binding.taskItemTitle.setText(note.getTopic());
        holder.binding.editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditorActivity.class);
                intent.putExtra(NOTE_ID, note.getId());
                context.startActivity(intent);
            }
        });
        holder.binding.deleteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.deleteNote(note.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        NoteListItemBinding binding;

        public ViewHolder(NoteListItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }
}
