package com.texnopos.uz.instagramtexnopos.di
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import uz.texnopos.instapos.data.Settins
import uz.texnopos.instapos.data.helpers.AuthHelper
import uz.texnopos.instapos.data.helpers.PostHelper
import uz.texnopos.instapos.data.helpers.ProfileHelper
import uz.texnopos.instapos.ui.account.AccountViewModel
import uz.texnopos.instapos.ui.account.edit.EditViewModel
import uz.texnopos.instapos.ui.add.AddFragmentViewModel
import uz.texnopos.instapos.ui.auth.signin.SignInViewModel
import uz.texnopos.instapos.ui.auth.signup.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { AuthHelper(get(), get()) }
    single { ProfileHelper(get(), get()) }
    single { PostHelper(get(), get(), get()) }
    single { Settins(androidContext()) }
}
val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { EditViewModel(get()) }
    viewModel { AddFragmentViewModel(get()) }
}