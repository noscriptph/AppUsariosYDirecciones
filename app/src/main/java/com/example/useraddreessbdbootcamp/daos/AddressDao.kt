package com.example.useraddreessbdbootcamp.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.useraddreessbdbootcamp.entities.Address

@Dao
interface AddressDao {

    // Método para insertar una dirección en la base de datos
    @Insert
    suspend fun insertAddress(address: Address): Long

    // Método para obtener todas las direcciones de un usuario específico
    @Query("SELECT * FROM addresses WHERE userOwnerId = :userId")
    fun getAddressForUser(userId: Long): MutableList<Address>

    // Método para actualizar una dirección existente
    @Update
    suspend fun updateAddress(address: Address)

    // Método para eliminar una dirección específica
    @Delete
    suspend fun deleteAddress(address: Address)
}
