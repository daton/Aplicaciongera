package com.example.aplicaciongera

import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*

class TareaGuardarCliente(private var ctx: Context?,
                          private var activity: MainActivity?)
    : AsyncTask<Void, Void, Void>() {

    var estatus=Estatus()
    var cliente=Cliente()
    override fun onPostExecute(result: Void?) {

        //Invocamos nuestra vistaa del MainActivity
        //  activity?.findViewById<TextView>(R.id.txtActual)?.text=estacion?.temp_c
        Toast.makeText(ctx,estatus.mensaje, Toast.LENGTH_LONG).show()



    }

    override fun onPreExecute() {
        super.onPreExecute()
        //Generamos un usuario
        cliente.ident=   activity?.txtident?.text.toString().toInt()

        cliente.nombre=  activity?.txtnombre?.text.toString()


    }

    override fun doInBackground(vararg p0: Void?): Void? {
        try {

            var retrofit = Retrofit.Builder()
                .baseUrl("https://actuario.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        var servicioCliente=       retrofit.create(ClienteService::class.java)


            cliente.mail="rapid@gmail.com"

            var direccion=Direccion()
            direccion.calle="amargura"
            direccion.cp=777
            direccion.municipio="Ecaterror"
            cliente.direccion=direccion

    var  envio= servicioCliente.guardarCliente(cliente)

      estatus= envio.execute().body()!!

        } catch (t: Throwable) {
print("ERRRORRRRRRRRRRRRR  "+t.message)
        }
        return null

    }


}