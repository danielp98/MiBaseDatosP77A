package net.ivanvega.mibasedatosp77a;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class Actualizar extends AppCompatActivity implements View.OnClickListener {

    Contacto contacto;
    EditText txtUsuario;
    EditText txtEmail;
    EditText txtTel;
    EditText txtfecNac;
    private int dia, mes, año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        contacto = (Contacto)getIntent().getExtras().getSerializable("contacto");

        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtUsuario.setText(contacto.getUsuario().toString());
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtEmail.setText(contacto.getEmail().toString());
        txtTel = (EditText)findViewById(R.id.txtTel);
        txtTel.setText(contacto.getTel().toString());
        txtfecNac = (EditText)findViewById(R.id.txtfecNac);
        txtfecNac.setText(contacto.getFecNac().toString());

        Button btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(this);

        Button btnfecNac = (Button)findViewById(R.id.btnfecNac);
        btnfecNac.setOnClickListener(this);

        Button btnRegresar = (Button)findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        contacto.setUsuario(txtUsuario.getText().toString());
        contacto.setEmail(txtEmail.getText().toString());
        contacto.setTel(txtTel.getText().toString());
        contacto.setFecNac(txtfecNac.getText().toString());

        DAOContactos dao = new DAOContactos(this);

        switch (view.getId()){
            case R.id.btnActualizar:
                dao.update(contacto);
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
                        txtfecNac.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
