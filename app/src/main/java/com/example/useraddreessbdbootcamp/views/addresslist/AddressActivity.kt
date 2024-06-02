package com.example.useraddreessbdbootcamp.views.addresslist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.useraddreessbdbootcamp.R
import com.example.useraddreessbdbootcamp.database.AppDataBase
import com.example.useraddreessbdbootcamp.repository.MainRepository
import com.example.useraddreessbdbootcamp.viewmodels.address.AddressViewModel
import com.example.useraddreessbdbootcamp.viewmodels.address.AddressViewModelFactory

class AddressActivity : AppCompatActivity() {

    private lateinit var viewModel: AddressViewModel
    private var userId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_address)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener el ID del usuario desde el Intent
        userId = intent.getLongExtra("USER_ID", -1L)

        // Inicializar la base de datos y el repositorio
        val database = AppDataBase.getDatabase(application)
        val repository = MainRepository(database.userDao(), database.addressDao())
        val factory = AddressViewModelFactory(application, repository)

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]

        // Obtener la lista de direcciones para el usuario
        viewModel.getAddressForUser(userId)

        // Configurar observadores, adaptadores, etc.
    }
}
