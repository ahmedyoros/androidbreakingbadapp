package com.eservices.breakingbad.presentation.characterdisplay.search.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eservices.breakingbad.R;
import com.eservices.breakingbad.data.di.FakeDependencyInjection;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterActionInterface;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterGridAdapter;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterItemViewModel;
import com.eservices.breakingbad.presentation.viewmodel.CharacterInfoViewModel;

import java.util.List;

public class CharacterGridFragment extends Fragment implements CharacterActionInterface {

    public static final String TAB_NAME = "Grid View";
    private View rootView;
    private CharacterGridAdapter characterGridAdapter;
    private ProgressBar progressBar;
    private CharacterInfoViewModel characterInfoViewModel;

    private CharacterGridFragment() {
    }

    public static CharacterGridFragment newInstance() {
        return new CharacterGridFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_character_list_gridview, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        progressBar = rootView.findViewById(R.id.progress_bar);

        registerViewModels();
        characterInfoViewModel.fetchCharacterInfoList();
    }

    private void registerViewModels() {
        characterInfoViewModel = new ViewModelProvider(requireActivity(), FakeDependencyInjection.getViewModelFactory()).get(CharacterInfoViewModel.class);

        characterInfoViewModel.getCharacters().observe(getViewLifecycleOwner(), new Observer<List<CharacterItemViewModel>>() {
            @Override
            public void onChanged(List<CharacterItemViewModel> characterItemViewModelList) {
                characterGridAdapter.bindViewModels(characterItemViewModelList);
            }
        });

        characterInfoViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isDataLoading) {
                progressBar.setVisibility(isDataLoading ? View.VISIBLE : View.GONE);
            }
        });
    }


    private void setupRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_gridview);
        recyclerView.setLayoutManager(gridLayoutManager);
        characterGridAdapter = new CharacterGridAdapter(this);
        recyclerView.setAdapter(characterGridAdapter);
    }

}
