package com.canerture.cryptocurrencypricetracker.domain.repository

import com.canerture.cryptocurrencypricetracker.common.Resource
import com.canerture.cryptocurrencypricetracker.domain.model.CoinDetailUI
import com.canerture.cryptocurrencypricetracker.domain.model.FavouritesUI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface Authenticator {

    fun getFirebaseUserUid(): Flow<String>

    fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    fun isCurrentUserExist(): Flow<Boolean>

    fun getCurrentUser(): Flow<Resource<FirebaseUser>>

    fun addToFavourites(coinDetailUI: CoinDetailUI): Flow<Resource<Task<Void>>>

    fun deleteFromFavourites(favouritesUI: FavouritesUI): Flow<Resource<Task<Void>>>

    fun getFavourites(): Flow<Resource<List<FavouritesUI>>>

    fun signOut()
}