package com.example.lab19.persistence.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskAccess {

    @Insert
    void persistTask(TaskEntry task);

    @Delete
    void removeTask(TaskEntry task);

    @Query("DELETE FROM app_tasks")
    void clearAllTasks();

    @Query("SELECT * FROM app_tasks ORDER BY uid DESC")
    LiveData<List<TaskEntry>> fetchTasks();
}
