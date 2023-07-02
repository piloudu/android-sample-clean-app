package android.template.core.data.repositories

import android.template.api.apimodels.CatApiModel
import android.template.core.data.mappers.toDomainModel
import android.template.datasources.CatDataSource
import android.template.domain.models.CatModel
import android.template.domain.repositories.CatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CatRepositoryImpl(
    private val catDataSource: CatDataSource,
) : CatRepository {
    override fun getCat(): Flow<CatModel> {
        return catDataSource.getCat().map(CatApiModel::toDomainModel)
    }
}
