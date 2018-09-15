package com.bingo.app.listacontatosdm.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bingo.app.listacontatosdm.Model.Contato;
import com.bingo.app.listacontatosdm.R;
import com.bingo.app.listacontatosdm.adapter.ListaContatosAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaContatosActivity extends AppCompatActivity implements  AdapterView.OnItemClickListener {
    //REQUEST CODE PARA ABERTURA DA TELA CONTTATOACTIVITY
    private final int NOVO_CONTATO_REQUEST_CODE = 0;

    //constatnte para passar parametros para tela contatoActivity - modoDetalhes
    public static final String CONTATO_EXTRA = "CONTATO_EXTRA";
    public static final String CONTATO_ALTERAR = "CONTATO_ALTERAR";
    public static final String CONTATO_DETALHAR = "CONTATO_DETALHAR";


    //referencia para as views
    private ListView listaContatosLisView;

    //Lista de contatos usada para preencher a listView
    private List<Contato> ListaContatos;

    private ListaContatosAdapter listaContatosAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listra_contatos);

        // referencia para listView
        listaContatosLisView = findViewById(R.id.listaContatoListView);
        ListaContatos = new ArrayList<>();
        //preencherListaContatos();

        // List<String> listaNomes = new ArrayList<>();
        // for (Contato contato: ListaContatos){
        ///     listaNomes.add(contato.getNome());
        // }

        // ArrayAdapter<String> listaContatosAdpter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaNomes);
        listaContatosAdapter = new ListaContatosAdapter(this, ListaContatos);
        listaContatosLisView.setAdapter(listaContatosAdapter);
        listaContatosLisView.setOnItemClickListener(this);
        registerForContextMenu(listaContatosLisView);

        //ArrayAdapter<Contato> listaContatosAdapter = new ArrayAdapter<Contato>(this,android.R.layout.simple_list_item_1,ListaContatos);
        //listaContatosLisView.setAdapter(listaContatosAdapter);

    }

    private void preencherListaContatos() {
        for (int i = 0; i < 20; i++) {
            ListaContatos.add(new Contato("C" + i, "ifsp", "1234", "@fdfsd"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.configuracaoMenuItem:
                return true;

            case R.id.novoContatoMenuItem:
                Intent novoContatoIntent = new Intent("NOVO_CONTATO_ACTION");
                startActivityForResult(novoContatoIntent, NOVO_CONTATO_REQUEST_CODE);
                return true;
            case R.id.sairMenuItem:
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo infoMenu = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Contato contato = ListaContatos.get(infoMenu.position);

        switch (item.getItemId()) {
            case R.id.editarContatoMenuItem:
                Intent novoContatoIntent = new Intent("NOVO_CONTATO_ACTION");
                novoContatoIntent.putExtra("POSITION", infoMenu.position);
                novoContatoIntent.putExtra(CONTATO_EXTRA, contato);
                startActivityForResult(novoContatoIntent, NOVO_CONTATO_REQUEST_CODE);
                return true;
            case R.id.ligarContatoMenuItem:
                return true;
            case R.id.verEnderecoMenuItem:
                return true;
            case R.id.enviarEmailMenuItem:
                return true;
            case R.id.removerContatoMenuItem:
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case NOVO_CONTATO_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    //recupero da intent data
                    Contato contato = (Contato) data.getSerializableExtra(CONTATO_EXTRA);

                    int position = data.getIntExtra("POSITION", -1);
                    if (position != -1) {
                        ListaContatos.set(position, contato);
                    } else {
                        //atualizo lista
                        ListaContatos.add(contato);
                        Toast.makeText(this, "Contato alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                    listaContatosAdapter.notifyDataSetChanged();
                        //notifico a adapter
                        Toast.makeText(this, "Novo Contato adicionado", Toast.LENGTH_SHORT).show();

                } else {
                    if (resultCode == RESULT_CANCELED) {
                        Toast.makeText(this, "Cadastro cancelado", Toast.LENGTH_SHORT).show();
                    }
                }



        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contato contato = ListaContatos.get(position);
        Intent detalhesContatosIntent = new Intent(this,ContatoActivity.class);
        detalhesContatosIntent.putExtra(CONTATO_EXTRA,contato);
        startActivity(detalhesContatosIntent);
    }




}
