package com.durini.solucionlab10.data.repository

import com.durini.solucionlab10.data.local.dao.CharacterDao
import com.durini.solucionlab10.data.local.model.Character
import com.durini.solucionlab10.data.remote.api.RickMortyApi
import com.durini.solucionlab10.data.remote.dto.mapToModel
import com.durini.solucionlab10.data.util.DataState
import com.durini.solucionlab10.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryimpl(
    private val characterDao : CharacterDao,
    private val api : RickMortyApi
)  : CharacterRepository{
    override suspend fun getAllCharacters(): Resource<List<Character>> {
        val localCharacter = characterDao.getCharacters()
        return if (localCharacter.isEmpty()) {
            try {
                val remoteCharacter = api.getCharacters().results
                val charactersToStore = remoteCharacter.map { dto -> dto.mapToModel() }
                characterDao.insertAll(charactersToStore)
                Resource.Success(charactersToStore)
            } catch (e: Exception) {
                Resource.Error(e.message ?: "")
            }
        } else {
            Resource.Success(localCharacter)
        }
    }

    override suspend fun deleteAllCharacters(): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacter(id: Int): Flow<DataState<Character>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCharacter(character: Character): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }

    override fun deleteCharacter(id: Int): Flow<DataState<Int>> {
        TODO("Not yet implemented")
    }


}