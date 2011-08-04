package br.com.sapereaude.scroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TesteDeScrollInfinitoActivity extends Activity {
    protected static final int OFFSET = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView lista = (ListView) findViewById(R.id.lista);
        
        final List<String> itensDaLista = geraListaDeStringsAleatoria(10);
        // Populando o ListView normalmente
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itensDaLista);
		lista.setAdapter(adapter);
		
		// Aqui a mágica acontece :)
        lista.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// Não faz nada nesse caso
			}
			@Override
			public void onScroll(AbsListView view, int primeiroItemVisivel,
					int quantidadeDeItensVisiveis, int totalDeItensNoAdapter) {
				if(primeiroItemVisivel + quantidadeDeItensVisiveis >= totalDeItensNoAdapter - OFFSET) {
					// Aqui fazemos o tratamento para aumentar a lista. Se fosse uma lista
					// de tweets, por exemplo, aqui pediríamos os tweets anteriores para colocar
					// na lista.
					List<String> proximosItensDaLista = geraListaDeStringsAleatoria(20);
					itensDaLista.addAll(proximosItensDaLista);
					adapter.notifyDataSetChanged();
				}
			}
		});
    }

    // Essa parte não é relevante. É só para gerar uma lista de strings aleatórias com o tamanho desejado
	private List<String> geraListaDeStringsAleatoria(int tamanhoDaLista) {
		String[] strings = {"Toshi", "Andrew", "Abobrinha", "Maioneses", "Folha", "Semente", "Quadro", "Palavra", "Aleatorio", "Elefante", "Girafa", "Televisão", "Papel Higienico", "Computador", "Sorteio", "Bingo"};
		List<String> listaDeStringsAleatoria = new ArrayList<String>();
		Random random = new Random();
		
		for(int i = 0; i < tamanhoDaLista; i++) {
			Integer posicaoAleatoria = random.nextInt(strings.length);
			String stringAleatoria = strings[posicaoAleatoria].concat(posicaoAleatoria.toString());
			listaDeStringsAleatoria.add(stringAleatoria);
		}
		return listaDeStringsAleatoria;
	}
}