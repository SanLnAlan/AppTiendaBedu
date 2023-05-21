package org.bedu.v2_tiendabedu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import org.w3c.dom.Text;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link EditNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNameFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText editText;
    private Button cancelButton;
    private Button acceptButton;

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditNameDialogListener {
        void onFinishEditDialog(String inputText);
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
        editText = (EditText) view.findViewById(R.id.editNameField);
        cancelButton = (Button) view.findViewById(R.id.cancelButton);
        acceptButton = (Button) view.findViewById(R.id.acceptButton);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Ingrese su nombre");
        // Show soft keyboard automatically and request focus to field
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        // 2. Setup a callback when the "Done" button is pressed on keyboard
        editText.setOnEditorActionListener(this);

        cancelButton.setOnClickListener(v -> getDialog().dismiss());
        acceptButton.setOnClickListener(v -> {
            if(editText.getText() != null) {
                Toast.makeText(getActivity().getApplicationContext(), "Texto agregado correctamente", Toast.LENGTH_SHORT).show();
                dismiss();
            } else{
                Toast.makeText(getActivity().getApplicationContext(), "Pon texto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            EditNameDialogListener listener = (EditNameDialogListener) getActivity();
            listener.onFinishEditDialog(editText.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }

    /*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
            }
        });
        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        return alertDialogBuilder.create();
    }
     */
}