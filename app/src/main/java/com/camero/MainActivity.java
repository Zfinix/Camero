package com.camero;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.camero.adapter.MyAdapter;
import com.camero.utils.PathUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener{
    private Uri filePath;
    private MyAdapter adapter;
    public ArrayList<String> mName;
    public ArrayList<String> mPhones;
    public  RecyclerView recyclerView;
    private String SMS = "";
    private final int PICK_FILE_REQUEST = 10;
    int fb = 0;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        setupView();
        requestPerms();

        check();
    }

    private void setupView() {
        mName = new ArrayList<>();
        mPhones = new ArrayList<>();
        fab = findViewById( R.id.fab );
        recyclerView = findViewById( R.id.rv);
        adapter = new MyAdapter(this, mName, mPhones);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById( R.id.fab ).setOnClickListener( view -> {
            if (fb == 0){
                chooseFILE();
            }else{

                LayoutInflater inflater = LayoutInflater.from(this);
                final View yourCustomView = inflater.inflate(R.layout.msg, null);

                final EditText msg = yourCustomView.findViewById(R.id.msg);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("")
                        .setView(yourCustomView)
                        .setPositiveButton("OK", (dialog1, whichButton) -> {
                            sendSMS(msg.getText().toString());
                        } )
                        .setNegativeButton("Cancel", null).create();

                dialog.show();
            }

        } );
    }


    private void requestPerms() {
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
    }
    private void sendSMS(String message) {
        Uri smsToUri = Uri.parse("smsto:" + SMS);
        Intent intent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
        // message = message.replace("%s", StoresMessage.m_storeName);
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void  check() {

        if (adapter.getItemCount() < 1){

            findViewById( R.id.CONTENT).setVisibility( View.GONE);
            findViewById( R.id.EMPTY).setVisibility( View.VISIBLE);
        } else {
            fb = 1;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.send, this.getTheme()));
            } else {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.send));
            }
                findViewById( R.id.CONTENT).setVisibility( View.VISIBLE);
                findViewById( R.id.EMPTY).setVisibility( View.GONE);

        }

    }

    private void chooseFILE() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST);
    }
    private void readFileData() throws IOException {
        if( filePath != null){
            String path= null;
            try {
                path = PathUtil.getPath(this, filePath);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            String[] data;
            File file = new File(path);
            Log.d( "CSVX", "readFileData: ");
            if (file.exists())
            {
                BufferedReader br = null;

                    br = new BufferedReader(new FileReader(file));

                    {
                        String csvLine;



                        while ((csvLine = br.readLine()) != null)
                        {
                            data=csvLine.split(",");
                            try
                            {

                                mPhones.add(data[1]);
                                mName.add(data[0]);
                                SMS = SMS + data[1] + ";";

                                Log.d( "CSVX", "readFileData: " + data[0]+" "+data[1]);
                                //Toast.makeText(getApplicationContext(),data[0]+" "+data[1],Toast.LENGTH_SHORT).show();
                                adapter.notifyDataSetChanged();
                                check();


                                // set up the RecyclerView

                            }
                            catch (Exception e)
                            {
                                Log.e("Problem", e.toString());
                            }
                        }
                    }


            }
            else
            {
                Toast.makeText(getApplicationContext(),"file not exists",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            Log.d( "FPATH", "FILE-PATH-URI: " +filePath);

            try {
                Log.d( "FPATH", "FILE-PATH-STRING: " + PathUtil.getPath( this, filePath));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (filePath.toString().toLowerCase().contains( ".csv" )) {

                try {
                    readFileData();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getApplicationContext(),"File is not cv",Toast.LENGTH_SHORT).show();


            }


        }

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
