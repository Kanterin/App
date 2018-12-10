package com.designer.app.tm.aquariumstart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddAnimal extends AppCompatActivity {

    EditText name_animal, biology_name, quantity, total_price;
    ImageButton btn_camera, btn_galery;
    Button btn_add_animal;
    ImageView img_placeholder;

    final int REQUEST_CODE_GALLERY = 999;
    final int REQUEST_CODE_CAMERA = 888;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        init();


        btn_galery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddAnimal.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddAnimal.this, new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent, REQUEST_CODE_CAMERA);

            }
        });


        btn_add_animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Fishes.sqLiteHelperAnimal.insertData(
                            name_animal.getText().toString().trim(),
                            biology_name.getText().toString().trim(),
                            quantity.getText().toString().trim(),
                            total_price.getText().toString().trim(),
                            imageViewToByte(img_placeholder)
                    );
                    Intent intent = new Intent(AddAnimal.this, Fishes.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();

                    /*name_animal.setText("");
                    biology_name.setText("");
                    quantity.setText("");
                    total_price.setText("");
                    img_placeholder.setImageResource(R.drawable.placeholder);*/

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        else if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_placeholder.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode == REQUEST_CODE_CAMERA){

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                img_placeholder.setImageBitmap(bitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void init(){
        name_animal = (EditText) findViewById(R.id.tx_name_animal);
        biology_name = (EditText) findViewById(R.id.tx_biology_name_animal);
        quantity = (EditText) findViewById(R.id.tx_quantity_animal);
        total_price = (EditText) findViewById(R.id.tx_total_price_animal);
        btn_camera = (ImageButton) findViewById(R.id.btn_img_camera);
        btn_galery = (ImageButton) findViewById(R.id.btn_img_galery);
        btn_add_animal = (Button) findViewById(R.id.btn_add_animal);
        img_placeholder = (ImageView) findViewById(R.id.img_placeholder);
    }
}
