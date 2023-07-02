package android.template.datasources

import android.template.api.apimodels.ProductApiModel
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    fun getData(): Flow<List<ProductApiModel>>
}
