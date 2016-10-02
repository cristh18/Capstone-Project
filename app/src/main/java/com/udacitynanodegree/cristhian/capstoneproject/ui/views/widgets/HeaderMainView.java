package com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.ViewHeaderMainBinding;
import com.udacitynanodegree.cristhian.capstoneproject.utils.ViewUtil;

public class HeaderMainView extends Toolbar implements View.OnClickListener {

    private HeaderMainListener headerMainListener;
    private Context mContext;
    private String titleToolbar;
    private Drawable imageLeft;
    private Drawable imageRight;
    private int background = -1;
    private Drawable imageBackground;
    private ViewHeaderMainBinding headerMainBinding;

    public HeaderMainView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public HeaderMainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttrs(attrs);
        init();
    }

    public HeaderMainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        init();
    }

    private void init() {
        initViews();
        buildViews();
        initListeners();
    }

    private void initAttrs(AttributeSet attrs) {

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.toolbar);

        if (typedArray != null) {
            final int N = typedArray.getIndexCount();
            for (int i = 0; i < N; ++i) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.toolbar_title_toolbar:
                        titleToolbar = typedArray.getString(attr);
                        break;
                    case R.styleable.toolbar_icon_left_toolbar:
                        imageLeft = typedArray.getDrawable(attr);
                        break;
                    case R.styleable.toolbar_icon_right_toolbar:
                        imageRight = typedArray.getDrawable(attr);
                        break;
                    case R.styleable.toolbar_background_toolbar:
                        background = typedArray.getColor(attr, -1);
                        break;
                    case R.styleable.toolbar_background_img_toolbar:
                        imageBackground = typedArray.getDrawable(attr);
                        break;
                }
            }
            typedArray.recycle();
        }

    }

    private void initViews() {
        setContentInsetsAbsolute(0, 0);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        headerMainBinding = DataBindingUtil.inflate(inflater, R.layout.view_header_main, this, true);
    }

    private void buildViews() {
        setTitleHeaderText();
        setLeftImageDrawable();
        setRightImageDrawable();
        setBackground();
    }

    private void setBackground() {
        if (imageBackground != null) {
            ViewUtil.setBackgroundView(headerMainBinding.relativeLayoutMainContainer, null);
            headerMainBinding.imageViewBackground.setImageDrawable(imageBackground);
            headerMainBinding.imageViewBackground.setVisibility(View.VISIBLE);
        } else {
            headerMainBinding.imageViewBackground.setImageDrawable(null);
            headerMainBinding.imageViewBackground.setVisibility(View.GONE);
            setBackgroundColor();
        }
    }

    private void setBackgroundColor() {
        if (background != -1) {
            headerMainBinding.relativeLayoutMainContainer.setBackgroundColor(background);
        } else {
            headerMainBinding.relativeLayoutMainContainer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red_primary));
        }
    }

    private void setRightImageDrawable() {
        if (imageRight != null) {
            headerMainBinding.imageViewHeaderRight.setImageDrawable(imageRight);
        }
    }

    private void setTitleHeaderText() {
        if (titleToolbar != null) {
            headerMainBinding.textViewHeaderTitle.setText(titleToolbar);
        }
    }

    private void setLeftImageDrawable() {
        if (imageLeft != null) {
            headerMainBinding.imageViewHeaderBack.setImageDrawable(imageLeft);
        }
    }

    private void initListeners() {
        headerMainBinding.imageViewHeaderBack.setOnClickListener(this);
        headerMainBinding.imageViewHeaderRight.setOnClickListener(this);
    }

    public void setTextTitle(String titleToolbar) {
        headerMainBinding.textViewHeaderTitle.setText(titleToolbar);
    }

    public void setImageLeft(Drawable imageLeft) {
        this.imageLeft = imageLeft;
        setLeftImageDrawable();
    }

    public void hideImageViewHeaderBack() {
        headerMainBinding.imageViewHeaderBack.setVisibility(GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageView_header_back) {
            if (headerMainListener != null) {
                headerMainListener.onClickBackHeader();
            }
        } else {
            if (headerMainListener != null) {
                headerMainListener.onClickRightHeader();
            }
        }
    }

    public void setHeaderMainListener(HeaderMainListener headerMainListener) {
        this.headerMainListener = headerMainListener;
    }

    public void setVisibilityRightButton(int visibility) {
        headerMainBinding.imageViewHeaderRight.setVisibility(visibility);
    }

    public void setBackgroundColor(int resource) {
        background = resource;
        setBackgroundColor();
    }

    public void setBackgroundImg(Drawable resource) {
        imageBackground = resource;
        setBackground();
    }

    public TextView getTextViewHeaderTitle() {
        return headerMainBinding.textViewHeaderTitle;
    }

    public interface HeaderMainListener {

        void onClickBackHeader();

        void onClickRightHeader();
    }
}
