package com.roundarch.codetest.part1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.roundarch.codetest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdigiovanni on 8/15/13.
 */
public class Part1Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    // TODO - any member variables you need to store?
    private SeekBar seekBar1, seekBar2;
    private ToggleButton seekbarController;
    private TextView tvDisplay;
    private int progressChange1, progressChange2;
    private int lastProgressChange;//lastest progress changed value. This value is used when seekbars are synced
    private boolean isSync;
    private String tvDisplayTitle = "Seekbar Values are: \n";


    //FIXME: Improve something! Anything
    // TODO - obtain references to your views from the layout
    // TODO - hook up any event listeners that make sense for the task
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1, container, false);
        seekbarController = (ToggleButton) view.findViewById(R.id.tbSeekBarController);
        seekBar1 = (SeekBar) view.findViewById(R.id.seekbar1);
        seekBar2 = (SeekBar) view.findViewById(R.id.seekbar2);
        tvDisplay = (TextView) view.findViewById(R.id.tvDisplay);

        seekbarController.setOnClickListener(this);
        seekbarController.setChecked(false);//default value = unsync
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);

        progressChange1 = progressChange2 = 0;
        lastProgressChange = 0;
        isSync = seekbarController.isChecked();
        return view;
    }

    @Override
    public void onClick(View v) {
        isSync = seekbarController.isChecked();
        if (isSync) {
            progressChange1 = lastProgressChange;
            progressChange2 = lastProgressChange;
            seekBar1.setProgress(progressChange1);
            seekBar2.setProgress(progressChange2);
        }
    }

    /****
     * Implementation for OnSeekBarChangeListener
     ****/
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()) {
            case R.id.seekbar1:
                progressChange1 = progress;
                if (isSync) {
                    progressChange2 = progress;
                    seekBar2.setProgress(progress);
                }
                break;
            case R.id.seekbar2:
                progressChange2 = progress;
                if (isSync) {
                    progressChange1 = progress;
                    seekBar1.setProgress(progress);
                }
                break;
            default:
                break;
        }

        lastProgressChange = progress;
        updateDisplay();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void updateDisplay() {
        String textDisplay1 = "\t Seekbar1 Value : " + progressChange1 + "\n";
        String textDisplay2 = "\t Seekbar2 Value : " + progressChange2;
        tvDisplay.setText(tvDisplayTitle + textDisplay1 + textDisplay2);
    }

    /*** OnSeekBarChangeListener Implementation End***/
}
