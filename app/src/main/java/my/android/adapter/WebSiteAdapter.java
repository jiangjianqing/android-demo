package my.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.ztxs.myapplication2.R;
import my.android.fragment.website.WebSiteContent;

/**
 * Created by cz_jjq on 11/17/15.
 */
public class WebSiteAdapter extends ArrayAdapter<WebSiteContent.WebSiteItem> {

    private int resourceId;

    public WebSiteAdapter(Context context, int resource, List<WebSiteContent.WebSiteItem> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WebSiteContent.WebSiteItem webSiteItem=getItem(position);
        View view;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }else{
            view=convertView;
        }
        TextView textWebSiteName=(TextView)view.findViewById(R.id.website_name);
        textWebSiteName.setText(webSiteItem.name);

        return view;
        //return super.getView(position, convertView, parent);
    }
}
