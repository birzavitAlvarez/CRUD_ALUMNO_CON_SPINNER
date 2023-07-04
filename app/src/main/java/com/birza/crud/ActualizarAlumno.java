package com.birza.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.birza.crud.DAO.AlumnoDAO;
import com.birza.crud.Entity.Alumno;
import com.birza.crud.databinding.ActivityActualizarAlumnoBinding;

public class ActualizarAlumno extends AppCompatActivity {

    ActivityActualizarAlumnoBinding binding;
    AlumnoDAO dao = null;

    void MostrarDistritos(){
        // Poblar el spinner de distrito con el vector vDistritos llenar la data
        // simple_spinner_dropdown_item
        ArrayAdapter<String> adp = new ArrayAdapter<String>(
                ActualizarAlumno.this,
                android.R.layout.simple_list_item_1,
                AlumnoDAO.vDistritos
        );
        //
        binding.spinnerDistritoUpd.setAdapter(adp);
    }

    // 1ra forma para obtener el indice de (position) del distrito
    int buscarIndiceDistrito(String nomdis){
        for (int i = 0; i < AlumnoDAO.vDistritos.length; i++){
            if (AlumnoDAO.vDistritos[i].equals(nomdis)){
                return i; // si existe te lo retorna
            }
        }
        return -1; // si no existe te manda -1
    }

    void MostrarAlumno() {
        // recuperamos el Intent
        Intent i = getIntent();
        if (i!=null){
            int pos = i.getIntExtra("Indice",0);
            // recuperar y mostrar los datos del alumno
            dao = new AlumnoDAO();
            Alumno alu = dao.ListarAlumnos().get(pos);
            binding.edtCodigoUpd.setText(alu.getCodigo()+"");
            binding.edtNombreUpd.setText(alu.getNombre());
            binding.edtPensionUpd.setText(alu.getPension()+"");
            binding.edtCelularUpd.setText(alu.getCelular());
            // seleccionar el distrito en el spinner
            //int idx = buscarIndiceDistrito(alu.getDistrito());
            //binding.spinnerDistritoUpd.setSelection(idx);
            binding.spinnerDistritoUpd.setSelection(buscarIndiceDistrito(alu.getDistrito()));
            dao = null;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        binding = ActivityActualizarAlumnoBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);
        //
        MostrarDistritos();
        //
        MostrarAlumno();
        //
        binding.btnCerrarUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //
        binding.btnActualizarUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno obj = new Alumno(
                        Integer.parseInt(binding.edtCodigoUpd.getText()+""),
                        binding.edtNombreUpd.getText().toString(),
                        Double.parseDouble(binding.edtPensionUpd.getText()+""),
                        binding.edtCelularUpd.getText().toString(),
                        binding.spinnerDistritoUpd.getSelectedItem().toString()
                );
                //
                dao = new AlumnoDAO();
                String mensaje = dao.ActualizarAlumno(obj);
                dao = null;
                Toast.makeText(ActualizarAlumno.this, mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

}