package com.example.minutnik_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class fragmentMinutnik : Fragment() {
    private var timer: Timer? = null
    private var czasStartu: Long = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_minutnik, container, false)

        val minutnikTextView = view.findViewById<TextView>(R.id.tvMinutnikCzas)
        val buttonStart = view.findViewById<Button>(R.id.buttonStart)
        val buttonStop = view.findViewById<Button>(R.id.buttonStop)
        val buttonPauza = view.findViewById<Button>(R.id.buttonPauza)
        val konfiguracja = view.findViewById<Button>(R.id.buttonKonfiguracja)
        val buttonDodajSekundaJednosci = view.findViewById<Button>(R.id.buttonDodajSekundaJednosci)
        val buttonDodajSekundaDziesiatek = view.findViewById<Button>(R.id.buttonDodajSekundaDziesatek)
        val buttonOdejmijSekundaJednosci = view.findViewById<Button>(R.id.odejmijSekundeJednosci)
        val buttonOdejmijSekundaDziesiatek = view.findViewById<Button>(R.id.odejmijSekundeDziesiatki)
        val buttonDodajMinutaJednosci = view.findViewById<Button>(R.id.buttonDodajMinutaJednosci)
        val buttonDodajMinutaDziesiatek = view.findViewById<Button>(R.id.buttondodajMinutaDziesiatek)
        val buttonOdejmijMinutaJednosci = view.findViewById<Button>(R.id.buttonOdejmijMinuteJednostek)
        val buttonOdejmijMinutaDziesiatek = view.findViewById<Button>(R.id.buttonOdejmijMinuteDziesiatek)

        val minuty = arguments?.getInt("minuty") ?: 0
        val sekundy = arguments?.getInt("sekundy") ?: 0

        czasStartu = (minuty * 60 + sekundy) * 1000L
        timer = Timer(requireContext(), czasStartu, onTick = { czas -> minutnikTextView.text = czas })

        minutnikTextView.text = timer?.formatTime(czasStartu)

        buttonStart.setOnClickListener {
            timer?.startMinutnik()
        }

        buttonStop.setOnClickListener {
            timer?.stop()
            minutnikTextView.text = timer?.formatTime(czasStartu)
        }

        buttonPauza.setOnClickListener {
            timer?.pauza()
        }

        buttonDodajSekundaJednosci.setOnClickListener{
            timer?.dodajSekundaJednosci()
        }

        buttonDodajSekundaDziesiatek.setOnClickListener {
            timer?.dodajSekundaDziesiatek()
        }

        buttonOdejmijSekundaJednosci.setOnClickListener {
            timer?.odejmijSekundaJednosci()
        }

        buttonOdejmijSekundaDziesiatek.setOnClickListener {
            timer?.odejmijSekundaDziesiatek()
        }

        buttonDodajMinutaJednosci.setOnClickListener{
            timer?.dodajMinutyJednosci()
        }

        buttonDodajMinutaDziesiatek.setOnClickListener {
            timer?.dodajMinutyDziesiatki()
        }

        buttonOdejmijMinutaJednosci.setOnClickListener {
            timer?.odejmijMinutaJednosci()
        }

        buttonOdejmijMinutaDziesiatek.setOnClickListener {
            timer?.odejmijMinutaDziesiatek()
        }

        konfiguracja.setOnClickListener {
            (activity as? FragmentNavigation)?.navigateToFragment(fragmentKonfiguracja())
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.stop()
    }
}
