package myapp.is.com.todo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


//Here We are creating the DAO part of the ROOM.

@Dao
public interface ListDAO {

    @Insert
    void insert(ListEntity listEntity);

    @Update
    void update(ListEntity listEntity);

    @Delete
    void delete(ListEntity listEntity);

    @Query("DELETE  FROM Reminder_table")
    void deleteAllReminder();

    @Query("SELECT * FROM Reminder_table ")
    LiveData<List<ListEntity>> getAllNotes();  //LiveData<> is used because if any change happens then our List<Note> will update Automatically.
}
