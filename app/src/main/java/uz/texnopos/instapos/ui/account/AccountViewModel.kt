package uz.texnopos.instapos.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.texnopos.instapos.data.helpers.ProfileHelper
import uz.texnopos.instapos.data.mode.User
import com.texnopos.uz.instagramtexnopos.data.Resource

class AccountViewModel(private val profileHelper: ProfileHelper) : ViewModel() {
    private var mutableProfile: MutableLiveData<Resource<User>> = MutableLiveData()
    val profile: LiveData<Resource<User>>
        get() = mutableProfile

    fun getProfileData() {
        mutableProfile.value = Resource.loading()
            profileHelper.getProfileData(
                {
                    mutableProfile.value = Resource.success(it)
                }, {
                    mutableProfile.value = Resource.error(it)
                }
            )
    }
}