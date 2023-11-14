package com.example.ecu_andriod_recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<String> recipeTitles;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // Constructor
    RecipeAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.recipeTitles = data;
    }

    // Interface for click events
    public interface ItemClickListener {
        void onItemClick(String title);
    }

    // Method to set the click listener
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = recipeTitles.get(position);
        holder.myTextView.setText(title);
        holder.setData(title);
    }

    @Override
    public int getItemCount() {
        return recipeTitles.size();
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        private String currentTitle; // Field to store the current title

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this); // Set the click listener
        }

        // Method to set the data on the ViewHolder
        public void setData(String title) {
            this.currentTitle = title;
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(currentTitle);
            }
        }
    }
}
