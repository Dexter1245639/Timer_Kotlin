package com.example.minutnik_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class fragmentKonfiguracja : Fragment() {
    private var minuty = 0
    private var sekundy = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_konfiguracja, container, false)

        val minutyTextView = view.findViewById<TextView>(R.id.tvMinuty)
        val sekundyTextView = view.findViewById<TextView>(R.id.tvSekundy)

        val plusMinuty = view.findViewById<Button>(R.id.plusMinuta)
        val minusMinuty = view.findViewById<Button>(R.id.minusMinuta)
        val plusSekundy = view.findViewById<Button>(R.id.plusSekunda)
        val minusSekundy = view.findViewById<Button>(R.id.minusSekunda)

        val minutnik = view.findViewById<Button>(R.id.minutnik)

        plusMinuty.setOnClickListener {
            minuty++
            minutyTextView.text = minuty.toString()
        }

        minusMinuty.setOnClickListener {
            if (minuty > 0) minuty--
            minutyTextView.text = minuty.toString()
        }

        plusSekundy.setOnClickListener {
            if (sekundy < 59) sekundy++
            sekundyTextView.text = sekundy.toString()
        }

        minusSekundy.setOnClickListener {
            if (sekundy > 0) sekundy--
            sekundyTextView.text = sekundy.toString()
        }

        minutnik.setOnClickListener {
            val fragmentMinutnik = fragmentMinutnik()
            val bundle = Bundle()
            bundle.putInt("minuty", minuty)
            bundle.putInt("sekundy", sekundy)
            fragmentMinutnik.arguments = bundle
            (activity as? FragmentNavigation)?.navigateToFragment(fragmentMinutnik)
        }
        return view
    }
}