package org.bedu.v2_tiendabedu.activities.user;

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

import org.bedu.v2_tiendabedu.R;


/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link EditNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNameFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText editText;

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditNameDialogListener {
        void onFinishEditDialogN(String inputText);
    }

    public EditNameFragment(){ }

    public static EditNameFragment newInstance(String title) {
        EditNameFragment frag = new EditNameFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_name, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Get field and buttons from view
        editText = view.findViewById(R.id.editNameField);
        Button cancelButton = view.findViewById(R.id.cancelButton);
        Button acceptButton = view.findViewById(R.id.acceptButton);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Ingrese su nombre");
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        cancelButton.setOnClickListener(v -> dismiss());
        acceptButton.setOnClickListener(v -> {
            if(editText.getText().toString().equals("")){
                Toast.makeText(getContext(), "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
            } else{
                EditNameDialogListener listener = (EditNameDialogListener) getActivity();
                listener.onFinishEditDialogN(editText.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            listener.onFinishEditDialogN(editText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }
}