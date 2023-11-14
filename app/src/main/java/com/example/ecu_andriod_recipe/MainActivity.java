package com.example.ecu_andriod_recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    EditText edtTitle, edtDescription;
    Button btnSubmit, btnSearch;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSearch = findViewById(R.id.btnSearch);

        // Initialize Firebase Storage
        storage = FirebaseStorage.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String description = edtDescription.getText().toString();

                if (!title.isEmpty() && !description.isEmpty()) {
                    uploadRecipeToFirebase(title, description);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter title and description!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    private void uploadRecipeToFirebase(String title, String description) {
        StorageReference fileRef = storage.getReference().child("recipes/" + title + ".txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.writeBytes(description.getBytes(StandardCharsets.UTF_8));
        byte[] data = baos.toByteArray();

        fileRef.putBytes(data)
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(MainActivity.this, "Recipe added!", Toast.LENGTH_SHORT).show();

                    // Create an Intent to start MainActivity2
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    // Put the title and description into the Intent
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    // Start MainActivity2
                    startActivity(intent);
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error adding recipe!", Toast.LENGTH_SHORT).show());
    }

}
