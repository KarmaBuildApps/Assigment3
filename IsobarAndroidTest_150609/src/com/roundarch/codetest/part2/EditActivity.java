package com.roundarch.codetest.part2;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.roundarch.codetest.R;

public class EditActivity extends FragmentActivity {
    private EditFragment editFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction ftransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editFragment = new EditFragment();
        fragmentManager = getSupportFragmentManager();
        ftransaction = fragmentManager.beginTransaction();
        ftransaction.replace(R.id.edit_fragment, editFragment);
        ftransaction.commit();
        // TODO - you will need to obtain the model object provided to this activity and provide it to the EditFragment
    }
}
