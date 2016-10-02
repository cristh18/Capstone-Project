package com.udacitynanodegree.cristhian.capstoneproject.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ActivityMainBinding;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItem;
import com.udacitynanodegree.cristhian.capstoneproject.interfaces.GenericItemView;
import com.udacitynanodegree.cristhian.capstoneproject.model.Person;
import com.udacitynanodegree.cristhian.capstoneproject.model.Vehicle;
import com.udacitynanodegree.cristhian.capstoneproject.ui.adapters.GenericAdapter;
import com.udacitynanodegree.cristhian.capstoneproject.ui.factories.GenericAdapterFactory;
import com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.account.RegisterVehicleFragment;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.SimpleDividerItemDecoration;
import com.udacitynanodegree.cristhian.capstoneproject.ui.views.items.VehicleItemView;
import com.udacitynanodegree.cristhian.capstoneproject.utils.ViewUtil;

import java.util.List;

public class MainActivity extends BaseFragmentActivity implements
        View.OnClickListener,
        RegisterVehicleFragment.RegisterVehicleListener,
        ChildEventListener {

    private final static String TAG = MainActivity.class.getName();
    private ActivityMainBinding mainBinding;

    private final int SPLASH_REQUEST_CODE = 0;
    private final int INTRO_REQUEST_CODE = 1;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    private Person person;
    private GenericAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_activities, R.anim.slide_out_activities);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        startActivityForResult(new Intent(this, SplashActivity.class), SPLASH_REQUEST_CODE);
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        initRecyclerViewAdapter();
        initViews();
        initListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPLASH_REQUEST_CODE) {
            manageOnResultSplash(resultCode);
        } else if (requestCode == INTRO_REQUEST_CODE) {
            manageOnResultIntro();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void manageOnResultSplash(int resultCode) {
        if (resultCode == RESULT_OK) {
            startActivityForResult(new Intent(this, AccountActivity.class), INTRO_REQUEST_CODE);
        } else {
            finish();
        }
    }

    private void manageOnResultIntro() {
        Log.e(TAG, "ENTER TO MAINACTIVITY");
        init();
        getUserData();

        //        List<String> list = new ArrayList<>();
//        Log.e(TAG + "ITEM", "ITEM: " + list.get(-1));
    }

    private void initRecyclerViewAdapter() {
        adapter = new GenericAdapter(new GenericAdapterFactory() {
            @Override
            public GenericItemView onCreateViewHolder(ViewGroup parent, int viewType) {
                VehicleItemView vehicleItemView = new VehicleItemView(parent.getContext());
                return vehicleItemView;
            }
        });
    }

    private void initViews() {

    }

    private void initListeners() {
        mainBinding.buttonSignOut.setOnClickListener(this);
    }

    private void buildViews() {
        if (person.getVehicles().isEmpty()) {
            ViewUtil.hideGoneViews(mainBinding.containerLayoutVehicles);
            addFragment(new RegisterVehicleFragment());
        } else {
//            mainBinding.recyclerViewMyVehicles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//            mainBinding.recyclerViewMyVehicles.setVerticalScrollBarEnabled(true);
//            mainBinding.recyclerViewMyVehicles.addItemDecoration(new SimpleDividerItemDecoration(this));
//            mainBinding.recyclerViewMyVehicles.setHasFixedSize(true);
//            mainBinding.recyclerViewMyVehicles.setVerticalScrollBarEnabled(true);
//            mainBinding.recyclerViewMyVehicles.setAdapter(adapter);
//            adapter.setItems(getVehicleItems(person.getVehicles()));
            // TODO DELETE
            mainBinding.containerLayoutVehicles.setVisibility(View.GONE);
            addFragment(new RegisterVehicleFragment());
        }
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
        startActivityForResult(new Intent(this, AccountActivity.class), INTRO_REQUEST_CODE);
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
    public void onBackRegisterVehicle() {
        ViewUtil.showViews(mainBinding.containerLayoutVehicles);
    }

    @Override
    public void onFinish() {
        Toast.makeText(this, "USER REGISTERED", Toast.LENGTH_SHORT).show();
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
