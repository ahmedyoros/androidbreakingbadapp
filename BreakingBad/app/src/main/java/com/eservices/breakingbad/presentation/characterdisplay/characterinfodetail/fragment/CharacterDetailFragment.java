
package com.eservices.breakingbad.presentation.characterdisplay.characterinfodetail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.eservices.breakingbad.R;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterActionInterface;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterItemViewModel;


public class CharacterDetailFragment extends Fragment implements CharacterActionInterface {

    private View rootView;
    private static final String BUNDLE_CHARACTER_ID = "bundle_character_id";
    private CharacterItemViewModel mCharacterItemViewModel;


    public static CharacterDetailFragment newInstance(CharacterItemViewModel characterItemViewModel) {
        CharacterDetailFragment fragment = new CharacterDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_CHARACTER_ID, characterItemViewModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_character_detail, container, false);
        initFields();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacterItemViewModel = (CharacterItemViewModel) getArguments().getSerializable(BUNDLE_CHARACTER_ID);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    // Initialize character detail fields
    public void initFields() {
        TextView nameTextView = rootView.findViewById(R.id.character_name_textview);
        TextView birthdayTextView = rootView.findViewById(R.id.character_birthday_textView);
        TextView occupationsTextView = rootView.findViewById(R.id.character_occupations_textView);
        TextView statusTextView = rootView.findViewById(R.id.character_status_textView);
        TextView nicknameTextView = rootView.findViewById(R.id.character_nickname_textview);
        ImageView iconImageView = rootView.findViewById(R.id.character_img_imageView);
        TextView appearanceTextView = rootView.findViewById(R.id.character_appearances_textView);
        TextView portrayedTextView = rootView.findViewById(R.id.character_portrayed_textView);
        TextView categoryTextView = rootView.findViewById(R.id.character_category_textView);

        nameTextView.setText(mCharacterItemViewModel.getName());
        nicknameTextView.setText(mCharacterItemViewModel.getNickName());
        birthdayTextView.setText(mCharacterItemViewModel.getBirthday());
        occupationsTextView.setText(mCharacterItemViewModel.getOccupations());
        statusTextView.setText(mCharacterItemViewModel.getStatus());
        nicknameTextView.setText(mCharacterItemViewModel.getNickName());
        appearanceTextView.setText(mCharacterItemViewModel.getAppearanceList());
        portrayedTextView.setText(mCharacterItemViewModel.getPortrayed());
        categoryTextView.setText(mCharacterItemViewModel.getCategory());

        Glide.with(rootView)
                .load(mCharacterItemViewModel.getImageUrl())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .circleCrop()
                .into(iconImageView);

    }
}
