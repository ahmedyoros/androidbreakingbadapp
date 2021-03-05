package com.eservices.breakingbad.data.repository.characterdisplay;

import com.eservices.breakingbad.data.api.model.CharacterInfo;
import com.eservices.breakingbad.data.repository.characterdisplay.remote.CharacterDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Single;


public class CharacterDisplayDataRepository implements CharacterDisplayRepository {

    private CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource;

    public CharacterDisplayDataRepository(CharacterDisplayRemoteDataSource characterDisplayRemoteDataSource) {
        this.characterDisplayRemoteDataSource = characterDisplayRemoteDataSource;
    }

    @Override
    public Single<List<CharacterInfo>> getCharacterInfoListResponse() {
        return characterDisplayRemoteDataSource.getCharacterInfoListResponse();
    }


}
