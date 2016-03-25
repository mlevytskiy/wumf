package io.wumf.wumf.memory;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by max on 25.03.16.
 */
public class AppsManager {

    private Realm realm;

    public AppsManager() {
        realm = Realm.getDefaultInstance();
    }

    public List<App> getAll() {
        RealmResults<App> results = realm.where(App.class).findAll();
        return results.subList(0, results.size());
    }

    public void save(App app) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(app);
        realm.commitTransaction();
    }

    public void saveAll(List<App> apps) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(apps);
        realm.commitTransaction();
    }

}