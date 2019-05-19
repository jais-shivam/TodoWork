package myapp.is.com.roomwordsample;

import android.app.Application;

import java.util.List;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application); //reference to the repository
        mAllWords = mRepository.getAllWords();    // gets the list of words from the repository.
    }

    //Add a "getter" method for all the words.
    // This completely hides the implementation from the UI.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    //Create a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is completely hidden from the UI.
    public void insert(Word word) {
        mRepository.insert(word);
    }
}
