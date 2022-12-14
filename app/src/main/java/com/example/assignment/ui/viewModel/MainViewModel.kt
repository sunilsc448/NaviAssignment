package com.example.assignment.ui.viewModel

import androidx.lifecycle.*
import com.example.assignment.data.model.PullRequest
import com.example.assignment.data.repository.MainRepository
import com.example.assignment.ui.utils.RemoteDataResponse
import com.example.assignment.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DEBOUNCE_PERIOD = 2000L
class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val dataStatus:MutableLiveData<Status> = MutableLiveData()
    fun getDataStatus():LiveData<Status> {
        return dataStatus
    }

    private val response:MutableLiveData<List<PullRequest>> = MutableLiveData()
    fun getResponse():LiveData<List<PullRequest>> = response

    /** For Pagination */
    private var page = 0
    var isLastPage:Boolean = false
    private set
    var blockContinuousPagination = false
    private set

    init {
        dataStatus.value = Status.EMPTY
        fetchClosedPullRequests()
    }

    private fun fetchClosedPullRequests(){
        page++
        dataStatus.value = Status.LOADING
        viewModelScope.launch{
            when(val response = mainRepository.getClosedPullRequests(page, Dispatchers.IO)){
                is RemoteDataResponse.SuccessResponse -> {
                    val data = response.data
                    handleSuccessData(data)
                }
                is RemoteDataResponse.ErrorResponse ->{
                    handleErrorData(response)
                }
            }
        }
    }

    private fun handleErrorData(response: RemoteDataResponse.ErrorResponse) {
        page--
        when (response.status) {
            Status.NETWORK_ERROR -> dataStatus.value = Status.NETWORK_ERROR
            else -> dataStatus.value = Status.API_ERROR
        }
    }

    private fun handleSuccessData(data: List<PullRequest>) {
        dataStatus.value = Status.SUCCESS
        if(data.isNotEmpty()) {
            /** Pagination support
             * Check for already present data and stitch the new data to it*/
            if(response.value != null && response.value!!.isNotEmpty()){
                val newList:MutableList<PullRequest> = mutableListOf()
                newList.addAll(response.value!!)
                newList.addAll(data)
                response.value = newList
            }else{
                response.value = data
            }
        }else{
            /** Turn off Pagination support
             *  If data is empty for any particular page*/
            page--
            isLastPage = true
            if(response.value == null || response.value!!.isEmpty())
                dataStatus.value = Status.EMPTY
        }
    }

    fun loadMore(){
        /** blockContinuousLoad blocks the back to back pagination calls */
        if(!blockContinuousPagination) {
            blockContinuousPagination = true
            fetchClosedPullRequests()
            viewModelScope.launch {
                delay(DEBOUNCE_PERIOD)
                blockContinuousPagination = false
            }
        }
    }

    fun refresh(){
        reset()
        fetchClosedPullRequests()
    }

    private fun reset() {
        page = 0
        dataStatus.value = Status.EMPTY
        isLastPage = false
        blockContinuousPagination = false
        response.value = emptyList()
    }
}