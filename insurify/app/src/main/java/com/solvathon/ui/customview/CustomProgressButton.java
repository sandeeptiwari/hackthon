package com.solvathon.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.solvathon.R;

public class CustomProgressButton extends RelativeLayout {
    private LayoutInflater mInflater;

    private TextView mCustomButton;
    private ProgressBar mProgressBar;
    private ImageView mArrowImg;
    private ImageView mTickImage;


    public CustomProgressButton(Context context) {
        super(context);
        init(context);
    }

    public CustomProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomProgressButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mInflater = LayoutInflater.from(context);
        View v = mInflater.inflate(R.layout.custom_progress_button, this, true);
        mProgressBar = v.findViewById(R.id.progressBtn);
        mArrowImg = v.findViewById(R.id.arrowImg);
        mTickImage = v.findViewById(R.id.doneImg);
        mCustomButton = v.findViewById(R.id.textBtn);
        mProgressBar.setVisibility(GONE);
        mArrowImg.setVisibility(VISIBLE);
        mTickImage.setVisibility(GONE);
    }

    public void setText(int resId) {
        mCustomButton.setText(resId);
    }

    public void startLoading(String text) {
        if (text != null) {
            setText(text);
        }
        mProgressBar.setVisibility(VISIBLE);
        mArrowImg.setVisibility(GONE);
        mTickImage.setVisibility(GONE);
        this.setEnabled(false);
    }

    public void reset(int resId) {
        setText(resId);
        mProgressBar.setVisibility(GONE);
        mArrowImg.setVisibility(VISIBLE);
        mTickImage.setVisibility(GONE);
        this.setEnabled(true);
    }


    public void reset(String text) {
        if (text != null) {
            setText(text);
        }
        mProgressBar.setVisibility(GONE);
        mArrowImg.setVisibility(VISIBLE);
        mTickImage.setVisibility(GONE);
        this.setEnabled(true);
    }

    public void setSuccess(String text) {
        if (text != null) {
            setText(text);
        }
        mProgressBar.setVisibility(GONE);
        mArrowImg.setVisibility(GONE);
        mTickImage.setVisibility(VISIBLE);
        this.setEnabled(true);
    }

    private void setText(String text) {
        mCustomButton.setText(text);
    }
}
