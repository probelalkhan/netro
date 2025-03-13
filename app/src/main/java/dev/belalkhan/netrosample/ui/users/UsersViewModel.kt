//package dev.belalkhan.netrosample.ui.users
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import dagger.hilt.android.lifecycle.HiltViewModel
//import dev.belalkhan.netrosample.users.UsersApiService
//import dev.belalkhan.netrosample.users.models.User
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class UsersViewModel
//    @Inject
//    constructor(
//        private val usersApiService: UsersApiService,
//    ) : ViewModel() {
//        private val _users = MutableStateFlow(emptyList<User>())
//        val users: StateFlow<List<User>> = _users
//
//        init {
//            getUsers()
//        }
//
//        private fun getUsers() =
//            viewModelScope.launch {
//                val response = usersApiService.getUsers()
//                _users.value = response.users
//            }
//    }
