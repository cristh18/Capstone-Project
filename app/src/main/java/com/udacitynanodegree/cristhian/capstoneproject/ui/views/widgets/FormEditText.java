package com.udacitynanodegree.cristhian.capstoneproject.ui.views.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.udacitynanodegree.cristhian.capstoneproject.R;
import com.udacitynanodegree.cristhian.capstoneproject.databinding.FormEditTextBinding;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class FormEditText extends FrameLayout {

    private final String PASSWORD_TYPE = "textPassword";
    private final String NUMBER_TYPE = "number";
    private final String EMAIL_TYPE = "email";
    private final int DEFAULT_MAX_LENGTH = 999;
    private FormEditTextBinding binding;
    private FormEditTextInterface listener;
    private Context context;
    private String hint;
    private int textLimit;
    private Drawable imageStart;
    private Drawable imageEnd;

    private boolean passwordVisible = true;
    private String inputType;

    public FormEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public FormEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(attrs);
        init();
    }

    public FormEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(attrs);
        init();
    }

    public void setListener(FormEditTextInterface listener) {
        this.listener = listener;
    }

    private void initAttrs(AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.formEditText);

        if (typedArray != null) {
            final int arraySize = typedArray.getIndexCount();
            for (int i = 0; i < arraySize; ++i) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.formEditText_hint:
                        hint = typedArray.getString(attr);
                        break;
                    case R.styleable.formEditText_textLimit:
                        textLimit = typedArray.getInt(attr, DEFAULT_MAX_LENGTH);
                        break;
                    case R.styleable.formEditText_drawableStart:
                        imageStart = typedArray.getDrawable(attr);
                        break;
                    case R.styleable.formEditText_drawableEnd:
                        imageEnd = typedArray.getDrawable(attr);
                        break;
                    case R.styleable.formEditText_inputType:
                        inputType = typedArray.getString(attr);
                        break;
                }
            }
            typedArray.recycle();
        }
    }

    private void init() {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        binding = DataBindingUtil.inflate(inflater, R.layout.form_edit_text, this, true);

        initValidators();

        initHints();

        initMaxLength();

        setStartImageDrawable();
        setEndImageDrawable();
        setPasswordField();
        initInputType();
    }

    private void initValidators() {

    }

    private void initInputType() {

        if (inputType != null && inputType.equals(NUMBER_TYPE)) {

            binding.text.setInputType(TYPE_CLASS_NUMBER);

        } else if (inputType != null && inputType.equals(EMAIL_TYPE)) {

            binding.text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        }
    }

    private void initHints() {

        if (hint != null) {

            binding.text.setHint(hint);
            binding.error.setSelected(true);
        }
    }

    private void initMaxLength() {

        if (textLimit != DEFAULT_MAX_LENGTH) {

            InputFilter[] FilterArray = new InputFilter[1];

            FilterArray[0] = new InputFilter.LengthFilter(textLimit);

            binding.text.setFilters(FilterArray);
        }
    }

    private void setStartImageDrawable() {

        if (imageStart != null) {

            binding.imageStart.setVisibility(VISIBLE);

            binding.imageStart.setImageDrawable(imageStart);
        }
    }

    private void setEndImageDrawable() {

        if (imageEnd != null) {

            binding.imageEnd.setVisibility(VISIBLE);

            binding.imageEnd.setImageDrawable(imageEnd);
        }
    }

    private void setPasswordField() {

        if (inputType != null && inputType.equals(PASSWORD_TYPE)) {

            binding.text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    public EditText getEditText() {

        return binding.text;
    }

    public void showError(String errorMessage) {

        binding.error.setText(errorMessage);
        binding.error.setAlpha(0f);
        binding.error.setVisibility(View.VISIBLE);
        binding.error.animate().alpha(1f);
    }


    public boolean isError() {
        return !binding.error.getText().toString().isEmpty() && binding.error.getVisibility() == VISIBLE;
    }


    public void hideError() {
        binding.error.animate().alpha(0);
        binding.error.setVisibility(GONE);
    }

    public String getHint() {
        return hint;
    }

    public void setViewModel() {
        setViewModel("");
    }

    public void setViewModel(String data) {

    }

    public void setKeyListener(KeyListener keyListener) {
        this.binding.text.setKeyListener(keyListener);
    }

    public String getValue() {
        return null;
    }

    public interface FormEditTextInterface {

        void onFocusChange(boolean hasFocus);
    }
}
