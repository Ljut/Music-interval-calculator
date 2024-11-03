package com.example.intervalquiz;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import interval.IntervalOutOfScopeException;

public class SkalaFlat {



	/*Eng
	*"Perfect prime","Minor second","Major second","Minor third",
			"Major third","Perfect fourth","Tritone","Perfect fifth",
			"Minor sixth","Major sixth","Minor seventh","Major seventh",
			"Perfect octave"
	*
	* */





	// Private no use attributes
	public static final Pair<String[], String[]> notes = new Pair<>(
			new String[]{"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"},
			new String[]{"C","Db","D","Eb","E","F","Gb","G","Ab","A","b","H"}
	);
	private String[] note_lista_global = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","b","H"};
//{"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
	// Private attributes
	private String Ime;
	private String flatSharp = "#";
	private static Map<String, Integer> note = new HashMap<>();
	private static Map<Integer, String> reverse_note = new HashMap<>();
	private static final Map<Integer, String> intervali = new HashMap<>();
	private static final String[] intervali_lista = {
			"Prima","Mala sekunda","Velika sekunda","Mala terca",
			"Velika terca","Kvarta","Prekomjerna Kvarta","Kvinta",
			"Mala seksta","Velika seksta","Mala septima","Velika septima",
			"Oktava"
	};

	// Private Final Attributes - Do not touch from out side of this class: IT will BREAK the class
	public static final int note_lista_size = 12;
	private static final String[] note_lista = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","b","H"};
			//{"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
	//


	// Constructors
	public SkalaFlat(String Ime) throws interval.SkalaNePostojiException {
		if(doesNoteExist(Ime) || Ime == null) {
			throw new interval.SkalaNePostojiException("Nota: "+Ime+" ne postoji.");
		}
		this.Ime = Ime;
		map_init_intervali();
		map_init_note(Ime);
	}
	public SkalaFlat(String Ime, String notation) throws interval.SkalaNePostojiException, interval.NotationNePostojiException {
		if(doesNoteExist(Ime) || Ime == null) {
			throw new interval.SkalaNePostojiException("Nota: "+Ime+" ne postoji.");
		}
		this.Ime = Ime;
		setFlatSharp(notation);
		map_init_intervali();
		map_init_note(Ime);
	}
	public void reinit(String Ime) throws interval.SkalaNePostojiException {
		if(doesNoteExist(Ime) || Ime == null) {
			throw new interval.SkalaNePostojiException("Nota: "+Ime+" ne postoji.");
		}
		this.Ime = Ime;
		//map_init_intervali();
		//map_init_note(Ime);
		sortSkala(Ime);

	}

	// Setters
	public void setFlatSharp(String notation) throws interval.NotationNePostojiException {
		if(notation.equals("#")) {
			flatSharp = notation;
			note_lista_global = new String[]{"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
		} else if (notation.equals("b")) {
			flatSharp = notation;
			note_lista_global = new String[]{"C","Db","D","Eb","E","F","Gb","G","Ab","A","b","H"};
		} else {
			throw new interval.NotationNePostojiException("Notation: "+notation+" ne postoji. Koristi '#' ili 'b'");
		}

	}

	// Getters
	public String getFlatSharp() { return flatSharp; }
	public String[] getNote_lista_global() { return note_lista_global; }
	public String[] get_note_lista() { return note_lista; }
	public static String[] get_intervali_lista() { return intervali_lista; }


	// Constructor methods
	private void map_init_intervali() {
		for(int i=0;i<note_lista_size;i++) {
			intervali.put(i,intervali_lista[i]);
		}
	}
	private void map_init_note(String NOTA1) {
		int i=0;
		int c1 = 0;//24;
		while (!note_lista[i].equals(NOTA1))
		{
			if(!note_lista[i].equals(NOTA1)) {
				note.put(note_lista[i], c1 +i+note_lista_size);
				reverse_note.put(c1 +i+note_lista_size, note_lista[i]);
				//note[note_lista[i]]=C1+i+note_lista_size-1;
				i++;
			} else {
				//note[note_lista[i]]=C1+i+1;
				break;
			}
		}
	   for(;i<note_lista_size;i++) {
		   note.put(note_lista[i], c1 +i);
		   reverse_note.put(c1+i, note_lista[i]);
			//note[note_lista[i]]=C1+i;
	   }
	}
	public String[] sortSkala () throws interval.SkalaNePostojiException {
		return sortSkala(this.Ime);
	}
		public String[] sortSkala (String Ime) throws interval.SkalaNePostojiException {
		if(doesNoteExist(Ime) || Ime == null) {
			throw new interval.SkalaNePostojiException("Nota: "+Ime+" ne postoji.");
		}
		int root = 0;
		this.Ime = Ime;
		for(int i=0;i<note_lista_size;i++) {
			if(Objects.equals(note_lista[i], Ime)) {
				break;
			}
			root++;
		}
		String[] note_lista_new = new String[12];
		int index = 0;
		for(int i=root;i<note_lista_size;i++) {
			note_lista_new[index] = note_lista[i];
			index++;
		}
		for (int i=0;i<root;i++) {
			note_lista_new[index] = note_lista[i];
			index++;
		}
		note_lista_global = note_lista_new;
		return note_lista_new;
	}

	// Logic boolean => If exists method
	public boolean doesNoteExist(String NOTA) {
		
		for(String nota : note_lista) {
			if(nota.equals(NOTA)) {
				return false;
			}
		}
		return true;
	}
	public String isFlatOrSharp() {return flatSharp;}
	public String changeNotation() {
		if(Objects.equals(flatSharp, "#")) {
			flatSharp = "b";
			note_lista_global = new String[]{"C","Db","D","Eb","E","F","Gb","G","Ab","A","b","H"};
		} else {
			flatSharp = "#";
			note_lista_global = new String[]{"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
		}
		return flatSharp;
	}

	// Logic meat of the Class
	public int findIntervalAsInt(String NOTA2) throws interval.SkalaNePostojiException {
		if(doesNoteExist(NOTA2)) {
			throw new interval.SkalaNePostojiException("Nota: "+NOTA2+" ne postoji.");
		}
		return note.get(NOTA2) - note.get(Ime);
	} //could be NULL if uninitialised constructor
	public String convertIntervalToString(int interval) throws IntervalOutOfScopeException {
		if(interval <0) {
			throw new IntervalOutOfScopeException("Interval je van dosega ove klase:"+ interval+"<0");
		}
		if(interval>intervali.size()) {
			throw new IntervalOutOfScopeException("Interval je van dosega ove klase:"+ interval+">"+note_lista_size);
		}
		return intervali.get(interval);
	}
	public String findNotaFromInterval(int interval) {

		int value = note.get(this.Ime) + interval;

		return reverse_note.get(value);
	} //could be NULL if uninitialised constructor

	// Debug methods
	public void printNote_lista() {
		//for(String nota : note_lista) {
		//System.out.println("TU");
		System.out.print(this.Ime+": ");
		for (String s : note_lista_global) {
			System.out.print(s + ", ");
		}
		System.out.println();
	}
	public void printIntervali() {
		for(Map.Entry<Integer,String> interval : intervali.entrySet()) {
			System.out.println(interval.getKey()+" "+interval.getValue());
		}
	}
	public void printInterval(int INTERVAL) {
		try {
			System.out.println(convertIntervalToString(INTERVAL));
		} catch (IntervalOutOfScopeException e) {
			throw new RuntimeException(e);
		}
	}
	public void printSkala() {
		for(Map.Entry<String,Integer> nota : note.entrySet()) {
			System.out.println(nota.getKey()+" "+nota.getValue());
		}
	}

}
