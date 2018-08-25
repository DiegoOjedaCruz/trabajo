package diego.app.diego.com.grados

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val grados = arrayOf("farenheit", "celsius", "kelvin")
        val spinner = findViewById<Spinner>(R.id.spinnerGrados)


            val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, grados)
            spinner.adapter = arrayAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (position == 0) {
                        btnFarenheit.setEnabled(false)
                        btnCelsius.setEnabled(true)
                        btnKelvin.setEnabled(true)
                        i=position
                    } else
                        if (position == 1) {
                            btnCelsius.setEnabled(false)
                            btnFarenheit.setEnabled(true)
                            btnKelvin.setEnabled(true)
                            i=position
                        } else {
                            btnKelvin.setEnabled(false)
                            btnFarenheit.setEnabled(true)
                            btnCelsius.setEnabled(true)
                            i=position
                        }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }



    fun calcular(view: View?) {
        val numero = eTextNumber.text.toString()
        var result = 0.0
        if (validation(numero)) {
            val number = numero.toDouble()
            when(view!!.id){
                R.id.btnKelvin->{
                    if(i==0){
                        result =(number+456.67)*(0.56)
                    }else
                        result=number+273.15
                }
                R.id.btnFarenheit->{
                    if(i==1){
                        result=(number*1.8)+32
                    }else
                        result = (1.8*(number-273))+32
                }
                R.id.btnCelsius->{
                    if(i==2){
                        result=number-273.15
                    }else
                        result=(5*(number-32))/9
                }

            }
            txvResult.text = "el resultado es $result"


        }
    }

    private fun validation(strNumber: String): Boolean {
        if (strNumber.isEmpty()) {
            eTextNumber.setError(getText(R.string.required_field))
            return false
        } else
            return true
    }
}
