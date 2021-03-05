package com.eservices.breakingbad.presentation.characterdisplay.characterinfodetail;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.eservices.breakingbad.R;
import com.eservices.breakingbad.presentation.characterdisplay.characterinfodetail.fragment.CharacterDetailFragment;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterItemViewModel;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String CHARACTER_DETAIL_ID = "detail_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.character_detail_containerView, CharacterDetailFragment.newInstance((CharacterItemViewModel) getIntent().getSerializableExtra(CHARACTER_DETAIL_ID)), null)
                    .commit();
        }

    }

}
