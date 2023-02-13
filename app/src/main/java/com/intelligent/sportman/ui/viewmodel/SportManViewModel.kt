package com.intelligent.sportman.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intelligent.sportman.data.remote.repository.SportRepository
import com.intelligent.sportman.model.RequestState
import com.intelligent.sportman.model.Sport
import com.intelligent.sportman.model.SportEvent
import com.intelligent.sportman.util.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SportManViewModel @Inject constructor(
    private val sportRepository: SportRepository
) : ViewModel() {

    //  internal LiveData can be mutable to update the sport and sport events
    private var _sports = MutableLiveData<List<Sport>>(listOf())
    // external LiveData should be immutable to just pass the sport and sport events
    val sports: LiveData<List<Sport>> = _sports

    //  internal LiveData can be mutable to update the filter mode of sport events
    private var _mode = MutableLiveData<Mode>(Mode.ALL)
    // external LiveData should be immutable to just pass the filter mode of sport events
    val mode: LiveData<Mode> = _mode

    //  internal LiveData can be mutable to store and update the status of the most recent request
    private var _requestState = MutableLiveData(RequestState.INITIAL)
    // external LiveData should be immutable to just pass the request status
    val requestState: LiveData<RequestState> = _requestState

    /**
     * Call api when method invoked
     */
    fun getSports() {
        viewModelScope.launch {
            _requestState.value = RequestState.LOADING
            try {
                _sports.value = sportRepository.getSports()
                _requestState.value = RequestState.SUCCESS
            } catch (e: java.lang.Exception) {
                _requestState.value = RequestState.ERROR
            }
        }
    }

    /**
     * Removes one sport event from sportEvent and adds it to favoriteSportEventList list
     */
    fun addFavorite(sportIndex: Int, sportEvent: SportEvent) {

        // get the current list of sports, or return if null
        val sportsList = _sports.value ?: return

        // get current sport at the specified index, or return if null
        val currentSport = sportsList.getOrNull(sportIndex) ?: return

        // remove the specified sport event from the sport's sportEvents list
        val updatedSportEvents = currentSport.sportEvents.filter { it != sportEvent }

        // remove the specified sport event from the sport's favoriteSportEvents list
        val updatedFavoriteSportEvents =
            currentSport.favoriteSportEvents.toMutableList().apply { add(0, sportEvent) }

        // create a copy of the sport with the updated lists
        val updatedSport = currentSport.copy(
            sportEvents = updatedSportEvents,
            favoriteSportEvents = updatedFavoriteSportEvents
        )

        // create a copy of the sports list with the updated sport
        val updatedSportsList = sportsList.toMutableList().apply { set(sportIndex, updatedSport) }

        // update the LiveData with the new list of sports
        _sports.value = updatedSportsList

    }

    /**
     * Removes one sport event from favoriteSportEventList and adds it to sportEvent list
     */
    fun removeFavorite(sportIndex: Int, favSportEvent: SportEvent) {

        // get the list of sports, or return if null
        val sportsList = _sports.value ?: return

        // get current sport at the specified index, or return if null
        val currentSport = sportsList.getOrNull(sportIndex) ?: return

        // remove the specified sport event from the sport's sportEvents list
        val updatedFavoriteSportEvents = currentSport.favoriteSportEvents.filter { it != favSportEvent }

        // remove the specified sport event from the sport's favoriteSportEvents list
        val updatedSportEvents =
            currentSport.sportEvents.toMutableList().apply { add(0, favSportEvent) }

        // create a copy of the sport with the updated lists
        val updatedSport = currentSport.copy(
            sportEvents = updatedSportEvents,
            favoriteSportEvents = updatedFavoriteSportEvents
        )

        // create a copy of the sports list with the updated sport
        val updatedSportsList = sportsList.toMutableList().apply { set(sportIndex, updatedSport) }

        // update the LiveData with the new list of sports
        _sports.value = updatedSportsList

    }

    /**
     * Updates the filter mode of sport event list
     */
    fun updateMode(mode: Mode) {
        _mode.value = mode
    }
}