package com.eservices.breakingbad.data.repository.characterdisplay;

import com.eservices.breakingbad.data.api.model.CharacterInfo;

import java.util.List;

import io.reactivex.Single;

public interface CharacterDisplayRepository {

    Single<List<CharacterInfo>> getCharacterInfoListResponse();

}
