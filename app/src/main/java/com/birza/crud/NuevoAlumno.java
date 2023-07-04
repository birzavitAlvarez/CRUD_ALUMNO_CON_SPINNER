package com.birza.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.birza.crud.DAO.AlumnoDAO;
import com.birza.crud.Entity.Alumno;
import com.birza.crud.databinding.ActivityNuevoAlumnoBinding;
import com.google.android.material.snackbar.Snackbar;

public class NuevoAlumno extends AppCompatActivity {

    // view Binding
    ActivityNuevoAlumnoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enlace tradicional
        //setContentView(R.layout.activity_nuevo_alumno);

        // enlace view Binding
        binding = ActivityNuevoAlumnoBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);

        binding.edtCodigo.requestFocus();

        // Poblar el spinner de distrito con el vector vDistritos llenar la data
        // simple_spinner_dropdown_item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                NuevoAlumno.this,
                android.R.layout.simple_list_item_1,
                AlumnoDAO.vDistritos
        );
        // enlazar el adapter con el control de lista
        binding.spinnerDistrito.setAdapter(adapter);

        // static para buscar 2da forma
        binding.spinnerDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nombre = parent.getSelectedItem().toString();
                Toast.makeText(NuevoAlumno.this, "Seleccionaste: " + nombre, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtCodigo.setText("");
                binding.edtNombre.setText("");
                binding.edtPension.setText("");
                binding.edtCelular.setText("");
                binding.spinnerDistrito.setSelection(0);
                binding.edtCodigo.requestFocus();
            }
        });


        // para utilizar algun control del layout
        binding.btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NuevoAlumno.this,
                        ListadoAlumno.class);
                //
                startActivity(i);
            }
        });

        binding.btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // crear un objeto Alumno
                Alumno obj = new Alumno(
                        Integer.parseInt(binding.edtCodigo.getText()+""),
                        binding.edtNombre.getText().toString(),
                        Double.parseDouble(binding.edtPension.getText()+""),
                        binding.edtCelular.getText().toString(),
                        binding.spinnerDistrito.getSelectedItem().toString()
                );
                // DAO
                AlumnoDAO dao = new AlumnoDAO();
                String mensaje = dao.AgregarAlumno(obj);
                dao = null;
                // SnackBar
                Snackbar.make(v, mensaje, Snackbar.LENGTH_INDEFINITE)
                        .setTextColor(Color.YELLOW)
                        .setAction("Aceptar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(NuevoAlumno.this,
                                        mensaje, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(Color.CYAN)
                        .show();

                //Toast.makeText(NuevoAlumnoActivity.this, "mensaje", Toast.LENGTH_SHORT).show();
            }
        });


    }

}