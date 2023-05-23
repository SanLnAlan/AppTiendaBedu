package org.bedu.v2_tiendabedu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditEmailFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText editText;

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditEmailDialogListener {
        void onFinishEditDialogE(String inputText);
    }

    public EditEmailFragment(){ }

    public static EditEmailFragment newInstance(String title) {
        EditEmailFragment frag = new EditEmailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_email, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Get field and buttons from view
        editText = view.findViewById(R.id.editEmailField);
        Button cancelButton = view.findViewById(R.id.cancelButtonE);
        Button acceptButton = view.findViewById(R.id.acceptButtonE);
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        cancelButton.setOnClickListener(v -> dismiss());
        acceptButton.setOnClickListener(v -> {
            if(editText.getText().toString().equals("")) {
                Toast.makeText(getContext(), "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
            } else {
                EditEmailFragment.EditEmailDialogListener listener = (EditEmailFragment.EditEmailDialogListener) getActivity();
                listener.onFinishEditDialogE(editText.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            EditEmailFragment.EditEmailDialogListener listener = (EditEmailFragment.EditEmailDialogListener) getActivity();
            listener.onFinishEditDialogE(editText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }
}