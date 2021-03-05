package com.eservices.breakingbad.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eservices.breakingbad.data.api.model.CharacterInfo;
import com.eservices.breakingbad.data.repository.characterdisplay.CharacterDisplayRepository;
import com.eservices.breakingbad.presentation.characterdisplay.search.adapter.CharacterItemViewModel;
import com.eservices.breakingbad.presentation.characterdisplay.search.mapper.CharacterToViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CharacterInfoViewModel extends ViewModel {
    private CharacterDisplayRepository characterDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private CharacterToViewModelMapper characterToViewModelMapper;

    public CharacterInfoViewModel(CharacterDisplayRepository characterDisplayRepository) {
        this.characterDisplayRepository = characterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.characterToViewModelMapper = new CharacterToViewModelMapper();
    }

    private MutableLiveData<List<CharacterItemViewModel>> characters = new MutableLiveData<List<CharacterItemViewModel>>();
    private MutableLiveData<CharacterItemViewModel> characterDetail = new MutableLiveData<CharacterItemViewModel>();
    private MutableLiveData<Boolean> isDataLoading = new MutableLiveData<Boolean>();

    public MutableLiveData<List<CharacterItemViewModel>> getCharacters() {
        return characters;
    }

    public MutableLiveData<CharacterItemViewModel> getCharacterDetail() {
        return characterDetail;
    }

    public MutableLiveData<Boolean> getIsDataLoading() {
        return isDataLoading;
    }

    public void fetchCharacterInfoList() {
        isDataLoading.postValue(true);
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getCharacterInfoListResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CharacterInfo>>() {

                    @Override
                    public void onSuccess(List<CharacterInfo> characterInfoList) {
                        characters.setValue(characterToViewModelMapper.map(characterInfoList));
                        isDataLoading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        System.out.println(e.toString());
                        isDataLoading.setValue(false);
                    }
                }));
    }


    public void cancelSubscription() {
        compositeDisposable.clear();
        isDataLoading.setValue(false);
    }

}