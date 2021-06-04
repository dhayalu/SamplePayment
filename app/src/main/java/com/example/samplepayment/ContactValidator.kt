package com.example.samplepayment

import com.example.samplepayment.model.Contact

class ContactValidator {

    fun validateSingleContact(contact: Contact) : Boolean {
        return (contact != null)
    }

    fun validateAllContactLists(arrayContact: ArrayList<Contact>) : Boolean {
        return (arrayContact.size > 0)
    }

    fun validateMobileNumber(number : String) : Boolean {
        return ((number.length > 9 && number.length <= 15) && number.startsWith("+1"))
    }

    fun validateName(name : String) : Boolean {
        return ((!name.isEmpty() ||  name != null))
    }

    fun validateEmail(email : String) : Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return (email.matches(emailPattern.toRegex()))
    }
}