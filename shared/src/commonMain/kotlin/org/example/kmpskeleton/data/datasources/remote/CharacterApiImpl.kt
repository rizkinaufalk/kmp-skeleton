package org.example.kmpskeleton.data.datasources.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.kmpskeleton.data.datasources.remote.model.CharacterDto
import org.example.kmpskeleton.data.datasources.remote.model.CharacterPageDto
import org.example.kmpskeleton.domain.util.AppConstant

class CharacterApiImpl(
    private val httpClient: HttpClient
) : CharacterApi {
    override suspend fun getCharacterPage(page: Int): CharacterPageDto {
        return httpClient.get("${AppConstant.GET_CHARACTER}?page=$page").body()
    }


    override suspend fun getCharacterById(id: Int): CharacterDto {
        return httpClient.get("${AppConstant.GET_CHARACTER}/$id").body()
    }

}