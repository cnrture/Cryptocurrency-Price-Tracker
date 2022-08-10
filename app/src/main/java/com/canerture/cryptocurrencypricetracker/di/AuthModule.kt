package com.canerture.cryptocurrencypricetracker.di

import com.canerture.cryptocurrencypricetracker.data.repository.FirebaseAuthenticator
import com.canerture.cryptocurrencypricetracker.domain.repository.Authenticator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthenticator(
        firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore
    ): Authenticator = FirebaseAuthenticator(firebaseAuth, firebaseFirestore)
}