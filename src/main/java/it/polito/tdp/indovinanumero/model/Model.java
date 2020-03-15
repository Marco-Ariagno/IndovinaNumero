package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.*;

public class Model {
	
	//le variabili e il cotruttore vanno nel modello
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;
	
	public Model() {
		this.inGioco=false;
		this.tentativiFatti=0;
	}
	
	//l'utente puo fare nuova partita o nuovo tentativo
	
	public void nuovaPartita() {
		//porto solo la logica dell'applicazione, non le parti di gestione della vista
		
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi=new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		//controllo se partita effettivamente in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita e' gia' terminata");
		}
		
		//controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire numero che non hai ancora usato tra 1 e "+NMAX+"\n");
		}
		// ----> il tentativo e' valido se arriviamo fino a qua
		this.tentativiFatti ++;
		
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(tentativo<this.segreto) {
			return -1;
		}else {
			return 1;
		}
		
		//0 se utente indovina, 1 se troppo alto, -1 se troppo basso
		//ABBIAMORIPORTATO TUTTA LA LOGICA NEL MODELLO
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo <1 || tentativo>NMAX) {
			return false;
		}else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
}
