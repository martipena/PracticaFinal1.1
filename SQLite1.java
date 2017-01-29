package com.example.marti.practicafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLite1 extends AppCompatActivity {

    private EditText txtID,txtTipo,txtMarca,txtNom,txtPreu;

    private Button btnIns,btnDel,btnCons,btnAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite1);

        txtID=(EditText)findViewById(R.id.editID);
        txtTipo=(EditText)findViewById(R.id.editTipo);
        txtMarca=(EditText)findViewById(R.id.editMarca);
        txtNom=(EditText)findViewById(R.id.editNom);
        txtPreu=(EditText)findViewById(R.id.editPreu);

        btnIns = (Button)findViewById(R.id.btnInsertar);
        btnAct = (Button)findViewById(R.id.btnActualitzar);
        btnDel = (Button)findViewById(R.id.btnEliminar);
        btnCons = (Button) findViewById(R.id.btnConsultar);

        //Abrimos la base de datos 'DBProductes' en modo escritura
        ProducteSQLiteHelper usdbh =
                new ProducteSQLiteHelper(this, "DBProductes", null, 1);

        final SQLiteDatabase db = usdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
            //Insertamos 5 productes de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
                int id = i;
                String tipo = "" +txtNom;
                String marca = "" +txtMarca;
                String nom = "" +txtNom;
                //double preu = Double.parseDouble(txtPreu.getText().toString());
                int preu = +i;
                //Insertamos los datos en la tabla Productes
                db.execSQL("INSERT INTO Productes (id, tipo, marca, nom, preu) " +
                        "VALUES (" + id + ", '" + tipo +"', '" + marca + "', '" + nom + "', " + preu + ");");
            }

            //Cerramos la base de datos
            //db.close();
        }

        btnIns.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String id = txtID.getText().toString();
                String tipo = txtTipo.getText().toString();
                String marca = txtMarca.getText().toString();
                String nom = txtNom.getText().toString();
                String preu = txtPreu.getText().toString();


                //Alternativa 1: método sqlExec()
                //String sql = "INSERT INTO Productes (id,tipo,marca,nom,preu) VALUES (" + id + ", '" + tipo +"', '" + marca + "', '" + nom + "', " + preu + ");");
                //db.execSQL(sql);

                //Alternativa 2: método insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("id", id);
                nuevoRegistro.put("tipo", tipo);
                nuevoRegistro.put("marca", marca);
                nuevoRegistro.put("nom", nom);
                nuevoRegistro.put("preu", preu);
                db.insert("Productes", null, nuevoRegistro);
            }
        });

        btnAct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String id = txtID.getText().toString();
                String tipo = txtTipo.getText().toString();
                String marca = txtMarca.getText().toString();
                String nom = txtNom.getText().toString();
                String preu = txtPreu.getText().toString();

                /*if (tipo == null || marca == null || nom == null || preu == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Entra tots el valors per poder cambiar", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String sql = "UPDATE Productes SET nom='" + nom + "', tipo='" + tipo + "' WHERE id=" + id;
                    String sql2 = "UPDATE Productes SET marca='" + marca + "', preu='" + preu + "' WHERE id=" + id;
                    db.execSQL(sql);
                    db.execSQL(sql2);
                }*/

                //Alternativa 1: método sqlExec()


                //Alternativa 2: método update()
                ContentValues valores = new ContentValues();
                //valores.put("id", id);
                valores.put("tipo", tipo);
                valores.put("marca", marca);
                valores.put("nom", nom);
                valores.put("preu", preu);
                db.update("Productes", valores, "id=" + id, null);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String id = txtID.getText().toString();
                /*String tipo = txtTipo.getText().toString();
                String marca = txtMarca.getText().toString();
                String nom = txtNom.getText().toString();
                String preu = txtPreu.getText().toString();*/

                //Alternativa 1: método sqlExec()
                //String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: método delete()
                db.delete("Productes", "id=" + id, null);
            }
        });

        btnCons.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Cursor c = db.rawQuery(" SELECT codigo,nombre FROM Usuarios WHERE nombre='usuario3' ", null);

                String[] campos = new String[] {"id", "tipo", "marca", "nom", "preu"};
                String[] args = new String[] {"Producte2"};

                Cursor c = db.query("Productes", campos, "id=?", args, null, null, null);

                //Nos aseguramos de que existe al menos un registro
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String id = c.getString(0);
                        String tipo = c.getString(1);
                        String marca = c.getString(2);
                        String nom = c.getString(3);
                        String preu = c.getString(4);
                    } while(c.moveToNext());
                }
            }
        });
    }
}
