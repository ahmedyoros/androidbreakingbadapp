package com.eservices.breakingbad.presentation.viewmodel;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.eservices.breakingbad.data.repository.characterdisplay.CharacterDisplayRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final CharacterDisplayRepository characterDisplayRepository;

    public ViewModelFactory(CharacterDisplayRepository characterDisplayRepository) {
        this.characterDisplayRepository = characterDisplayRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterInfoViewModel.class)) {
            return (T) new CharacterInfoViewModel(characterDisplayRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}