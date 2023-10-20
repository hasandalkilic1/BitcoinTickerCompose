package com.example.bitcointickercompose.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserManagerDataStorage(var context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")

    companion object {
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        val USER_GENDER_KEY = stringPreferencesKey("USER_GENDER")
        val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL_KEY")
        val USER_PHONE_KEY = stringPreferencesKey("USER_PHONE_KEY")
        val USER_UUID_KEY = stringPreferencesKey("USER_UUID_KEY")
        val USER_PROFILE_PIC_KEY = stringPreferencesKey("USER_PROFILE_PIC_KEY")
        val USER_FULL_NAME_KEY = stringPreferencesKey("USER_FULL_NAME_KEY")
        val USER_ADDRESS_KEY = stringPreferencesKey("USER_ADDRESS_KEY")
        val USER_BIO_KEY = stringPreferencesKey("USER_BIO_KEY")
        val ON_BOARDING_PASSED = booleanPreferencesKey("ON_BOARDING_PASSED")


    }

    suspend fun storeUser(age: Int, name: String, isMale: String) {
        context.dataStore.edit {
            it[USER_AGE_KEY] = age
            it[USER_NAME_KEY] = name
            it[USER_GENDER_KEY] = isMale
        }

    }

    suspend fun storeUserAge(age: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_AGE_KEY] = age
        }
    }

    suspend fun storeUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    suspend fun storeUserGender(gender: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_GENDER_KEY] = gender
        }
    }

    suspend fun storeUserEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    suspend fun storeUserPhone(phone: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE_KEY] = phone
        }
    }

    suspend fun storeUserUUID(uuid: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_UUID_KEY] = uuid
        }
    }

    suspend fun storeUserProfilePic(profilePicUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PROFILE_PIC_KEY] = profilePicUrl
        }
    }

    suspend fun storeUserFullName(fullName: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_FULL_NAME_KEY] = fullName
        }
    }

    suspend fun storeUserAddress(address: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ADDRESS_KEY] = address
        }
    }

    suspend fun storeUserBio(bio: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_BIO_KEY] = bio
        }
    }

    suspend fun storeOnboardingPassed(passed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ON_BOARDING_PASSED] = passed
        }
    }


    // Getters...
    suspend fun getNameFromStorage(): String {
        return context.dataStore.data.first()[USER_NAME_KEY]  ?: ""
    }

    suspend fun getAgeFromStorage(): Int {
        return context.dataStore.data.first()[USER_AGE_KEY] ?: 0
    }

    suspend fun getGenderFromStorage(): String {
        return context.dataStore.data.first()[USER_GENDER_KEY]  ?: ""
    }

    suspend fun getEmailFromStorage(): String {
        return context.dataStore.data.first()[USER_EMAIL_KEY]  ?: ""
    }

    suspend fun getPhoneFromStorage(): String {
        return context.dataStore.data.first()[USER_PHONE_KEY]  ?: ""
    }

    suspend fun getUUIDFromStorage(): String {
        return context.dataStore.data.first()[USER_UUID_KEY]  ?: ""
    }

    suspend fun getProfilePicFromStorage(): String {
        return context.dataStore.data.first()[USER_PROFILE_PIC_KEY]  ?: ""
    }

    suspend fun getFullNameFromStorage(): String {
        return context.dataStore.data.first()[USER_FULL_NAME_KEY]  ?: ""
    }

    suspend fun getAddressFromStorage(): String {
        return context.dataStore.data.first()[USER_ADDRESS_KEY]  ?: ""
    }

    suspend fun getBioFromStorage(): String {
        return context.dataStore.data.first()[USER_BIO_KEY] ?: ""
    }

    suspend fun getOnboardingPassedFromStorage(): Boolean {
        return context.dataStore.data.first()[ON_BOARDING_PASSED] ?: false
    }

    // Flows

    val userNameFlow = context.dataStore.data.map { preferences ->
        preferences[USER_NAME_KEY] ?: ""
    }

    val userGenderFlow= context.dataStore.data.map { preferences ->
        preferences[USER_GENDER_KEY] ?: "Others"
    }

    val userEmailFlow = context.dataStore.data.map { preferences ->
        preferences[USER_EMAIL_KEY] ?: ""
    }

    val userPhoneFlow = context.dataStore.data.map { preferences ->
        preferences[USER_PHONE_KEY] ?: ""
    }

    val userUUIDFlow= context.dataStore.data.map { preferences ->
        preferences[USER_UUID_KEY] ?: ""
    }

    val userProfilePicFlow = context.dataStore.data.map { preferences ->
        preferences[USER_PROFILE_PIC_KEY] ?: ""
    }

    val userFullNameFlow = context.dataStore.data.map { preferences ->
        preferences[USER_FULL_NAME_KEY] ?: ""
    }

    val userAddressFlow= context.dataStore.data.map { preferences ->
        preferences[USER_ADDRESS_KEY] ?: ""
    }

    val userBioFlow = context.dataStore.data.map { preferences ->
        preferences[USER_BIO_KEY] ?: ""
    }

    val onboardingPassedFlow= context.dataStore.data.map { preferences ->
        preferences[ON_BOARDING_PASSED] ?: false
    }
}