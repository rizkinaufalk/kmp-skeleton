package org.example.kmpskeleton.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.kmpskeleton.data.remote.entity.CharacterEntity
import org.example.kmpskeleton.domain.NetworkResponse
import org.example.kmpskeleton.domain.getSafeNetworkResponse
import org.example.kmpskeleton.domain.remote.ICharacterApi
import org.example.kmpskeleton.domain.util.AppConstant

class CharacterApiImpl (
    private val httpClient: HttpClient
): ICharacterApi{
    override suspend fun getCharacter(page: Int): NetworkResponse<CharacterResponse> =
        getSafeNetworkResponse {
            httpClient.get("${AppConstant.GET_CHARACTER}?page=$page").body()
        }

    override suspend fun getCharacterById(id: Int): NetworkResponse<CharacterEntity> =
        getSafeNetworkResponse {
            httpClient.get("${AppConstant.GET_CHARACTER}/$id").body()
        }
}