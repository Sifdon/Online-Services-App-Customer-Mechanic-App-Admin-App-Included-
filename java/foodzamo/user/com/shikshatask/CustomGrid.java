package foodzamo.user.com.shikshatask;

/**
 * Created by Satish on 3/16/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomGrid extends BaseAdapter{
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;

    public CustomGrid(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = null;

        final LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = vi.inflate(R.layout.grid_single, null);


        } else {
            holder = (Holder)convertView.getTag();
        }

        holder = new Holder();
        holder.imageView =(ImageView) convertView.findViewById(R.id.grid_image);
        holder.textView=(TextView)  convertView.findViewById(R.id.grid_text);
        convertView.setTag(holder);

        holder.textView.setText(web[position]);
        holder.imageView.setImageResource(Imageid[position]);


        return convertView;

    }

    public static class Holder{

        public TextView textView;
        public ImageView imageView;
    }
}

