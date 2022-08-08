package com.example.assignment.data.repository

import com.example.assignment.data.api.ApiService
import com.example.assignment.data.model.PullRequest
import com.example.assignment.data.model.User
import com.example.assignment.ui.utils.Status
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainRepositoryTest : TestCase() {
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var SUT: MainRepository
    private lateinit var apiService: SuccessDataService

    @Before
    override fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiService = SuccessDataService()
        SUT = MainRepository(apiService)
    }

    @Test
    fun testGetClosedPullRequestsSuccessCase() {
        val page = 1
        runBlocking {
            val result = SUT.getClosedPullRequests(page, dispatcher)
            assertEquals(result.status, Status.SUCCESS)
        }
    }

    class SuccessDataService:ApiService{
        val mockedPullRequestsData = listOf(PullRequest(title = "Pull request 1",
            user = User(user_name = "Sunil", avatar = ""),
            created_at = "08-08-2022", merged_at = "08-08-2022"),
            PullRequest(title = "Pull request 2",
                user = User(user_name = "Sunil", avatar = ""),
                created_at = "08-08-2022", merged_at = "08-08-2022") )
        override suspend fun getClosedPullRequests(page: Int): List<PullRequest> {
            return mockedPullRequestsData
        }

    }
}

