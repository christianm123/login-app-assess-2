package com.example.loginappassess2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val typeTextView = findViewById<TextView>(R.id.typeTextView)
        val ownerTextView = findViewById<TextView>(R.id.ownerTextView)

        // Get data from intent
        val entityId = intent.getStringExtra("ENTITY_ID")
        val entityName = intent.getStringExtra("ENTITY_NAME")
        val entityType = intent.getStringExtra("ENTITY_TYPE")
        val entityOwner = intent.getStringExtra("ENTITY_OWNER")

        // Display data
        nameTextView.text = entityName
        typeTextView.text = entityType
        ownerTextView.text = entityOwner
    }
}
