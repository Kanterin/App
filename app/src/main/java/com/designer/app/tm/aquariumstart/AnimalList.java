package com.designer.app.tm.aquariumstart;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by GTX on 2017-12-20.
 */

public class AnimalList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Animals> list;
    AnimalListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_list);



        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new AnimalListAdapter(this, R.layout.animal_items, list);
        gridView.setAdapter(adapter);



        // get all data from sqlite
        Cursor  cursor=  Fishes.sqLiteHelperAnimal.getData("SELECT * FROM Animal");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String biology_name = cursor.getString(2);
            String quantity = cursor.getString(3);
            String total_price = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Animals(name, biology_name, quantity,total_price, image, id));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Info", "Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(AnimalList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = Fishes.sqLiteHelperAnimal.getData("SELECT id FROM Animal");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                             //show dialog update at here
                            showDialogInfo(AnimalList.this, arrID.get(position));

                        }
                        else if(item == 1){
                            Cursor c = Fishes.sqLiteHelperAnimal.getData("SELECT id FROM Animal");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(AnimalList.this, arrID.get(position));

                        }
                        else {
                            // delete
                            Cursor c = Fishes.sqLiteHelperAnimal.getData("SELECT id FROM Animal");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }


    ImageView imageViewAnimal;

    //
    private void showDialogInfo(Activity activity, final int position){


        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.more_info_animal);
        dialog.setTitle("Info");

        Button btnBack = (Button) dialog.findViewById(R.id.btnBackAnimal);
        /*
        imageViewAnimal = (ImageView) dialog.findViewById(R.id.imageViewAnimal);
        final TextView edtName = (TextView) dialog.findViewById(R.id.textViewAnimalName);
        final TextView edtBiologyName = (TextView) dialog.findViewById(R.id.textViewAnimalInfo);
        final TextView edtQuantity = (TextView) dialog.findViewById(R.id.textViewAnimalFamily);
        final TextView edtTotalPrice = (TextView) dialog.findViewById(R.id.textViewAnimalDerivation);
        final TextView edtBiologyName = (TextView) dialog.findViewById(R.id.textViewAnimalInfo);
        final TextView edtQuantity = (TextView) dialog.findViewById(R.id.textViewAnimalFamily);
        final TextView edtTotalPrice = (TextView) dialog.findViewById(R.id.textViewAnimalDerivation);
        final TextView edtTotalPrice = (TextView) dialog.findViewById(R.id.textViewAnimalDerivation);
        final TextView edtBiologyName = (TextView) dialog.findViewById(R.id.textViewAnimalInfo);
        final TextView edtQuantity = (TextView) dialog.findViewById(R.id.textViewAnimalFamily);
        final TextView edtTotalPrice = (TextView) dialog.findViewById(R.id.textViewAnimalDerivation);
        */

        // get all data from sqlite
        Cursor  cursor=  Fishes.sqLiteHelperAnimal.getData("SELECT * FROM Animal");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String biology_name = cursor.getString(2);
            String quantity = cursor.getString(3);
            String total_price = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Animals(name, biology_name, quantity,total_price, image, id));
        }
        adapter.notifyDataSetChanged();



        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                }
                catch (Exception error) {
                    Log.e("Back button error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    //
    //picture to add
    ImageView imageViewAnimalUpdate;

    private void showDialogUpdate(Activity activity, final int position){


        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_animal);
        dialog.setTitle("Update");

        imageViewAnimalUpdate = (ImageView) dialog.findViewById(R.id.imageViewAnimal);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edt1Name);
        final EditText edtBiologyName = (EditText) dialog.findViewById(R.id.edt1BiologyName);
        final EditText edtQuantity = (EditText) dialog.findViewById(R.id.edt1Quantity);
        final EditText edtTotalPrice = (EditText) dialog.findViewById(R.id.edt1TotalPrice);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdateAnimal);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        AnimalList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        999
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fishes.sqLiteHelperAnimal.updateData(
                            edtName.getText().toString().trim(),
                            edtBiologyName.getText().toString().trim(),
                            edtQuantity.getText().toString().trim(),
                            edtTotalPrice.getText().toString().trim(),
                            AddAnimal.imageViewToByte(imageViewAnimal),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    private void showDialogDelete(final int idAnimal){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(AnimalList.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Fishes.sqLiteHelperAnimal.deleteData(idAnimal);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = Fishes.sqLiteHelperAnimal.getData("SELECT * FROM Animal");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String biology_name = cursor.getString(2);
            String quantity = cursor.getString(3);
            String total_price = cursor.getString(4);
            byte[] image = cursor.getBlob(5);

            list.add(new Animals(name, biology_name,quantity, total_price, image, id));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 999){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 999);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 999 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewAnimalUpdate.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
