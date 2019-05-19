package myapp.is.com.todo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;

public class ListRepository {

    private ListDAO mlistDAO;
    private LiveData<List<ListEntity>> allNotes;

     ListRepository(Application application){
        ListDatabase db=ListDatabase.getDatabase(application);
        mlistDAO=db.ListDAO();
        allNotes=mlistDAO.getAllNotes();
    }

    public void insertReminder(ListEntity listEntity) {
        new InsertListAyncTask(mlistDAO).execute(listEntity);

    }

    public void update(ListEntity listEntity) {
        new UpdateListAyncTask(mlistDAO).execute(listEntity);

    }

    public void deleteReminder(ListEntity listEntity) {
        new DeleteListAyncTask(mlistDAO).execute(listEntity);

    }

    public void deleteAllReminder() {
        new DeleteAllListAyncTask(mlistDAO).execute();

    }

    public LiveData<List<ListEntity>> getAllReminder(){
        return allNotes;
    }

    private static class InsertListAyncTask extends AsyncTask<ListEntity,Void,Void> {

        private ListDAO listDAO;
        private InsertListAyncTask(ListDAO mlistDAO) {
            this.listDAO=mlistDAO;
        }

        @Override
        protected Void doInBackground(ListEntity... listEntities) {
            listDAO.insert(listEntities[0]);
            return null;
        }
    }

    private static class UpdateListAyncTask extends AsyncTask<ListEntity,Void,Void> {

        private ListDAO listDAO;
        private UpdateListAyncTask(ListDAO mlistDAO) {
            this.listDAO=mlistDAO;
        }

        @Override
        protected Void doInBackground(ListEntity... listEntities) {
            listDAO.update(listEntities[0]);
            return null;
        }
    }

    private static class DeleteListAyncTask extends AsyncTask<ListEntity,Void,Void> {

        private ListDAO listDAO;
        private DeleteListAyncTask(ListDAO mlistDAO) {
            this.listDAO=mlistDAO;
        }

        @Override
        protected Void doInBackground(ListEntity... listEntities) {
            listDAO.delete(listEntities[0]);
            return null;
        }
    }

    private static class DeleteAllListAyncTask extends AsyncTask<ListEntity,Void,Void> {

        private ListDAO listDAO;
        private DeleteAllListAyncTask(ListDAO mlistDAO) {
            this.listDAO=mlistDAO;
        }

        @Override
        protected Void doInBackground(ListEntity... listEntities) {
            listDAO.deleteAllReminder();
            return null;
        }
    }
}
