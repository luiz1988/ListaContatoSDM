package com.bingo.app.listacontatosdm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bingo.app.listacontatosdm.Model.Contato;
import com.bingo.app.listacontatosdm.R;

import java.util.List;

public class ListaContatosAdapter extends ArrayAdapter<Contato> {
    private LayoutInflater layoutInflater;

    public ListaContatosAdapter(Context context, List<Contato> listaContatos) {
        super(context, R.layout.layout_vew_contato_adapter, listaContatos);
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        //se ainda não foi inflada
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_vew_contato_adapter, null);
            holder = new Holder();
            holder.nomeContatotextView = convertView.findViewById(R.id.nomeContatoTextView);
            holder.emailContatotextView = convertView.findViewById(R.id.emailContatoTextView);


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Contato contato = getItem(position);

        holder.nomeContatotextView.setText(contato.getNome());
        holder.emailContatotextView.setText(contato.getEmail());

        //((TextView) convertView).setText(contato.getNome());

        return convertView;
    }

    private class Holder {
        public TextView nomeContatotextView;
        public TextView emailContatotextView;
    }
}
