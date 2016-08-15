package com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.utils.ViewUtil;

public class HeaderMainView extends Toolbar implements View.OnClickListener {

    private HeaderMainListener headerMainListener;
    private Context mContext;
    private String titleToolbar;
    private Drawable imageLeft;
    private Drawable imageRight;
    private ImageView imageViewHeaderBack;
    private TextView textViewHeaderTitle;
    private int background = -1;
    private Drawable imageBackground;
    private ImageView imageViewHeaderRight;
    private ImageView imageViewBackground;

    private RelativeLayout relativeLayoutContainer;

    private View headerMainView;

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
        headerMainView = inflater.inflate(R.layout.view_header_main, this, true);
        relativeLayoutContainer = (RelativeLayout) findViewById(R.id.relativeLayout_main_container);
        imageViewHeaderBack = (ImageView) findViewById(R.id.imageView_header_back);
        imageViewHeaderRight = (ImageView) findViewById(R.id.imageView_header_right);
        textViewHeaderTitle = (TextView) findViewById(R.id.textView_header_title);
        imageViewBackground = (ImageView) findViewById(R.id.imageView_background);

    }

    private void buildViews() {
        setTitleHeaderText();
        setLeftImageDrawable();
        setRightImageDrawable();
        setBackground();
    }

    private void setBackground() {
        if (imageBackground != null) {
//            ViewUtil.setBackgroundView(headerMainBinding.relativeLayoutMainContainer, null);
            ViewUtil.setBackgroundView(relativeLayoutContainer, null);
            imageViewBackground.setImageDrawable(imageBackground);
            imageViewBackground.setVisibility(View.VISIBLE);
        } else {
            imageViewBackground.setImageDrawable(null);
            imageViewBackground.setVisibility(View.GONE);
            setBackgroundColor();
        }
    }

    private void setBackgroundColor() {
        if (background != -1) {
            relativeLayoutContainer.setBackgroundColor(background);
        } else {
            relativeLayoutContainer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));
        }
    }

    private void setRightImageDrawable() {
        if (imageRight != null) {
            imageViewHeaderRight.setImageDrawable(imageRight);
        }
    }

    private void setTitleHeaderText() {
        if (titleToolbar != null) {
            textViewHeaderTitle.setText(titleToolbar);
        }
    }

    private void setLeftImageDrawable() {
        if (imageLeft != null) {
            imageViewHeaderBack.setImageDrawable(imageLeft);
        }
    }

    private void initListeners() {
        imageViewHeaderBack.setOnClickListener(this);
        imageViewHeaderRight.setOnClickListener(this);
    }

    public void setTextTitle(String titleToolbar) {
        textViewHeaderTitle.setText(titleToolbar);
    }

    public void setImageLeft(Drawable imageLeft) {
        this.imageLeft = imageLeft;
        setLeftImageDrawable();
    }

    public void hideImageViewHeaderBack() {
        imageViewHeaderBack.setVisibility(GONE);
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
        imageViewHeaderRight.setVisibility(visibility);
    }

    public void setBackgroundColor(int resource) {
        background = resource;
        setBackgroundColor();
    }

    public void setBackgroundImg(Drawable resource) {
        imageBackground = resource;
        setBackground();
    }

    public interface HeaderMainListener {

        void onClickBackHeader();

        void onClickRightHeader();
    }

    public TextView getTextViewHeaderTitle() {
        return textViewHeaderTitle;
    }
}
