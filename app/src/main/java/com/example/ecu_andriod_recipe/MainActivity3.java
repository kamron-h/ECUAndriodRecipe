package com.example.ecu_andriod_recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity implements RecipeAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private RecipeAdapter adapter;
    private List<String> recipeTitles;
    private FirebaseStorage storage;
    private Button returnHomeBtn; // Button to return to MainActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView = findViewById(R.id.recyclerView);
        recipeTitles = new ArrayList<>();
        adapter = new RecipeAdapter(this, recipeTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        returnHomeBtn = findViewById(R.id.returnHomeBtn3);

        storage = FirebaseStorage.getInstance();
        adapter.setClickListener(this); // Set the click listener
        loadRecipeTitles();

        // Set OnClickListener for the button
        returnHomeBtn.setOnClickListener(view -> {
            // Intent to go back to MainActivity
            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void loadRecipeTitles() {
        StorageReference recipesRef = storage.getReference().child("recipes");
        recipesRef.listAll()
                .addOnSuccessListener(listResult -> {
                    for (StorageReference item : listResult.getItems()) {
                        String title = item.getName().replace(".txt", "");
                        recipeTitles.add(title);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity3.this, "Error loading recipes", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onItemClick(String title) {
        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
