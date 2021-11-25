package com.pansgami.to_do.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pansgami.to_do.Util.Constants.PREFERENCE_KEY
import com.pansgami.to_do.Util.Constants.PREFERENCE_NAME
import com.pansgami.to_do.data.modal.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name= PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object PreferenceKeys {
        val sortKey = stringPreferencesKey(name= PREFERENCE_KEY)

    }
    private val dataStore = context.dataStore

    suspend fun persistSortState(priority: Priority){
        dataStore.edit { preference->
            preference[PreferenceKeys.sortKey] = priority.name
        }
    }

    val readSortState: Flow<String> = dataStore.data
        .catch {e ->
            if(e is IOException){
                emit(emptyPreferences())
            }else{
                throw e
            }
        }
        .map { preferences->
            val sortState = preferences[PreferenceKeys.sortKey]?:Priority.NONE.name
            sortState

        }

}