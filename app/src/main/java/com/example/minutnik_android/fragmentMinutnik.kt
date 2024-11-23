package com.example.minutnik_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class fragmentMinutnik : Fragment() {
    private var minutnik: CountDownTimer? = null
    private var isRunning = false
    private var pozostalyCzas: Long = 0L
    private var czasStartu: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_minutnik, container, false)

        val minutnikTextView = view.findViewById<TextView>(R.id.tvMinutnikCzas)
        val buttonStart = view.findViewById<Button>(R.id.buttonStart)
        val buttonStop = view.findViewById<Button>(R.id.buttonStop)
        val buttonPauza = view.findViewById<Button>(R.id.buttonPauza)
        val konfiguracja = view.findViewById<Button>(R.id.buttonKonfiguracja)

        val minuty = arguments?.getInt("minuty") ?: 0
        val sekundy = arguments?.getInt("sekundy") ?: 0

        czasStartu = (minuty * 60 + sekundy) * 1000L
        pozostalyCzas = czasStartu
        minutnikTextView.text = formatTime(pozostalyCzas)

        buttonStart.setOnClickListener {
            if (!isRunning) {
                startMinutnik(pozostalyCzas, minutnikTextView)
            }
        }

        buttonStop.setOnClickListener {
            minutnik?.cancel()
            isRunning = false
            pozostalyCzas = 0L
            minutnikTextView.text = formatTime(pozostalyCzas)
        }

        buttonPauza.setOnClickListener {
            if (isRunning) {
                minutnik?.cancel()
                isRunning = false
            }
        }

        konfiguracja.setOnClickListener {
            (activity as? FragmentNavigation)?.navigateToFragment(fragmentKonfiguracja())
        }

        return view
    }

    private fun startMinutnik(czasMilisekundy: Long, minutnikTextView: TextView) {
        minutnik = object : CountDownTimer(czasMilisekundy, 1000) {
            override fun onTick(pozostalyCzasMS: Long) {
                pozostalyCzas = pozostalyCzasMS
                minutnikTextView.text = formatTime(pozostalyCzas)
            }

            override fun onFinish() {
                isRunning = false
                pozostalyCzas = 0L
                minutnikTextView.text = formatTime(pozostalyCzas)
            }
        }.start()
        isRunning = true
    }

    private fun formatTime(czasMilisekundy: Long): String {
        val minuty = (czasMilisekundy/1000)/60
        val sekundy = (czasMilisekundy/1000)%60
        return String.format("%02d:%02d", minuty, sekundy)
    }
}