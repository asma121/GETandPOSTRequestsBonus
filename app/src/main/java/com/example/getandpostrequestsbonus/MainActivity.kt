package com.example.getandpostrequestsbonus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etname: EditText
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var textView: TextView
    var userdata:List<UsersDetails.Datum>?=null
    var displayResponse =ArrayList<String>()
    var text=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etname=findViewById(R.id.etname)
        button=findViewById(R.id.button)
        button2=findViewById(R.id.button2)
        textView=findViewById(R.id.textView)

        button.setOnClickListener {
            var f = UsersDetails.Datum(etname.text.toString())
            addUserDetails(f, onResult = {
            })
        }

        button2.setOnClickListener {
            getUserDetails(onResult = {
                userdata = it
                val datumList = userdata
                for (datum in datumList!!) {
                    displayResponse+= arrayListOf("${datum.name}")
                }
            })
            for (i in displayResponse){
                text +=i+"\n"
            }
            textView.text=text
            textView.movementMethod = ScrollingMovementMethod()
        }


    }

    private fun addUserDetails(f :UsersDetails.Datum ,onResult: () -> Unit){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addUserDetails(f)?.enqueue(object :
                Callback<UsersDetails.Datum?> {
                override fun onResponse(call: Call<UsersDetails.Datum?>, response: Response<UsersDetails.Datum?>) {
                    Toast.makeText(applicationContext,"user added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<UsersDetails.Datum?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    private fun getUserDetails(onResult: (List<UsersDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getUserDetails()?.enqueue(object : Callback<List<UsersDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<UsersDetails.Datum>>,
                    response: Response<List<UsersDetails.Datum>>
                ) {
                    onResult(response.body())
                }
                override fun onFailure(call: Call<List<UsersDetails.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }

}