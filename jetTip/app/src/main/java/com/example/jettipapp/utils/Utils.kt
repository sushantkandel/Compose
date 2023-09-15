package com.example.jettipapp.utils

fun calculateTotalTip(totalBill: Double, tipPercentage: Int): Double {

    return if (totalBill > 1 && totalBill.toString().isNotEmpty()) {
        (totalBill * tipPercentage) / 100
    } else {
        0.00
    }
}

fun calculateTotalBillPerPerson(totalBill: Double, tipPercentage: Int, splitBy: Int): Double {
    val bill = calculateTotalTip(totalBill = totalBill, tipPercentage) + totalBill
    return bill / splitBy
}

