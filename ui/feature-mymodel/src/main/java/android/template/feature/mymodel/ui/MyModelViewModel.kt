/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.template.feature.mymodel.ui

import android.template.domain.models.ProductModel
import android.template.domain.usecases.AddModelUseCase
import android.template.domain.usecases.GetModelUseCase
import android.template.feature.mymodel.ui.MyModelUiState.Error
import android.template.feature.mymodel.ui.MyModelUiState.Loading
import android.template.feature.mymodel.ui.MyModelUiState.Success
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyModelViewModel(
    getModelUseCase: GetModelUseCase,
    private val addModelUseCase: AddModelUseCase,
) : ViewModel() {

    val uiState: StateFlow<MyModelUiState> = getModelUseCase()
        .map<ProductModel, MyModelUiState> { Success(data = it.toUiModel()) }
        .catch { emit(Error(it)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Loading,
        )

    fun addMyModel(name: String) {
        viewModelScope.launch {
            addModelUseCase(name)
        }
    }
}
