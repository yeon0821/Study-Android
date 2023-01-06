package com.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class ltalyScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_italy_screen, container,false)

        view.findViewById<Button>(R.id.to_london_from_italy).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_global_london_screen)
        }

        view.findViewById<Button>(R.id.to_paris_from_italy).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_global_paris_screen)
        }


        return view
    }
}