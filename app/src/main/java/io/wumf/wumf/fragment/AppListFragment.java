package io.wumf.wumf.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.wumf.wumf.R;
import io.wumf.wumf.adapter.AppListAdapter;

/**
 * Created by max on 27.04.16.
 */
public class AppListFragment extends Fragment {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_list, container, false);
        final RealmRecyclerView recyclerView = (RealmRecyclerView) view.findViewById(R.id.realm_recycler_view);
        AppListAdapter adapter = new AppListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

}
