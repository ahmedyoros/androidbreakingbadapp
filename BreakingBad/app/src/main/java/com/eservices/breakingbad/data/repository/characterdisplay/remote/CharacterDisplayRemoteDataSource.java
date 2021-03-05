package com.eservices.breakingbad.data.repository.characterdisplay.remote;

import com.eservices.breakingbad.data.api.CharacterDisplayService;
import com.eservices.breakingbad.data.api.model.CharacterInfo;

import java.util.List;

import io.reactivex.Single;

/**
 * A class for Character Display Remote DataSource
 */

public class CharacterDisplayRemoteDataSource {

    private CharacterDisplayService characterDisplayService;

    public CharacterDisplayRemoteDataSource(CharacterDisplayService characterDisplayService) {
        this.characterDisplayService = characterDisplayService;
    }

    public Single<List<CharacterInfo>> getCharacterInfoListResponse() {
        return characterDisplayService.allCharacter();
    }

}
