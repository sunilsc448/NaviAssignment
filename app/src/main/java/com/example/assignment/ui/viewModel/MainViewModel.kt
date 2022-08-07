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
    fun getDataStatus():LiveData<Status> = dataStatus

    private val response:MutableLiveData<List<PullRequest>> = MutableLiveData()
    fun getResponse():LiveData<List<PullRequest>> = response

    init {
        dataStatus.value = Status.EMPTY
        fetchClosedPullRequests()
    }

    fun fetchClosedPullRequests(){
        dataStatus.value = Status.LOADING
        viewModelScope.launch(Dispatchers.IO){
            try {
                val data = mainRepository.getClosedPullRequests()
                withContext(Dispatchers.Main){
                    if(data.size > 0) {
                        dataStatus.value = Status.SUCCESS
                        response.value = data
                    }else{
                        dataStatus.value = Status.EMPTY
                    }
                }
            } catch (exception: Exception) {
               dataStatus.value = Status.ERROR
            }
        }
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