package io.wumf.wumf.firebase.uploadDataToPlacesNode;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.wumf.wumf.application.WumfApp;

/**
 * Created by max on 21.07.16.
 */
public class FirebasePlaceUtils {

    private static final DatabaseReference PLACE_REF = FirebaseDatabase.getInstance().getReference().child("places");
    private static final DatabaseReference ANDROID_IDS_REF = FirebaseDatabase.getInstance().getReference().child("androidIds");

    public static String generatePlaceId(String country, String city) {
        return String.valueOf(country.hashCode()) + String.valueOf(city.hashCode());
    }

    public static void uploadMyInfo(String country, String city) {
        String placeId = generatePlaceId(country, city);
        PLACE_REF.child(placeId).child(WumfApp.instance.androidId).setValue("");
    }

    public static void loadUsersCountByPlace(String placeId, final UsersCountListener listener) {
        PLACE_REF.child(placeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = 0;
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    size++;
                }
                listener.getResult(size);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static int idsCount = 0;

//    public static void loadAppsByPlace(String placeId) {
//        final DatabaseReference placeRef = PLACE_REF.child(placeId);
//        placeRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                final Map<String, List<App>> idApps = new HashMap<String, List<App>>();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    final String androidId = postSnapshot.getKey();
//                    if (TextUtils.equals(androidId, WumfApp.instance.androidId)) {
//                        continue; //ignore my id
//                    }
//                    idsCount++;
//                    final DatabaseReference androidIdRef = ANDROID_IDS_REF.child(androidId);
//                    ANDROID_IDS_REF.child(androidId).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            List<App> apps = new ArrayList<App>();
//                            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                                String appKey = postSnapshot.getKey();
//                                io.wumf.wumf.firebase.uploadDataToPhonesNode.pojo. FirebaseApp firebaseApp = postSnapshot.getValue(io.wumf.wumf.firebase.uploadDataToPhonesNode.pojo.FirebaseApp.class);
//                                App app = new App(appKey.replace(" ", "."), firebaseApp.getName(), firebaseApp.getIcon());
//                                apps.add(app);
//                            }
//                            idApps.put(androidId, apps);
//                            androidIdRef.removeEventListener(this);
//                            if (idApps.size() == idsCount) {
//                                BusProvider.getInstance().post(new FirebaseLoadAppsFinishedEvent(idApps));
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            //do nothing
//                        }
//                    });
//                    placeRef.removeEventListener(this);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    public static void loadAppsByPlace(String country, String city) {
//        String placeId = generatePlaceId(country, city);
//        loadAppsByPlace(placeId);
//    }

}
