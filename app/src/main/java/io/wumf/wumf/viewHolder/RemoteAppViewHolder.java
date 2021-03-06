package io.wumf.wumf.viewHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.wumf.wumf.R;
import io.wumf.wumf.otto.BusProvider;
import io.wumf.wumf.otto.event.OnAppItemClickEvent;
import io.wumf.wumf.realmObject.RemoteApp;
import io.wumf.wumf.view.UsersCountTextView;

/**
 * Created by max on 22.07.16.
 */
public class RemoteAppViewHolder extends AnyRealmViewHolder<RemoteApp> {

    private ImageView icon;
    private TextView label;
    private UsersCountTextView count;

    public RemoteAppViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_app);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        label = (TextView) itemView.findViewById(R.id.label);
        count = (UsersCountTextView) itemView.findViewById(R.id.count);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String packageName = (String) v.getTag();
                BusProvider.getInstance().post(new OnAppItemClickEvent(packageName));
            }
        });
    }

    @Override
    public void bind(RemoteApp item) {
        Glide.with(itemView.getContext()).load(item.getIcon())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(icon);
        label.setText(item.getName());
        count.setCount(item.getUsersCount());
        itemView.setTag(item.getPackageName());
    }

}
