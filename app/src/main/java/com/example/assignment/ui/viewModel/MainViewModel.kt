package com.example.assignment.ui.viewModel

import androidx.lifecycle.*
import com.example.assignment.data.model.PullRequest
import com.example.assignment.data.repository.MainRepository
import com.example.assignment.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DEBOUNCE_PERIOD = 2000L
class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val dataStatus:MutableLiveData<Status> = MutableLiveData()
    fun getDataStatus():LiveData<Status> {
        return dataStatus
    }

    private var page = 0

    var isLastPage:Boolean = false
    private set

    var blockContinuousPagination = false
    private set

    private val response:MutableLiveData<List<PullRequest>> = MutableLiveData()
    fun getResponse():LiveData<List<PullRequest>> = response

    init {
        dataStatus.value = Status.EMPTY
        fetchClosedPullRequests()
    }

    private fun fetchClosedPullRequests(){
        page++
        dataStatus.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO){
            try {
                val data = mainRepository.getClosedPullRequests(page)
                withContext(Dispatchers.Main){
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
            } catch (exception: Exception) {
                page--
                /** Error Handling*/
               dataStatus.value = Status.ERROR
            }
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
}