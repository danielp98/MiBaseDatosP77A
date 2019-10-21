package net.ivanvega.mibasedatosp77a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnCreateContextMenuListener {
    ListView lv;
    Button boton;
    Button botonRegresar;
    Button botonInsertar;
    ArrayAdapter<Contacto> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAOContactos dao = new DAOContactos(this);
        //dao.insert(new Contacto(0, "perronegro",
        //        "perronegro@","445"));
        //dao.insert(new Contacto(0, "perroblanco",
        //        "perroblanco@","544"));
        // for (Contacto c : dao.getAll()){
        //     Toast.makeText(this,
        //             c.usuario,
        //             Toast.LENGTH_SHORT).show();
        // }

         lv = findViewById(R.id.lv);

        //SimpleCursorAdapter adp =
        //        new SimpleCursorAdapter(
        //                this,
        //                android.R.layout.simple_list_item_2,
        //                dao.getAllCursor(),
        //                new String[]{"usuario", "email"},
        //                new int[]{android.R.id.text1, android.R.id.text2
        //                },
        //                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE
        //
        //        );
        //lv.setAdapter(adp);

        //onClick(lv);

        String[] usuario = {""};

        ArrayList<Contacto> contactos = dao.buscarporNombre(usuario);

        adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);

        lv.setAdapter(adapter);

        boton = (Button) findViewById(R.id.btnBuscar);

        boton.setOnClickListener(this);

        botonRegresar = (Button) findViewById(R.id.btn_Regresar);

        botonRegresar.setOnClickListener(this);

        botonRegresar.setVisibility(View.INVISIBLE);

        botonInsertar = (Button) findViewById(R.id.btnInsertar);

        botonInsertar.setOnClickListener(this);

        registerForContextMenu(lv);



        /////////////////////////////////////////////////////////////////////////////////////
        /*findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Insertar.class);
                startActivityForResult(intent,0);
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();

        DAOContactos dao1 = new DAOContactos(this);

        String[] usuario1 = {""};

        ArrayList<Contacto> contactos = dao1.buscarporNombre(usuario1);

        adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);

        lv.setAdapter(adapter);

    }

    @Override
    public void onClick(View view){

        switch (view.getId()){

            case R.id.btnBuscar:
                EditText text = (EditText)findViewById(R.id.txtUsuario);
                String[] usuario = {text.getText().toString()};
                //String[] usuarios = {};

                DAOContactos dao = new DAOContactos(this);

                //Cursor contactos = dao.buscarporNombre(usuario);

                ArrayList<Contacto> contactos = dao.buscarporNombre(usuario);

                adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);

                //lv = findViewById(R.id.lv);

                //SimpleCursorAdapter adp = new SimpleCursorAdapter(this,
                //        android.R.layout.simple_list_item_2,
                //        contactos,
                //        new String[]{"usuario", "email"},
                //        new int[]{android.R.id.text1, android.R.id.text2},
                //        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

                //lv.removeAllViews();
                lv.setAdapter(adapter);
                botonRegresar.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_Regresar:
                text = (EditText)findViewById(R.id.txtUsuario);
                text.setText("");

                DAOContactos dao1 = new DAOContactos(this);

                String[] usuario1 = {""};

                contactos = dao1.buscarporNombre(usuario1);

                adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);

                lv.setAdapter(adapter);
                botonRegresar.setVisibility(View.INVISIBLE);
                break;
            case R.id.btnInsertar:
                Intent intent2 = new Intent(MainActivity.this, Insertar.class);
                startActivity(intent2);
                break;

        }

    }

    public void onClickRegresar(View view){

        //DAOContactos dao = new DAOContactos(this);

        //lv = findViewById(R.id.lv);

        //String[] usuario = {""};

        //ArrayList<Contacto> contactos = dao.buscarporNombre(usuario);

        //ArrayAdapter<Contacto> adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);

        //lv.setAdapter(adapter);

        EditText text = (EditText)findViewById(R.id.txtUsuario);
        text.setText("");
        //onClick(lv);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View lv, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, lv, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item){

        DAOContactos dao = new DAOContactos(this);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //lv.getAdapter().getItem(info.position);

        lv = findViewById(R.id.lv);

        Contacto contacto = (Contacto) lv.getItemAtPosition(info.position);

        switch (item.getItemId()) {
            case R.id.eliminar:
                dao.eliminar(contacto.getId());
                //onClick(lv);

                EditText text = (EditText)findViewById(R.id.txtUsuario);
                String[] usuario = {text.getText().toString()};
                //String[] usuarios = {};

                //DAOContactos dao = new DAOContactos(this);

                //Cursor contactos = dao.buscarporNombre(usuario);

                ArrayList<Contacto> contactos = dao.buscarporNombre(usuario);

                adapter = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, contactos);

                //lv = findViewById(R.id.lv);

                //SimpleCursorAdapter adp = new SimpleCursorAdapter(this,
                //        android.R.layout.simple_list_item_2,
                //        contactos,
                //        new String[]{"usuario", "email"},
                //        new int[]{android.R.id.text1, android.R.id.text2},
                //        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

                //lv.removeAllViews();
                lv.setAdapter(adapter);

                //dao._sqLiteDatabase.execSQL("DELETE FROM " + MiDB.TABLE_NAME_CONTACTOS + " where _id="+contacto.getId());
                return true;
            case R.id.actualizar:
                Intent intent = new Intent(MainActivity.this, Actualizar.class);
                intent.putExtra("contacto", contacto);
                startActivity(intent);
            default:
                return super.onContextItemSelected(item);
        }
    }

}
