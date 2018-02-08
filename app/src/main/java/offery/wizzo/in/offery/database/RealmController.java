package offery.wizzo.in.offery.database;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;
import offery.wizzo.in.offery.model.NewsFeeds;


public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh realm database
    public void refresh() {

        realm.refresh();
    }


    /**
     * Will remove all new from {@link NewsFeeds} class
     * */
    public void clearAll(String category) {

        realm.beginTransaction();
        (realm.where(NewsFeeds.class).contains("news_type", category).findAll()).clear();

        realm.commitTransaction();
    }

    //returns list of all news stored in database
    public RealmResults<NewsFeeds> getNews(String newsType) {

        return realm.where(NewsFeeds.class).contains("news_type", newsType).findAll();
    }



    //returns list of all news stored in database
    public RealmResults<NewsFeeds> getNews() {

        return realm.where(NewsFeeds.class).findAll();
    }
    //query a single item with the given id
    public NewsFeeds getSingleNews(String newsId) {

        return realm.where(NewsFeeds.class).equalTo("new_id", newsId).findFirst();
    }

    /**
     * Will update local path of any object if it exists in DB
     * */
    public boolean updateLocalPath(String newsId, String localPath) {
        // This query is fast because "new_id" is an indexed field
        NewsFeeds kanjoComp = realm.where(NewsFeeds.class)
                .equalTo("new_id", newsId)
                .findFirst();
        if (kanjoComp != null) {
            realm.beginTransaction();
            kanjoComp.setLocalPath(localPath);
            realm.commitTransaction();
            return true;

        } else {
            return false;
        }

    }

    //check if there is any news or not in DB
    public boolean hasNews() {

        return !realm.allObjects(NewsFeeds.class).isEmpty();
    }

    //query example
    public RealmResults<NewsFeeds> queriedNews(String searchTitle) {

        return realm.where(NewsFeeds.class)
                .contains("news", searchTitle)
                .findAll();

    }
}