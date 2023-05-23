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

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link EditLastnameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditLastnameFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText editText;

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditLastnameDialogListener {
        void onFinishEditDialogLn(String inputText);
    }

    public EditLastnameFragment(){ }

    public static EditLastnameFragment newInstance(String title) {
        EditLastnameFragment frag = new EditLastnameFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_lastname, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Get field and buttons from view
        editText = view.findViewById(R.id.editLastnameField);
        Button cancelButton = view.findViewById(R.id.cancelButtonLn);
        Button acceptButton = view.findViewById(R.id.acceptButtonLn);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Ingrese su nombre");
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        cancelButton.setOnClickListener(v -> dismiss());
        acceptButton.setOnClickListener(v -> {
            if(editText.getText().toString().equals("")){
                Toast.makeText(getContext(), "El campo no puede estar vacio", Toast.LENGTH_SHORT).show();
            } else {
                EditLastnameFragment.EditLastnameDialogListener listener = (EditLastnameFragment.EditLastnameDialogListener) getActivity();
                listener.onFinishEditDialogLn(editText.getText().toString());
                dismiss();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            EditLastnameFragment.EditLastnameDialogListener listener = (EditLastnameFragment.EditLastnameDialogListener) getActivity();
            listener.onFinishEditDialogLn(editText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }
}