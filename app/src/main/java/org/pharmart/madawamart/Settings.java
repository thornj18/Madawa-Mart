package org.pharmart.madawamart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.pharmart.madawamart.authentication.Login;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings extends AppCompatActivity {

    private ListView settingsList;
    private final int SELECT_PHOTO = 1;
    CircleImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        profile = (CircleImageView) findViewById(R.id.profileImage);
        Context context = getApplicationContext();


        settingsList = (ListView) findViewById(R.id.settingsList);
        String[] list = {"Personal Information","Help","Legal Agreements","Logout","About"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Settings.this,android.R.layout.simple_list_item_1,list);
        settingsList.setAdapter(arrayAdapter);

        SharedPreferences image = context.getSharedPreferences("IMAGE_URI",Context.MODE_PRIVATE);
        String uri = image.getString("uri",null);

        if (uri != null) {
            profile.setImageBitmap(decodeToBase64(uri));
        } else {
            Toast.makeText(getApplicationContext(), "Image not set", Toast.LENGTH_SHORT).show();
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Settings.this, Login.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                        break;
                    case 4:
                        break;

                    default:
                        return;

                }
            }
        });

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myChildToolbar);
        // Get a support ActionBar corresponding to this toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        Context context = getApplicationContext();
                        SharedPreferences.Editor editor;
                        SharedPreferences sharedPreferences = context.getSharedPreferences("IMAGE_URI",Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("uri",encodeToBase64(selectedImage));
                        editor.apply();

                        profile.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}
