package com.bingo.app.listacontatosdm.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bingo.app.listacontatosdm.Model.Contato;
import com.bingo.app.listacontatosdm.R;
import com.bingo.app.listacontatosdm.adapter.ListaContatosAdapter;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nomeEditText;
    private EditText enderecoEditText;
    private EditText telefoneEditText;
    private EditText emailEditText;
    private Button cancelarButton;
    private Button salvarButton;
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);


        nomeEditText = findViewById(R.id.nomeEditText);
        enderecoEditText = findViewById(R.id.emailEditText);
        telefoneEditText = findViewById(R.id.telefoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        cancelarButton = findViewById(R.id.cancelarButton);
        salvarButton = findViewById(R.id.salvarButton);

        // Setando Listener dos botões
        cancelarButton.setOnClickListener(this);
        salvarButton.setOnClickListener(this);
        String subtitulo;
        Contato contato = (Contato) getIntent().getSerializableExtra(ListaContatosActivity.CONTATO_EXTRA);
        position = getIntent().getIntExtra("POSITION", -1);
        boolean isEdicao = position > -1;
        if (contato != null) {
            //modo detalhes
            subtitulo = isEdicao ? "Editar Contato" : "Detalhes do contato";
            modoDetalhes(contato,isEdicao);
        } else {
            subtitulo = "Novo contato";
        }

        //Setando subtitulo
        getSupportActionBar().setSubtitle("Contato");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.cancelarButton:
                setResult(RESULT_CANCELED);
                finish();
                break;

            case R.id.salvarButton:
                Contato novoContato = new Contato();
                novoContato.setNome(nomeEditText.getText().toString());
                novoContato.setTelefone(telefoneEditText.getText().toString());
                novoContato.setEndereco(enderecoEditText.getText().toString());
                novoContato.setEmail(emailEditText.getText().toString());
                Intent resultadoIntent = new Intent();
                resultadoIntent.putExtra(ListaContatosActivity.CONTATO_EXTRA, novoContato);
                resultadoIntent.putExtra("POSITION", position);
                setResult(RESULT_OK, resultadoIntent);
                finish();
                break;
        }

    }

    public void modoDetalhes(Contato contato, Boolean isEdicao) {
        nomeEditText.setText(contato.getNome());
        nomeEditText.setEnabled(isEdicao);
        enderecoEditText.setText(contato.getEndereco());
        enderecoEditText.setEnabled(isEdicao);
        telefoneEditText.setText(contato.getTelefone());
        telefoneEditText.setEnabled(isEdicao);
        emailEditText.setText(contato.getEmail());
        emailEditText.setEnabled(isEdicao);
        if (isEdicao) {
            salvarButton.setVisibility(View.VISIBLE);
        } else {
            salvarButton.setVisibility(View.GONE);
        }
    }




}
