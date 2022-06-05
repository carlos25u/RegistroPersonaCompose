package com.call.registropersonacompose.ui.theme.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.registropersonacompose.view.OcupacionViewModel
import com.call.registropersonacompose.view.PersonaViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ListadoPersonasScreen(goRegistroPersonas:() -> Unit, goListaOcupaciones:() -> Unit,
    viewModel: PersonaViewModel = hiltViewModel()){

    val ScaffoldState = rememberScaffoldState()

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Personas") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    goRegistroPersonas()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Nuevo")
            }
        },
        scaffoldState = ScaffoldState

    ) {it
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(onClick = {
                goListaOcupaciones()
            }) {
                Text(text = "Ocupaciones")
            }

            val listaPersonas = viewModel.personas.collectAsState(initial = emptyList())
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                items(listaPersonas.value) { persons ->
                    Row() {
                        Text(text = "${persons.personaId}\t\t\t " +
                                "${persons.nombres}\t\t\t " +
                                "${persons.email}\t\t\t " +
                                "${persons.salario}")
                    }
                }
            }
        }
    }
}

