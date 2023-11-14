package com.example.ecu_andriod_recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.nio.charset.StandardCharsets;

public class MainActivity2 extends AppCompatActivity {

    private TextView txtDescription, txtTitle; // Assuming you have a TextView in your layout
    private Button returnHomeBtn, returnRecipeListBtn;; // Button to return to MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize your TextViews and Button
        txtTitle = findViewById(R.id.titleTextView);
        txtDescription = findViewById(R.id.descriptionTextView);
        returnHomeBtn = findViewById(R.id.returnHomeBtn);
        returnRecipeListBtn = findViewById(R.id.returnRecipeListBtn);

        // Retrieve the data passed from MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String description = extras.getString("description");

            txtTitle.setText(title);
            txtDescription.setText(description);

            if (title != null) {
                fetchRecipeDetails(title);
            }
        }

        // Set OnClickListener for the button
        returnRecipeListBtn.setOnClickListener(view -> {
            // Intent to go back to MainActivity
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        });

        // Set OnClickListener for the button
        returnHomeBtn.setOnClickListener(view -> {
            // Intent to go back to MainActivity
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void fetchRecipeDetails(String title) {
        StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("recipes/" + title + ".txt");
        fileRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(bytes -> {
            String description = new String(bytes, StandardCharsets.UTF_8);
            txtDescription.setText(description); // Update the description TextView
        }).addOnFailureListener(e -> {
            // Handle any errors
        });
    }
}
