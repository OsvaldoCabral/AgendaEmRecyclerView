package io.osvaldocabral.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditAgenda extends AppCompatActivity {

    EditText editTextTextNameEdit;
    EditText editTextPhoneEdit;
    EditText editTextEnderecoEdit;
    Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_agenda);

        editTextTextNameEdit = findViewById(R.id.editTextTextNameEdit);
        editTextPhoneEdit = findViewById(R.id.editTextPhoneEdit);
        editTextEnderecoEdit = findViewById(R.id.editTextEnderecoEdit);

        fillFields();
    }

    public void fillFields() {
        agenda = DataSingleton.getInstance().currentEditAgenda;
        editTextTextNameEdit.setText(agenda.getNome());
        editTextPhoneEdit.setText(agenda.getTelefone());
        editTextEnderecoEdit.setText(agenda.getEndereco());
    }

    public void saveEditClicked(View view) {
        agenda.setNome(editTextTextNameEdit.getText().toString());
        agenda.setTelefone(editTextPhoneEdit.getText().toString());
        agenda.setEndereco(editTextEnderecoEdit.getText().toString());
        super.onBackPressed();
    }
}