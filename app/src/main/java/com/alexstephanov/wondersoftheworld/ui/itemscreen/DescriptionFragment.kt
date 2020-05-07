package com.alexstephanov.wondersoftheworld.ui.itemscreen

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.alexstephanov.wondersoftheworld.R
import com.alexstephanov.wondersoftheworld.listeners.OnFragmentEventListener
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_preview_1.*
import java.util.*

class DescriptionFragment(private var listener: OnFragmentEventListener? = null, val description: String): Fragment(), TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnFragmentEventListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description_detailed.text = description

        textToSpeech = TextToSpeech(context, this)
        button_start_text_to_speech.setOnClickListener {
            speakOut(description)
            button_stop_text_to_speech.visibility = View.VISIBLE
            button_stop_text_to_speech.isClickable = true
            button_start_text_to_speech.isClickable = false
        }
        button_stop_text_to_speech.setOnClickListener {
            if(textToSpeech!!.isSpeaking) {
                textToSpeech?.stop()
                button_stop_text_to_speech.visibility = View.INVISIBLE
                button_stop_text_to_speech.isClickable = false
                button_start_text_to_speech.isClickable = true
            }
        }

        frame_layout_description.setOnClickListener{
            listener?.onBackgroundClickEvent()
        }
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            if (textToSpeech?.isLanguageAvailable(Locale(Locale.getDefault().language))
                == TextToSpeech.LANG_AVAILABLE
            ) {
                textToSpeech?.language = Locale(Locale.getDefault().language)
            } else {
                textToSpeech?.language = Locale.US
            }
            textToSpeech?.setPitch(1f)
            textToSpeech?.setSpeechRate(1.1f)
        } else if(status == TextToSpeech.ERROR) {
            Toast.makeText(context, "Произошла ошибка", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speakOut(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        if(textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }

}