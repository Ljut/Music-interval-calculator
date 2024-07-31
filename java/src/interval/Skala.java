package interval;

import java.util.HashMap;
import java.util.Map;

public class Skala {
	
	private static Map<Integer, String> intervali = new HashMap<>();
	private static String[] note_lista = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
	private static int note_lista_size = 12;
	private static int C1 = 24;
	String Ime;
	static Map<String, Integer> note = new HashMap<>();
	
	
	public Skala(String Ime) throws SkalaNePostojiException{
		if(doesNoteExist(Ime)) {
			throw new SkalaNePostojiException("Nota: "+Ime+" ne postoji.");
		}
		this.Ime = Ime;
		map_init_intervali();
		map_init_note(Ime);
		
	}
	
	private void map_init_intervali() {
		intervali.put(0, "Perfect prime");
		intervali.put(1,"Minor second");
	    intervali.put(2,"Major second");
	    intervali.put(3,"Minor third");
	    intervali.put(4,"Major third");
	    intervali.put(5,"Perfect fourth");
	    intervali.put(6,"Tritone");
	    intervali.put(7,"Perfect fifth");
	    intervali.put(8,"Minor sixth");
	    intervali.put(9,"Major sixth");
	    intervali.put(10,"Minor seventh");
	    intervali.put(11,"Major seventh");
	    intervali.put(12,"Perfect octave");
	}
	
	private void map_init_note(String NOTA1) {
		int i=0;
	   while (note_lista[i]!=NOTA1 && i < note_lista_size)
	   {
	        if(note_lista[i]!=NOTA1) {
	        	note.put(note_lista[i], C1+i+note_lista_size-1);
	            //note[note_lista[i]]=C1+i+note_lista_size-1;
	            i++;
	        } else {
	            //note[note_lista[i]]=C1+i+1;
	            break;
	        }
	   }
	   for(;i<note_lista_size;i++) {
		   note.put(note_lista[i], C1+i);
	        //note[note_lista[i]]=C1+i;
	   }
	}
	
	public boolean doesNoteExist(String NOTA) {
		
		for(String nota : note_lista) {
			if(nota.equals(NOTA)) {
				return false;
			}
		}
		return true;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printSkala() {
		for(Map.Entry<String,Integer> nota : note.entrySet()) {
			System.out.println(nota.getKey()+" "+nota.getValue());
		}
	}
	
	public int findIntervalAsInt(String NOTA2) throws SkalaNePostojiException {
		if(doesNoteExist(NOTA2)) {
			throw new SkalaNePostojiException("Nota: "+NOTA2+" ne postoji.");
		}
		return note.get(NOTA2) - note.get(Ime);
	}
	public String convertIntervalToString(int interval) throws IntervalOutOfScopeException {
		if(interval <0) {
			throw new IntervalOutOfScopeException("Interval je van dosega ove klase:"+ interval+"<0");
		}
		if(interval>intervali.size()) {
			throw new IntervalOutOfScopeException("Interval je van dosega ove klase:"+ interval+">"+note_lista_size);
		}
		return intervali.get(interval);
	}
}
