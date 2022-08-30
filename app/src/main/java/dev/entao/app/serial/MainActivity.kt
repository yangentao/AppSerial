package dev.entao.app.serial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.FileDescriptor

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        test()
    }

    fun test() {
        val a = FileDescriptor()
        Log.d("serial", "111111")
        val f = a::class.java.fields.firstOrNull { it.name == "descriptor" }
        Log.d("serial", "22222")
        f?.isAccessible = true
        Log.d("serial", " ${f?.get(a)?.toString() ?: "null"}")
        f?.set(a, -10)
        Log.d("serial", " ${f?.get(a)?.toString() ?: "null"}")
    }

}