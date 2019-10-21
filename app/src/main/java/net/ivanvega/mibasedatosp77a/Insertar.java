package net.ivanvega.mibasedatosp77a;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Insertar extends AppCompatActivity implements View.OnClickListener{

    Contacto contacto;
    private EditText etUsuario;
    private EditText etEmail;
    private EditText etTel;
    private EditText etfecNac;
    private int dia, mes, año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        //contacto = (Contacto)getIntent().getExtras().getSerializable("contacto");

        etUsuario =  findViewById(R.id.txtUsuario);
        etEmail = findViewById(R.id.txtEmail);
        etTel = findViewById(R.id.txtTel);
        etfecNac = findViewById(R.id.txtfecNac);

        Button btnInsertar = (Button)findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(this);

        Button btnfecNac = (Button)findViewById(R.id.btnfecNac);
        btnfecNac.setOnClickListener(this);

        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        contacto = new Contacto(0, etUsuario.getText().toString(), etEmail.getText().toString(), etTel.getText().toString(), etfecNac.getText().toString());

        DAOContactos dao = new DAOContactos(this);

        switch (view.getId()){
            case R.id.btnInsertar:
                dao.insert(contacto);
                finish();
                break;
            case R.id.btnfecNac:
                Calendar c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                año = c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etfecNac.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }
                , dia, mes, año);
                datePickerDialog.show();
                break;
            case R.id.btnRegresar:
                finish();
        }
    }

}
