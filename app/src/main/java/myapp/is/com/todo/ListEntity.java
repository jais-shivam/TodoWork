package myapp.is.com.todo;

//Here We are creating the Entity Part of the Room.

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Reminder_table")
public class ListEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String time;

    private String date;


    public ListEntity(String title, String time, String date) {
        this.title = title;
        this.time = time;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

}
