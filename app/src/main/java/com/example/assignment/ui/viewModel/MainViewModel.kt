package com.example.assignment.ui.viewModel

import androidx.lifecycle.*
import com.example.assignment.data.model.PullRequest
import com.example.assignment.data.repository.MainRepository
import com.example.assignment.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val dataStatus:MutableLiveData<Status> = MutableLiveData()
    private var page = 1
    fun getDataStatus():LiveData<Status> {
        return dataStatus
    }

    private val response:MutableLiveData<List<PullRequest>> = MutableLiveData()
    fun getResponse():LiveData<List<PullRequest>> = response

    init {
        dataStatus.value = Status.EMPTY
        fetchClosedPullRequests()
    }

    private fun fetchClosedPullRequests(){
        dataStatus.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO){
            try {
                val data = mainRepository.getClosedPullRequests(page)
                withContext(Dispatchers.Main){
                    dataStatus.value = Status.SUCCESS
                    if(data.isNotEmpty()) {
                        if(response.value != null && response.value!!.isNotEmpty()){
                            val newList:MutableList<PullRequest> = mutableListOf()
                            newList.addAll(response.value!!)
                            newList.addAll(data)
                            response.value = newList
                        }else{
                            response.value = data
                        }
                    }else{
                        page--
                        if(response.value == null || response.value!!.isEmpty())
                            dataStatus.value = Status.EMPTY
                    }
                }
            } catch (exception: Exception) {
               dataStatus.value = Status.ERROR
            }
        }
    }

    fun loadMore(){
        page++
        fetchClosedPullRequests()
    }


//    private val response = MutableLiveData<RemoteDataResponse>()
//    fun getClosedPullRequests():LiveData<RemoteDataResponse> = response
//    init {
//        response.value = RemoteDataResponse.Response()
//    }
//
//    fun fetchClosedPullRequests(){
//        viewModelScope.launch(Dispatchers.IO){
//            try {
//                val data = mainRepository.getClosedPullRequests()
//                val success = RemoteDataResponse.SuccessResponse(data = data)
//                response.postValue(success)
//            } catch (exception: Exception) {
//                val error = RemoteDataResponse.ErrorResponse(exception = exception)
//                response.postValue(error)
//            }
//        }
//    }

//    fun getClosedPullRequests() = liveData(Dispatchers.IO){
//        emit(RemoteDataResponse.Response())
//        try {
//            emit(RemoteDataResponse.SuccessResponse(data = mainRepository.getClosedPullRequests(viewModelScope)))
//        } catch (exception: Exception) {
//            emit(RemoteDataResponse.ErrorResponse(exception = exception))
//        }
//    }
}