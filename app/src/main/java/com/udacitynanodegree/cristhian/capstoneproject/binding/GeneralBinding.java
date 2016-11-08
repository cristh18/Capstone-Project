package com.udacitynanodegree.cristhian.capstoneproject.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.utils.FontCache;

public class GeneralBinding {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontCache.get(fontName));
    }

    @BindingAdapter({"bind:font"})
    public static void setFont(TextInputLayout inputLayout, String fontName) {
        inputLayout.setTypeface(FontCache.get(fontName));
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder_detail)
                .noFade()
                .error(R.drawable.ic_placeholder_detail)
                .into(view);
    }
}