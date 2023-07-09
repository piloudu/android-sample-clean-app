package android.template.di

import android.template.api.apimodels.BASE_CAT_URL
import android.template.api.apimodels.rickandmorty.RICK_AND_MORTY_BASE_URL
import android.template.api.interceptors.getCatLocalInterceptor
import android.template.api.retrofit.CatNetworkClient
import android.template.api.retrofit.NetworkClient
import android.template.api.retrofit.RickAndMortyNetworkClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val RICK_AND_MORTY_SCOPE_NAME: String = "RickAndMortyScopeName"
const val CAT_SCOPE_NAME: String = "CatScopeName"

val networkModule: Module = module {
    single<NetworkClient>(named(CAT_SCOPE_NAME)) {
        CatNetworkClient(
            baseUrl = BASE_CAT_URL,
            localInterceptor = getCatLocalInterceptor(),
        )
    }
    single<NetworkClient>(named(RICK_AND_MORTY_SCOPE_NAME)) {
        RickAndMortyNetworkClient(
            baseUrl = RICK_AND_MORTY_BASE_URL,
        )
    }
}
