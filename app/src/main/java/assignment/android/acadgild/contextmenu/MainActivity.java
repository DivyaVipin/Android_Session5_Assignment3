package assignment.android.acadgild.contextmenu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ContactAdapter phoneadapter;
    ListView listViewContact;
    String[] names = {"Divya", "Dhanya", "Mahesh", "Vipin", "Sahil"};
    String[] phoneNo = {"9456734567", "7345623456", "8734567238", "8345673456", "8234567346"};
    int[] images={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewContact=(ListView) findViewById(R.id.listViewContact);
        phoneadapter=new ContactAdapter(this,names,phoneNo,images);
        listViewContact.setAdapter(phoneadapter);
        registerForContextMenu(listViewContact);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listViewContact) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("Select the Action");
            menu.add(0, v.getId(), 0, "Call");
            menu.add(0, v.getId(), 0, "SMS");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition=info.position;
        String number=phoneadapter.phoneNo[listPosition];
        if (item.getTitle() == "Call") {
            Intent callIntent = new Intent(Intent.ACTION_CALL); //use ACTION_CALL class
            callIntent.setData(Uri.parse("tel:"+number));    //this is the phone number calling
            //check permission
            //If the device is running Android 6.0 (API level 23) and the app's targetSdkVersion is 23 or higher,
            //the system asks the user to grant approval.
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //request permission from user if the app hasn't got the required permission
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},   //request specific permission from user
                        10);
                return true;
            }else {     //have got permission
                try{
                    startActivity(callIntent);  //call activity and make phone call
                }
                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"YourActivity is not founded",Toast.LENGTH_SHORT).show();
                }
            }

        }
        else
        {
            Intent sms=new Intent();
            sms.setAction(android.content.Intent.ACTION_VIEW);
            sms.setData(Uri.parse("smsto:"+number));
            startActivity(sms);
        }

    return true;
    }


}
