package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.managers.persistence.models.Person;
import com.udacitynanodegree.cristhian.capstoneproject.managers.persistence.models.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.GenericAdapter;
import com.udacitynanodegree.cristhian.capstoneproject.ui.factories.GenericAdapterFactory;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.SimpleDividerItemDecoration;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.items.VehicleItemView;

import java.util.List;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener,
        ChildEventListener {

    private final static String TAG = MainActivity.class.getName();
    private FirebaseAuth firebaseAuth;
    private Button buttonSignOut;
    private DatabaseReference mDatabase;
    private Person person;
    private GenericAdapter<GenericItem> adapter;
    RecyclerView recyclerViewVehicles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        init();
        getUserData();

//        List<String> list = new ArrayList<>();
//        Log.e(TAG + "ITEM", "ITEM: " + list.get(-1));
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        initRecyclerViewAdapter();
        initViews();
        initListeners();
    }

    private void initRecyclerViewAdapter() {
        adapter = new GenericAdapter<>(new GenericAdapterFactory() {
            @Override
            public GenericItemView onCreateViewHolder(ViewGroup parent, int viewType) {
                VehicleItemView vehicleItemView = new VehicleItemView(parent.getContext());
                return vehicleItemView;
            }
        });
    }

    private void initViews() {
        buttonSignOut = (Button) findViewById(R.id.button_sign_out);
        recyclerViewVehicles = (RecyclerView) findViewById(R.id.recyclerView_my_vehicles);
    }

    private void initListeners() {
        buttonSignOut.setOnClickListener(this);
    }

    private void buildViews() {
        recyclerViewVehicles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewVehicles.setVerticalScrollBarEnabled(true);
        recyclerViewVehicles.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerViewVehicles.setHasFixedSize(true);
        recyclerViewVehicles.setVerticalScrollBarEnabled(true);
        recyclerViewVehicles.setAdapter(adapter);
        adapter.setItems((List<GenericItem>) getVehicleItems(person.getVehicles()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_out:
                firebaseAuth.signOut();
                goToAccountActivity();
                break;
        }
    }

    private void goToAccountActivity() {
        Intent mainIntent = new Intent(this, AccountActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void getUserData() {
        person = new Person();
        mDatabase.child("").addChildEventListener(this);
    }

    private List<? extends GenericItem> getVehicleItems(List<Vehicle> items) {
        List<? extends GenericItem> vehicleItems = items;
        return vehicleItems;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        person = dataSnapshot.getValue(Person.class);
        buildViews();
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
