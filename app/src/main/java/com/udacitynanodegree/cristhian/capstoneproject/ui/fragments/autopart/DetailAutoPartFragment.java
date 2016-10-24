package com.udacitynanodegree.cristhian.capstoneproject.ui.fragments.autopart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FragmentAutoPartDetailBinding;
import com.udacitynanodegree.cristhian.capstoneproject.model.AutoPart;

public class DetailAutoPartFragment extends DialogFragment {

    private FragmentAutoPartDetailBinding autoPartDetailBinding;
    ImageView imageViewApp;
    TextView textViewTitle;
    TextView textViewSummary;
    private AutoPart autoPart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        autoPartDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_auto_part_detail, container, false);
        init();
        buildView();
        return autoPartDetailBinding.getRoot();
    }

    private void init(){
        initListeners();
    }

    private void initListeners(){

    }

    private void buildView(){
        getDialog().setTitle(autoPart.getName());
    }

    public AutoPart getAutoPart() {
        return autoPart;
    }

    public void setAutoPart(AutoPart autoPart) {
        this.autoPart = autoPart;
    }
}
