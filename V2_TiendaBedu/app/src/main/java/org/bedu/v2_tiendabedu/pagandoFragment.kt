package org.bedu.v2_tiendabedu

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.DialogFragment


class PagandoFragment : DialogFragment(),
    OnEditorActionListener {
    //private var editText: EditText? = null

    // 1. Defines the listener interface with a method passing back data result.
    interface EditNameDialogListener {
        fun onFinishEditDialogN(inputText: String?)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagando, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            dismiss()
        }
        val image = view.findViewById<ImageView>(R.id.cargandoImage)
        val animation = AnimationUtils.loadAnimation(context, R.anim.rotate)
        image.startAnimation(animation)
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
          //  val listener = activity as EditNameDialogListener?
            //listener!!.onFinishEditDialogN(editText!!.text.toString())
            //dismiss()
            return true
        }
        return false
    }

    companion object {
        fun newInstance(title: String?): PagandoFragment {
            val frag = PagandoFragment()
            val args = Bundle()
            args.putString("title", title)
            frag.arguments = args
            return frag
        }
    }
}