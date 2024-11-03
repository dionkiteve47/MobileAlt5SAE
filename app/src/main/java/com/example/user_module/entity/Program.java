package com.example.user_module.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "programs")
public class Program {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String type; // e.g., "Seminar", "Activity", "Hackathon"
    private long date; // Store date as a timestamp for simplicity

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }
}
