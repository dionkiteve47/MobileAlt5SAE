package com.example.user_module.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.user_module.entity.Program;

import java.util.List;

@Dao
public interface ProgramDao {

    @Insert
    void insert(Program program);

    @Update
    void update(Program program);

    @Delete
    void delete(Program program);

    @Query("SELECT * FROM programs ORDER BY date DESC")
    LiveData<List<Program>> getAllPrograms();

    @Query("SELECT * FROM programs WHERE id = :programId")
    LiveData<Program> getProgramById(int programId);
}
