package com.example.sampanit.ecard;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleContact extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView listView = null;
    private ImageView imageViewRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.single_contact_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.single_card_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imageViewRound =(ImageView)findViewById(R.id.contact_imageView_round_1);
        listView = new ListView(this);

        final String[] items = {"Email","QR Code","Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_share_card, R.id.txtitem, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup)view;
                TextView txt = (TextView)vg.findViewById(R.id.txtitem);
                txt.setTextSize(18);
                txt.setTextColor(Color.BLACK);
                switch( position )
                {
                    case 0:  Intent newActivity1 = new Intent(SingleContact.this, EmailCompose.class);
                        startActivity(newActivity1);
                        break;
                    case 1:  Intent newActivity2 = new Intent(SingleContact.this, QrCode.class);
                        startActivity(newActivity2);
                        break;
                    case 2:  Intent newActivity3 = new Intent(SingleContact.this, ShareCardViaOptions.class);
                        startActivity(newActivity3);
                        break;

                }

            }
        });
    }

    public void ShowDialogListViewToShareCard(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SingleContact.this);
        builder.setTitle("Share via");
        builder.setCancelable(true);
        builder.setNegativeButton("Cancel", null);
        builder.setView(listView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.single_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_all_cantacts) {
            Intent nav_all_cantacts_intent = new Intent(SingleContact.this, AllContacts.class);
            startActivity(nav_all_cantacts_intent);
        }
        else if (id == R.id.nav_all_cards) {

        }
        else if (id == R.id.nav_settings) {
            Intent nav_setting_intent = new Intent(SingleContact.this, Settings.class);
            startActivity(nav_setting_intent);
        }
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
