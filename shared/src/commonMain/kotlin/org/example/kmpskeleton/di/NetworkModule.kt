package org.example.kmpskeleton.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.kmpskeleton.domain.util.AppConstant
import org.koin.core.qualifier.named
import org.koin.dsl.module


val provideNetworkModule = module {
    single(named("app")) {
        HttpClient{
//            install(Auth){
//                bearer {
//                    loadTokens {
//                        BearerTokens(
//                            accessToken = "AccessToken",
//                            refreshToken = "RefreshedToken"
//                        )
//                    }
//                }
//            }
            defaultRequest {
                url{
                    takeFrom(AppConstant.BASE_URL)
                }
            }
            install(HttpTimeout){
                val timeout = 15000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(tag = "Http Client", message = message)
                    }
                }
            }.also {
                Napier.base(DebugAntilog())
            }
        }
    }
}