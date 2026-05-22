package com.example.lab19.logic;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.lab19.persistence.db.TaskEntry;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository dataCenter;
    private final LiveData<List<TaskEntry>> observableTasks;

    public TaskViewModel(@NonNull Application app) {
        super(app);
        dataCenter = new TaskRepository(app);
        observableTasks = dataCenter.getItems();
    }

    public void commit(TaskEntry entry) {
        dataCenter.addEntry(entry);
    }

    public void discard(TaskEntry entry) {
        dataCenter.removeEntry(entry);
    }

    public void clearEverything() {
        dataCenter.wipeAll();
    }

    public LiveData<List<TaskEntry>> getTaskStream() {
        return observableTasks;
    }
}
