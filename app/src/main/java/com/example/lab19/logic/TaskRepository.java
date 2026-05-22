package com.example.lab19.logic;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.lab19.persistence.db.AppDatabase;
import com.example.lab19.persistence.db.TaskAccess;
import com.example.lab19.persistence.db.TaskEntry;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private final TaskAccess access;
    private final LiveData<List<TaskEntry>> listData;
    private final ExecutorService ioExecutor;

    public TaskRepository(Application app) {
        AppDatabase db = AppDatabase.getDatabase(app);
        access = db.taskAccess();
        listData = access.fetchTasks();
        ioExecutor = Executors.newFixedThreadPool(2);
    }

    public void addEntry(TaskEntry entry) {
        ioExecutor.execute(() -> access.persistTask(entry));
    }

    public void removeEntry(TaskEntry entry) {
        ioExecutor.execute(() -> access.removeTask(entry));
    }

    public void wipeAll() {
        ioExecutor.execute(access::clearAllTasks);
    }

    public LiveData<List<TaskEntry>> getItems() {
        return listData;
    }
}
