package com.example.samplepayment.ui.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EasyTransferViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Transfer Fragment"
    }
    val text: LiveData<String> = _text
}