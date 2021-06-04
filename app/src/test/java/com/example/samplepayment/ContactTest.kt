package com.example.samplepayment
import com.example.samplepayment.model.Contact
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ContactTest {
    private fun getSingleContact() : Contact {

        val contact  = Contact()
        contact.name = "Jayant Tiwari"
        contact.number = "+19867608869"
        contact.email = "jayjt1@yahoo.in"
        return contact
    }

    private fun getAllContact() : ArrayList<Contact> {

        val contactLists = ArrayList<Contact>()

        val contact1  = Contact()
        contact1.name = "Jayant Tiwari"
        contact1.number = "+19867608869"
        contact1.email = "amitt.jt@gmail.com"

        val contact2  = Contact()
        contact2.name = "Gowtham Nallasivam"
        contact2.number = "+19896797979"
        contact2.email = "amitt.jt@gmail.com"

        contactLists.add(contact1)
        contactLists.add(contact2)

        return contactLists
    }

    @Test
    fun testSingleContact() {

        val contact = getSingleContact()

        val contactValidator = ContactValidator()
        val validateNull = contactValidator.validateSingleContact(contact)

        val mobileNumber = contact.number!!
        val validateMobile = contactValidator.validateMobileNumber(mobileNumber)

        val name = contact.name!!
        val validateName = contactValidator.validateName(name)

        val email = contact.email!!
        val validateEmail = contactValidator.validateEmail(email)

        assertEquals(validateNull, true)
        assertEquals(validateMobile, true)
        assertEquals(validateName, true)
        assertEquals(validateEmail, true)
    }

    @Test
    fun testAllContacts() {

        val contactLists = getAllContact()

        val contactValidator = ContactValidator()
        val validateSize = contactValidator.validateAllContactLists(contactLists)

        val mobileNumber = contactLists[1].number!!
        val validateMobile = contactValidator.validateMobileNumber(mobileNumber)

        val name = contactLists[1].name!!
        val validateName = contactValidator.validateName(name)

        val email = contactLists[1].email!!
        val validateEmail = contactValidator.validateEmail(email)

        assertEquals(validateSize, true)
        assertEquals(validateMobile, true)
        assertEquals(validateName, true)
        assertEquals(validateEmail, true)

    }

}