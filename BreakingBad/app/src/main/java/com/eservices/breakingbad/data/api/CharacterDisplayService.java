package com.eservices.breakingbad.data.api;


import com.eservices.breakingbad.data.api.model.CharacterInfo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CharacterDisplayService {

    /**
     * Provide all characters information
     */
    @GET("characters")
    Single<List<CharacterInfo>> allCharacter();

}
