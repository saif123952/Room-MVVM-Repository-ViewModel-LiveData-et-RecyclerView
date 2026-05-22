package com.example.lab19.persistence.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "app_tasks")
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "task_header")
    private String header;

    @ColumnInfo(name = "task_body")
    private String body;

    public TaskEntry(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
