package myapp.is.com.todo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ListViewModel extends AndroidViewModel {
    private ListRepository mRepository;
    private LiveData<List<ListEntity>> mAllReminder;

    public ListViewModel(Application application) {
        super(application);
        mRepository = new ListRepository(application);
        mAllReminder = mRepository.getAllReminder();
    }

    public void insert(ListEntity listEntity) {
        mRepository.insertReminder(listEntity);
    }

    public void update(ListEntity listEntity) {
        mRepository.update(listEntity);
    }

    public void delete(ListEntity listEntity) {
        mRepository.deleteReminder(listEntity);
    }

    public void deleteAllReminder() {
        mRepository.deleteAllReminder();
    }

    public LiveData<List<ListEntity>> getAllNotes() {
        return mAllReminder;
    }
}
