package com.eservices.breakingbad.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A class for character information
 */
public class CharacterInfo {

    private Integer char_id;
    private String name;
    private String birthday;
    private String img;
    private String status;
    private String nickname;
    private String portrayed;
    private String category;

    @SerializedName("occupation")
    private List<String> occupationList;

    @SerializedName("appearance")
    private List<Integer> appearanceList;

    public Integer getId() {
        return char_id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getImageUrl() { return img; }

    public String getStatus() { return status; }

    public String getNickName() {
        return nickname;
    }

    public String getCategory() {
        return category;
    }


    public String getPortrayed() { return portrayed; }

    public List<String> getOccupationList() {
        return occupationList;
    }

    public List<Integer> getAppearanceList() {
        return appearanceList;
    }
}
