package br.com.ifpr.projetointegrador.Model;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.ifpr.projetointegrador.Activity.MenuPrincipal;
import br.com.ifpr.projetointegrador.Activity.Ranqueamento;
import br.com.ifpr.projetointegrador.Avançado.Array;
import br.com.ifpr.projetointegrador.Firebase.UsuarioFirebase;
import br.com.ifpr.projetointegrador.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context ctx;
    List<Usuario> usuarios;
    Usuario u= UsuarioFirebase.getDadosUsuarioLogado();

    public RecyclerAdapter(Context c, List<Usuario> u){
        ctx = c;
        usuarios = u;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(ctx).inflate(R.layout.linha, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.nome.setText(usuarios.get(i).getNick().toUpperCase());
        myViewHolder.pontos.setText("Pontos: "+Integer.toString(usuarios.get(i).getTotal()));
        myViewHolder.posic.setText(Integer.toString(i+1)+"º ");
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, pontos, posic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txNome);
            pontos = itemView.findViewById(R.id.txTotal);
            posic = itemView.findViewById(R.id.posic);
        }
    }

}
