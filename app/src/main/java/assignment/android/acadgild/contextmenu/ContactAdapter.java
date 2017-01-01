package assignment.android.acadgild.contextmenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DivyaVipin on 12/20/2016.
 */
public class ContactAdapter extends ArrayAdapter<String>{
    String[] names={};
    String[] phoneNo={};
    int[] images;

    Context c;
    LayoutInflater inflator;
   // String[] phone={};
    public ContactAdapter(Context context,String[] names,String[] phoneno,int[] image) {
        super(context,R.layout.list_item,names);
        this.names=names;
        this.phoneNo=phoneno;
        this.images=image;
        this.c=context;
        this.images=image;
    }
    public class ViewHolder
    {
        TextView contactName;
        TextView contactPhone;
        ImageView contactImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            inflator=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflator.inflate(R.layout.list_item,null);
            if (position == 0) {
                convertView.setBackgroundColor(Color.BLUE);
            }
            else if (position % 2 == 1) {
                convertView.setBackgroundColor(Color.LTGRAY);
            }
            else if (position % 2 == 0) {
                convertView.setBackgroundColor(Color.BLUE);
            }

        }
        final ViewHolder holder=new ViewHolder();
        holder.contactName=(TextView)convertView.findViewById(R.id.textViewName);
        holder.contactPhone=(TextView)convertView.findViewById(R.id.textViewPhone);
        holder.contactImage=(ImageView)convertView.findViewById(R.id.imgContact);
        holder.contactName.setText(names[position]);
        holder.contactPhone.setText(phoneNo[position]);
        holder.contactImage.setImageResource(images[position]);
        return convertView;

    }



}
