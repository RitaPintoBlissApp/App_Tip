package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    //lateinit é nova. Ela é uma promessa de que seu código vai inicializar a variável antes de usá-la. Se você não fizer isso, o app falhará.
    lateinit var binding: ActivityMainBinding //Esta linha declara uma variável de nível superior na classe para o objeto de vinculação. Ela é definida nesse nível porque será usada em vários métodos da classe MainActivity.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //Essa linha inicializa o objeto binding que você usará para acessar as Views no layout activity_main.xml.
        setContentView(binding.root) //esse código especifica a raiz da hierarquia de visualizações no app

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    fun calculateTip() { //método auxiliar
        //custo do serviço
        val stringInTextField = binding.costOfService.text.toString() //acesse o texto do custo do serviço
        val cost = stringInTextField.toDouble()//Depois, converta o texto em número decimal
        //percentual da gorjeta
        val selectedId = binding.tipOptions.checkedRadioButtonId //vai buscar que radiobotum foi selecionado
        val tipPercentage = when (selectedId) { //qual das percentagens foi selecionado
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        //calcular a gorjeta
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked //erifique o atributo isChecked para ver se a chave está "ativada".
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        //formatar a gorjeta
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        //testtt
    }
}