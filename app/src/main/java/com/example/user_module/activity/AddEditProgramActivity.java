package com.example.user_module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.user_module.AppDatabase;
import com.example.user_module.R;
import com.example.user_module.entity.Program;

import java.util.concurrent.Executors;

public class AddEditProgramActivity extends AppCompatActivity {

    private EditText editTextName, editTextDescription, editTextType;
    private Button buttonSaveProgram;
    private int programId = -1; // Default -1 indicates a new program

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_program);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextType = findViewById(R.id.editTextType);
        buttonSaveProgram = findViewById(R.id.buttonSaveProgram);

        programId = getIntent().getIntExtra("programId", -1);

        // Load program data if editing an existing program
        if (programId != -1) {
            loadProgramData();
        }

        buttonSaveProgram.setOnClickListener(v -> saveProgram());
    }

    private void loadProgramData() {
        AppDatabase db = AppDatabase.getInstance(this);
        db.programDao().getProgramById(programId).observe(this, program -> {
            if (program != null) {
                editTextName.setText(program.getName());
                editTextDescription.setText(program.getDescription());
                editTextType.setText(program.getType());
            }
        });
    }

    private void saveProgram() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String type = editTextType.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Program program = new Program();
        program.setName(name);
        program.setDescription(description);
        program.setType(type);

        if (programId != -1) {
            program.setId(programId); // Set the ID for updating
        }

        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            if (programId == -1) {
                db.programDao().insert(program); // Insert new program
            } else {
                db.programDao().update(program); // Update existing program
            }

            runOnUiThread(() -> {
                Toast.makeText(this, "Program saved", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish(); // Close activity
            });
        });
    }
}
