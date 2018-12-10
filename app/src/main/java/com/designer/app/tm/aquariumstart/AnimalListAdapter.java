package com.designer.app.tm.aquariumstart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by GTX on 2017-12-20.
 */

public class AnimalListAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Animals> animalList;

    public AnimalListAdapter(Context context, int layout, ArrayList<Animals> animalList) {
        this.context = context;
        this.layout = layout;
        this.animalList = animalList;
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int position) {
        return animalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView Name, Biology_name, Quantity, Total_price;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.Name = (TextView) row.findViewById(R.id.item_NameAnimal);
            holder.Biology_name = (TextView) row.findViewById(R.id.item_BiologyName);
            holder.Quantity = (TextView) row.findViewById(R.id.item_Quantity);
            holder.Total_price = (TextView) row.findViewById(R.id.item_TotalPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.item_imgAnimal);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Animals animals = animalList.get(position);

        holder.Name.setText(animals.getName());
        holder.Biology_name.setText(animals.getBiology_name());
        holder.Quantity.setText(animals.getQuantity());
        holder.Total_price.setText(animals.getTotal_price());

        byte[] imgAnimal = animals.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgAnimal, 0, imgAnimal.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
