package com.eservices.breakingbad.presentation.characterdisplay.search.mapper;

import android.text.TextUtils;

import com.eservices.breakingbad.data.api.model.CharacterInfo;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class CharacterToViewModelMapper {

    private CharacterItemViewModel map(CharacterInfo characterInfo) {
        CharacterItemViewModel characterItemViewModel = new CharacterItemViewModel();
        characterItemViewModel.setId(characterInfo.getId());
        characterItemViewModel.setName(characterInfo.getName());
        characterItemViewModel.setBirthday(characterInfo.getBirthday());
        characterItemViewModel.setStatus(characterInfo.getStatus());
        characterItemViewModel.setNickName(characterInfo.getNickName());
        characterItemViewModel.setPortrayed(characterInfo.getPortrayed());
        characterItemViewModel.setCategory(characterInfo.getCategory());

        if (characterInfo.getImageUrl() != null) {
            characterItemViewModel.setImageUrl(characterInfo.getImageUrl());
        }

        if (characterInfo.getOccupationList() == null) {
            characterItemViewModel.setOccupations("N.C.");
        } else {
            characterItemViewModel.setOccupations(TextUtils.join(", ", characterInfo.getOccupationList()));
        }

        if (characterInfo.getAppearanceList() == null) {
            characterItemViewModel.setAppearance("N.C.");
        } else {
            characterItemViewModel.setAppearance(TextUtils.join(", ", characterInfo.getAppearanceList()));
        }

        return characterItemViewModel;
    }

    public List<CharacterItemViewModel> map(List<CharacterInfo> characterInfoList) {
        List<CharacterItemViewModel> characterItemViewModelList = new ArrayList<>();
        for (CharacterInfo characterInfo : characterInfoList) {
            characterItemViewModelList.add(map(characterInfo));
        }
        return characterItemViewModelList;
    }

    public CharacterItemViewModel mapDetail(CharacterInfo characterInfo) {
        return map(characterInfo);
    }
}
