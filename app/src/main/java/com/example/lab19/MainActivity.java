package com.example.lab19;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19.display.TaskAdapter;
import com.example.lab19.logic.TaskViewModel;
import com.example.lab19.persistence.db.TaskEntry;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private TextInputEditText inputHeader;
    private TextInputEditText inputBody;
    private Button actionCommit;
    private Button actionClear;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterface();
        setupLogic();
    }

    private void initializeInterface() {
        inputHeader = findViewById(R.id.inputHeader);
        inputBody = findViewById(R.id.inputBody);
        actionCommit = findViewById(R.id.actionCommit);
        actionClear = findViewById(R.id.actionClear);

        RecyclerView listRegistry = findViewById(R.id.listRegistry);
        listRegistry.setLayoutManager(new LinearLayoutManager(this));
        listRegistry.setHasFixedSize(true);

        taskAdapter = new TaskAdapter();
        listRegistry.setAdapter(taskAdapter);

        actionCommit.setOnClickListener(v -> handleSubmission());

        actionClear.setOnClickListener(v -> {
            taskViewModel.clearEverything();
            showFeedback("Base de données réinitialisée");
        });

        taskAdapter.setListener(new TaskAdapter.OnInteractionListener() {
            @Override
            public void onShortPress(TaskEntry entry) {
                showFeedback("Sélection : " + entry.getHeader());
            }

            @Override
            public void onLongPress(TaskEntry entry) {
                taskViewModel.discard(entry);
                showFeedback("Entrée supprimée");
            }
        });
    }

    private void setupLogic() {
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getTaskStream().observe(this, tasks -> {
            taskAdapter.updateData(tasks);
        });
    }

    private void handleSubmission() {
        String header = inputHeader.getText().toString().trim();
        String body = inputBody.getText().toString().trim();

        if (header.isEmpty() || body.isEmpty()) {
            showFeedback("Veuillez remplir tous les champs");
            return;
        }

        TaskEntry newEntry = new TaskEntry(header, body);
        taskViewModel.commit(newEntry);

        inputHeader.setText("");
        inputBody.setText("");
        showFeedback("Tâche enregistrée avec succès");
    }

    private void showFeedback(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
