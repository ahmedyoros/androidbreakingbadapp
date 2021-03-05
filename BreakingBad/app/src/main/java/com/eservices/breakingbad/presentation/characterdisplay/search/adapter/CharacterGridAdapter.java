package com.eservices.breakingbad.presentation.characterdisplay.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.eservices.breakingbad.R;
import com.eservices.breakingbad.presentation.characterdisplay.CharacterDisplayActivity;
import com.eservices.breakingbad.presentation.characterdisplay.characterinfodetail.CharacterDetailActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CharacterGridAdapter extends RecyclerView.Adapter<CharacterGridAdapter.CharacterViewHolder> {


    public static class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView nameTextView;
        private TextView nicknameTextView;
        private ImageView iconImageView;
        private ImageView eyeImageView;
        private View v;
        private CharacterItemViewModel characterItemViewModel;

        public CharacterViewHolder(View v, final CharacterActionInterface characterActionInterface) {
            super(v);
            this.v = v;
            v.setOnClickListener(this);
            nameTextView = v.findViewById(R.id.character_infogrid_name_textview);
            nicknameTextView = v.findViewById(R.id.character_infogrid_nickname_textview);
            iconImageView = v.findViewById(R.id.character_infogrid_photo_imageview);
            eyeImageView = v.findViewById(R.id.character_infogrid_already_seen_imageview);
        }


        void bind(CharacterItemViewModel characterItemViewModel) {
            this.characterItemViewModel = characterItemViewModel;
            setupAlreadySeen(characterItemViewModel.getId());
            nameTextView.setText(characterItemViewModel.getName());
            nicknameTextView.setText(characterItemViewModel.getNickName());
            Glide.with(v)
                    .load(characterItemViewModel.getImageUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);

        }

        private void setupAlreadySeen(int id){
            SharedPreferences prefs = v.getContext().getSharedPreferences(CharacterDisplayActivity.CHARACTER_ID_LIST, Context.MODE_PRIVATE);
            Set characterIdsSet = prefs.getStringSet(CharacterDisplayActivity.ID_SET, new HashSet<String>());
            if(characterIdsSet.contains(String.valueOf(id))){
                eyeImageView.setVisibility(View.VISIBLE);
            } else {
                eyeImageView.setVisibility(View.GONE);
            }

        }

        @Override
        public void onClick(View view) {
            if(characterItemViewModel != null) {
                saveCharacterIdInLocal(characterItemViewModel.getId());
                Context context = view.getContext();
                eyeImageView.setVisibility(View.VISIBLE);
                Intent intent = new Intent(context, CharacterDetailActivity.class);
                intent.putExtra(CharacterDetailActivity.CHARACTER_DETAIL_ID, characterItemViewModel);
                context.startActivity(intent);
            }
        }

        // Use SharedPreferences to save date in local
        private void saveCharacterIdInLocal(int id){
            SharedPreferences prefs = v.getContext().getSharedPreferences(CharacterDisplayActivity.CHARACTER_ID_LIST, Context.MODE_PRIVATE);
            Set characterIdsSet = prefs.getStringSet(CharacterDisplayActivity.ID_SET, new HashSet<String>());
            characterIdsSet.add(String.valueOf(id));

            SharedPreferences.Editor editor = v.getContext().getSharedPreferences(CharacterDisplayActivity.CHARACTER_ID_LIST, Context.MODE_PRIVATE).edit();
            editor.putStringSet(CharacterDisplayActivity.ID_SET, characterIdsSet);
            editor.apply();
        }

    }

    private List<CharacterItemViewModel> characterItemViewModelList;
    private CharacterActionInterface characterActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CharacterGridAdapter(CharacterActionInterface characterActionInterface) {
        characterItemViewModelList = new ArrayList<>();
        this.characterActionInterface = characterActionInterface;
    }

    public void bindViewModels(List<CharacterItemViewModel> characterItemViewModelList) {
        this.characterItemViewModelList.clear();
        this.characterItemViewModelList.addAll(characterItemViewModelList);
        notifyDataSetChanged();
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character_for_gridview, parent, false);
        CharacterViewHolder characterViewHolder = new CharacterViewHolder(v, characterActionInterface);
        return characterViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        holder.bind(characterItemViewModelList.get(position));
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return characterItemViewModelList.size();
    }


}