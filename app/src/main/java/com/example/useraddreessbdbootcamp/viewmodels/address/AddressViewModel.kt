package com.example.useraddreessbdbootcamp.viewmodels.address

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.useraddreessbdbootcamp.entities.Address
import com.example.useraddreessbdbootcamp.repository.MainRepository
import kotlinx.coroutines.launch

class AddressViewModel(
    application: Application,
    private var repository: MainRepository
) : AndroidViewModel(application) {

    private val _addressList = MutableLiveData<MutableList<Address>>()
    val addressList: LiveData<MutableList<Address>>
        get() = _addressList

    fun getAddressForUser(userId: Long) {
        viewModelScope.launch {
            val addressList = repository.getAdresessForUser(userId)
            _addressList.value = addressList
        }
    }

    fun insertAddress(address: Address, userId: Long) = viewModelScope.launch {
        repository.insertAddresess(address)
        val addressList = repository.getAdresessForUser(userId)
        _addressList.value = addressList
    }

    // Función para actualizar una dirección
    fun updateAddress(address: Address, userId: Long) = viewModelScope.launch {
        repository.updateAddress(address)
        val addressList = repository.getAdresessForUser(userId)
        _addressList.value = addressList
    }

    // Función para eliminar una dirección
    fun deleteAddress(address: Address, userId: Long) = viewModelScope.launch {
        repository.deleteAddress(address)
        val addressList = repository.getAdresessForUser(userId)
        _addressList.value = addressList
    }
}
