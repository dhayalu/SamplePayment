package com.example.samplepayment.ui.transfer

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplepayment.HomeActivity
import com.example.samplepayment.R
import com.example.samplepayment.adapter.ContactAdapter
import com.example.samplepayment.model.Contact
import kotlinx.android.synthetic.main.fragment_contact_list.*


class ContactListFragment : Fragment() {

    var listContacts = ArrayList<Contact>()
    var adapter: ContactAdapter? = null
    var cursor: Cursor? = null
    var name: String? = null
    var phonenumber:String? = null
    val RequestPermissionCode = 1
    var sharedPreferences : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_view?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler_view?.hasFixedSize()

        sharedPreferences = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        enableRuntimePermission()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.equals("")){
                    adapter!!.filterList(listContacts)
                }else{
                    filter(newText!!)
                }

                return false
            }
        })
    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredData = ArrayList < Contact > ()
        //looping through existing elements and adding the element to filtered list
        listContacts.filterTo(filteredData) {
            //if the existing elements contains the search input
            it.name?.lowercase()!!.contains(text.lowercase()) || it.number!!.contains(text)
        }
        //calling a method of the adapter class and passing the filtered list
        adapter!!.filterList(filteredData)
    }

    private fun enableRuntimePermission() {

        val permission = ActivityCompat.checkSelfPermission(
            (activity as HomeActivity),
            Manifest.permission.READ_CONTACTS)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            println("permission that already got")
            getContactList()
            Toast.makeText(
                requireContext(),
                "CONTACTS permission allows us to Access CONTACTS app",
                Toast.LENGTH_LONG
            ).show()
        } else {
            println("need to get permission")
            ActivityCompat.requestPermissions(
                (activity as HomeActivity), arrayOf(
                    Manifest.permission.READ_CONTACTS
                ), RequestPermissionCode
            )
        }
    }

    fun getContactList(){

        listContacts = ArrayList<Contact>()
        cursor = (activity as HomeActivity).contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val emails = (activity as HomeActivity).contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, null, null, null)
        while (emails!!.moveToNext()) {
            val email: String = emails!!.getString(emails!!.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
            Log.d("MailId","---------------------->${email }")
            val contacts = Contact(email,"")
            listContacts.add(contacts)
        }
        emails.close()
        while (cursor!!.moveToNext()) {
            name =
                cursor!!.getString(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME ))
            phonenumber =
                cursor!!.getString(cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contact = Contact(name = name!!, phonenumber!!)
            listContacts.add(contact)
        }
        cursor!!.close()
        adapter = ContactAdapter(listContacts)
        recycler_view?.adapter = adapter
    }

    // callback methods

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            RequestPermissionCode -> if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    requireContext(),
                    "Permission Granted, Now your application can access CONTACTS.",
                    Toast.LENGTH_LONG
                ).show()

                val editor = sharedPreferences!!.edit()
                editor.putBoolean("allowed", true)
                editor.apply()
                getContactList()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permission Canceled, Now your application cannot access CONTACTS.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}