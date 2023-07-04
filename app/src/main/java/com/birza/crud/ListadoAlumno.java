package com.birza.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.birza.crud.DAO.AlumnoDAO;
import com.birza.crud.Entity.Alumno;
import com.birza.crud.databinding.ActivityListadoAlumnoBinding;

public class ListadoAlumno extends AppCompatActivity {

    ActivityListadoAlumnoBinding binding;
    AlumnoDAO dao;


    void MostrarAlumnos(){
        dao = new AlumnoDAO();

        //simple_list_item_checked => forma de sheck
        // simple_list_item_single_choice => radio button

        ArrayAdapter<String> adp = new ArrayAdapter<String>(
                ListadoAlumno.this,
                android.R.layout.simple_list_item_1, // El más básico para ListView
                dao.ListarAlumnosString()
        );
        // modo de selección de elementos en el listview
        // CHOICE_MODE_SINGLE => selecciona 1
        // CHOICE_MODE_MULTIPLE => más de 1
        binding.lvAlumnos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        binding.lvAlumnos.setAdapter(adp);
        dao = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        binding = ActivityListadoAlumnoBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);
        //
        MostrarAlumnos();
        //
        // establecemos los eventos
        // OnItemClickListener //setOnItemSelectedListener ( este es para spinner nada más)
        // pulsas un ratito
        binding.lvAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                dao = new AlumnoDAO();
                int mi_codigo = dao.ListarAlumnos().get(position).getCodigo();
                dao = null;
                */
                Intent x = new Intent(ListadoAlumno.this, ActualizarAlumno.class);
                x.putExtra("Indice", position);
                startActivity(x);
                Toast.makeText(ListadoAlumno.this, "Indice: " + position, Toast.LENGTH_SHORT).show();

            }
        });

        // pulsas un buen rato 1 segundo
        //setOnItemLongClickListener
        binding.lvAlumnos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // cuadro de dialogo para confrimar la eliminación
                AlertDialog.Builder dialog =  new AlertDialog.Builder(ListadoAlumno.this);

                //establecer el titulo, mensaje.
                dialog.setTitle("Eliminación de Alumno")
                        .setMessage("Estás seguro de Eliminar al Alumno?")
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setCancelable(false); // no puede ser cancelado hasta escoger un boton
                // establecer los botones para el SI y para el NO
                dialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao = new AlumnoDAO();

                        int micodigo = dao.ListarAlumnos().get(position).getCodigo();

                        String mensaje = dao.EliminarAlumno(micodigo);

                        Toast.makeText(ListadoAlumno.this, mensaje, Toast.LENGTH_SHORT).show();
                        MostrarAlumnos();
                        //onResume();
                        dao = null;

                    }
                });
                //
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cerrar la ventana de dialogo
                        dialog.dismiss();
                    }
                });
                //
                dialog.setNeutralButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent x = new Intent(ListadoAlumno.this, ActualizarAlumno.class);
                        x.putExtra("Indice", position);
                        startActivity(x);
                        Toast.makeText(ListadoAlumno.this, "Indice: " + position, Toast.LENGTH_SHORT).show();
                    }
                });

                // Mostrar el cuadro de dialogo
                dialog.create().show();

                //
                return true; // android no continuará ejecutando los otros eventos.

                //return false; // android continuará ejecutando cualquier evento del listview
            }
        });


    } // fin del onCreate

    // metodo del sistema onResume (ciclo de vida de los activity)
    @Override
    protected void onResume() {
        super.onResume();
        // llamar al método MostrarAlumnos
        MostrarAlumnos();
    }


}