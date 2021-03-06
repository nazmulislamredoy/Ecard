package com.example.sampanit.ecard;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleContact extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener {
    private ImageView imageViewRound;
    private static String[] items = {"Email","QR Code","Others"};
    private Button button_open_dialog;
    AlertDialog ad;

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

        button_open_dialog = (Button)findViewById(R.id.b_open_dialog_share_box);
        button_open_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });
            AlertDialog.Builder builder = new  AlertDialog.Builder(this);
        builder.setTitle("Share via");
        builder.setItems(items, this);

        builder.setNegativeButton("Cancel", null);
        ad = builder.create();


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
        if (id == R.id.card_search) {
            //return true;
        }
        else if(id == R.id.select_card){
            Intent select_card_intent = new Intent(SingleContact.this, AllCards.class);
            startActivity(select_card_intent);
        }
        else if(id == R.id.create_card){
            Intent create_card_intent = new Intent(SingleContact.this, CardInfoManualUpdate.class);
            startActivity(create_card_intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent nav_profile_intent = new Intent(SingleContact.this, SingleContact.class);
            startActivity(nav_profile_intent);

        } else if (id == R.id.nav_all_cantacts) {
            Intent nav_all_cantacts_intent = new Intent(SingleContact.this, AllContacts.class);
            startActivity(nav_all_cantacts_intent);
        }
        else if (id == R.id.nav_all_cards) {
            Intent nav_all_cards_intent = new Intent(SingleContact.this, AllCards.class);
            startActivity(nav_all_cards_intent);
        }
        else if (id == R.id.nav_settings) {
            Intent nav_setting_intent = new Intent(SingleContact.this, Settings.class);
            startActivity(nav_setting_intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int pos) {
        switch( pos )
        {
            case 0:
                Intent email_intent = new Intent(SingleContact.this, EmailCompose.class);
                startActivityForResult(email_intent, 0);
                break;
            case 1:
                Intent qr_code_intent = new Intent(SingleContact.this, QrCode.class);
                startActivityForResult(qr_code_intent, 0);
                break;
            case 2:
                Intent other_intent = new Intent(SingleContact.this, ShareCardViaOptions.class);
                startActivityForResult(other_intent, 0);
                break;
        }
    }
}

