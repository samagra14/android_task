package com.samagra.task2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1000;
    private Button button;
    private Cursor cursor;
    private ArrayList<String> contacts;
    private static final int BUFFER = 4096;
    private static final String FILENAME = "zipcontacts.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        contacts = new ArrayList<String>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                } else {
                    zipContacts();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    zipContacts();

                } else {

                    Toast.makeText(this, "Please give permissions to zip your contacts", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    private void zipContacts() {
        String name,phonenumber;
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,null);
        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contacts.add(name + " "  + ":" + " " + phonenumber);
        }
        Log.e("Test",contacts.toString());

        cursor.close();
        String[] contactsArray = contacts.toArray(new String[contacts.size()]);

        Observable<String> contactsObservable = Observable.from(contactsArray);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.v("test","All data emitted");
                readCSV();
                String[] filePath = {getApplicationContext().getFilesDir().getPath() + FILENAME};
                String zippedFile = getApplicationContext().getFilesDir().getPath() + "zippedContacts";
                zip(filePath,zippedFile);

            }

            @Override
            public void onError(Throwable e) {
                Log.e("test",e.getStackTrace().toString());

            }

            @Override
            public void onNext(String s) {
                Log.d("test","New Data:" + s);
                writeCSV(FILENAME,s);

            }
        };

        Subscription subscription = contactsObservable
                .subscribeOn(Schedulers.io())       //observable will run on IO thread.
                .observeOn(Schedulers.newThread())      //Observer will run on main thread.
                .subscribe(observer);


    }

    private void writeCSV(String fileName, String content){
        try {
            String filePath = this.getFilesDir().getPath() + FILENAME;
            File file = new File(filePath);
            FileOutputStream outputStream;
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(filePath, true);
            outputStream.write(content.getBytes());
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readCSV(){
        BufferedReader br = null;
        String filePath = this.getFilesDir().getPath() + FILENAME;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                Log.d("test",sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void zip(String[] _files, String zipFileName) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(zipFileName);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte data[] = new byte[BUFFER];

            for (int i = 0; i < _files.length; i++) {
                Log.e("test","Zipping files");
                Log.v("Compress", "Adding: " + _files[i]);
                FileInputStream fi = new FileInputStream(_files[i]);
                origin = new BufferedInputStream(fi, BUFFER);

                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;

                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.root_view), "File Zipped", Snackbar.LENGTH_LONG);

            snackbar.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
