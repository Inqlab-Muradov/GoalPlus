package com.example.goalplus.presentation.ui.matches

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalplus.common.NetworkResponseState
import com.example.goalplus.data.mapper.toMatchesTableModel
import com.example.goalplus.domain.model.MatchesModel
import com.example.goalplus.domain.usecase.MatchesTableUseCase
import com.example.goalplus.domain.usecase.MatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesUseCase: MatchesUseCase,
    private val matchesTableUseCase: MatchesTableUseCase
) : ViewModel() {

    private val _matchesState = MutableLiveData<MatchesUiState>()
    val matchesState: LiveData<MatchesUiState> get() = _matchesState

    private val _matchWeek = MutableLiveData<Int?>()
    val matchWeek: LiveData<Int?> get() = _matchWeek

    private val _matchList = MutableLiveData<List<MatchesModel>>()
    val matchList: LiveData<List<MatchesModel>> get() = _matchList

    private var maxWeek = 0

    private lateinit var listAllMatches: List<MatchesModel>

    private val _matchesTableState = MutableLiveData<MatchesTableState>()
    val matchesTableState: LiveData<MatchesTableState> get() = _matchesTableState



    fun getMatches(code: String) {
        viewModelScope.launch {
            matchesUseCase(code).collectLatest {
                when (it) {
                    is NetworkResponseState.Success -> {
                        it.result?.let { list ->
                            listAllMatches = list
                            _matchesState.postValue(MatchesUiState.Success(list))
                            _matchWeek.postValue(list[0].season?.currentMatchday)
                        }
                    }

                    is NetworkResponseState.Error -> {
                        _matchesState.postValue(MatchesUiState.Error(it.exception.toString()))
                    }

                    is NetworkResponseState.Loading -> {
                        _matchesState.postValue(MatchesUiState.Loading)
                    }

                }
            }
        }
    }

    fun searchMatches(teamName:String){
        val searchedTeam = listAllMatches.filter {
            it.homeTeam?.shortName == teamName  || it.awayTeam?.shortName == teamName
        }
        if (searchedTeam.isNotEmpty()){
            _matchList.postValue(searchedTeam)
        }else{
            currentWeekMatches()
        }
    }

    fun currentWeekMatches() {
        val currentWeekMatchesList = listAllMatches.filter {
            it.matchDay == listAllMatches[0].season?.currentMatchday
        }
        _matchList.postValue(currentWeekMatchesList)
        _matchWeek.postValue(listAllMatches[0].season?.currentMatchday)
    }

     fun allWeekMatches() {
        val allWeekMatches = listAllMatches.filter {
            it.matchDay == _matchWeek.value
        }
        _matchList.postValue(allWeekMatches)
    }

    fun increaseWeek() {
        val weekList = listAllMatches.map {
            it.matchDay
        }
        for (num in weekList) {
            num?.let { number ->
                if (number >= maxWeek) {
                    maxWeek = number
                }
            }
        }
        _matchWeek.value?.let {
            if (it < maxWeek) {
                _matchWeek.value = (_matchWeek.value ?: 0) + 1
            }
        }
        allWeekMatches()
    }

    fun decreaseWeek() {
        _matchWeek.value?.let {
            if (it > 1) {
                _matchWeek.value = (_matchWeek.value ?: 0) - 1
            }
        }
        allWeekMatches()
    }

    fun getMatchesTable(code:String){
        viewModelScope.launch {
            matchesTableUseCase(code).collectLatest {
                when(it){
                    is NetworkResponseState.Success->{
                        it.result?.let { list->
                            val data = list[0].table?.toMatchesTableModel()
                            data?.let {
                                _matchesTableState.postValue(MatchesTableState.Success(data))
                            }
                        }
                    }
                    is NetworkResponseState.Error->{
                        _matchesTableState.postValue(MatchesTableState.Error(it.exception.toString()))
                    }
                    is NetworkResponseState.Loading->{
                        _matchesTableState.postValue(MatchesTableState.Loading)
                    }
                }
            }
        }
    }
}