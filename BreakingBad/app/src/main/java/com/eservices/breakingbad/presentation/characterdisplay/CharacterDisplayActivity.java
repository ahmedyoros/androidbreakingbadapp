package com.eservices.breakingbad.presentation.characterdisplay;


import android.os.Bundle;

import com.eservices.breakingbad.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.eservices.breakingbad.presentation.characterdisplay.search.fragment.CharacterGridFragment;
import com.eservices.breakingbad.presentation.characterdisplay.search.fragment.CharacterListFragment;


public class CharacterDisplayActivity extends AppCompatActivity {

    public static String CHARACTER_ID_LIST = "CHARACTER_ID_LIST";
    public static String ID_SET = "ID_SET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }


    private void setupViewPagerAndTabs() {
        ViewPager viewPager = findViewById(R.id.tab_viewpager);

        final CharacterListFragment characterListFragment = CharacterListFragment.newInstance();
        final CharacterGridFragment characterGridFragment = CharacterGridFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return characterListFragment;
                }
                return characterGridFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return CharacterListFragment.TAB_NAME;
                }
                return CharacterGridFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }


}
