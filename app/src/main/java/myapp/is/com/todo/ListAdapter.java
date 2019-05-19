package myapp.is.com.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {  //error

    private final LayoutInflater mInflater;
    private List<ListEntity> mRemindersEntity; // Cached copy of words

    public ListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.activity_list_item, viewGroup, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        if (mRemindersEntity != null) {
            ListEntity current = mRemindersEntity.get(i);
            listViewHolder.reminder.setText(current.getTitle());  //error
            listViewHolder.time.setText(current.getTime());
            listViewHolder.date.setText(current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            listViewHolder.reminder.setText("No Word");
        }
    }

    void setTitle(List<ListEntity> listEntities) {
        mRemindersEntity = listEntities;
        notifyDataSetChanged();
    }

    public ListEntity getTitleAt(int position) {
        return mRemindersEntity.get(position);
    }


    @Override
    public int getItemCount() {
        if (mRemindersEntity != null)
            return mRemindersEntity.size();
        else return 0;
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private final TextView reminder;
        private final TextView time;
        private final TextView date;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            reminder = itemView.findViewById(R.id.text_view_title);
            time = itemView.findViewById(R.id.text_view_time);
            date = itemView.findViewById(R.id.text_view_date);
        }
    }
}
