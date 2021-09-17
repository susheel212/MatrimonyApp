package com.susheelkaram.matrimonyapp.ui.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.susheelkaram.matrimonyapp.data.MatchesRepository
import com.susheelkaram.matrimonyapp.data.model.MatchUser
import com.susheelkaram.matrimonyapp.ui.UiEvent
import com.susheelkaram.matrimonyapp.ui.UiState
import com.susheelkaram.matrimonyapp.util.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class MatchesViewModel(private val matchesRepository: MatchesRepository) : ViewModel() {
    private val _stateData = MutableLiveData<UiState>()
    private val uiEventsData = SingleLiveEvent<UiEvent>()

    fun getStateData() = _stateData as LiveData<UiState>
    fun getUiEventData() = uiEventsData as SingleLiveEvent<UiEvent>

    val disposable = CompositeDisposable()

    fun fetchMatchesList(isNetworkAvailable: Boolean) {
        if(isNetworkAvailable){
            _stateData.value = UiState.Loading
            val getMatchesSingle = matchesRepository.getMatchesList(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success ->
                    _stateData.value = UiState.Success(success)
                }, { error ->
                    {
                        _stateData.value = UiState.Error(Exception(error.message))
                        uiEventsData.value =
                            UiEvent.Message(error.message ?: "Error fetching matches")
                    }
                })
            disposable.add(getMatchesSingle)
        }
        else {
            val getMatchesSingle = matchesRepository.getMatchesFromCache(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ success ->
                    _stateData.value = UiState.Success(success)
                    uiEventsData.value = UiEvent.Message("Device is offline")
                }, { error ->
                    {
                        uiEventsData.value =
                            UiEvent.Message(error.message ?: "Error fetching matches")
                    }
                })
            disposable.add(getMatchesSingle)
        }
    }

    fun updateInvitationStatus(user: MatchUser, invitationStatus: Int) {
        matchesRepository.updateAcceptanceStatus(invitationStatus, user)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}

class MatchesViewModelFactory(private val matchesRepository: MatchesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MatchesViewModel::class.java)) {
            return MatchesViewModel(matchesRepository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }

}