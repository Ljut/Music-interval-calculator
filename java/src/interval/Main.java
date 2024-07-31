package interval;

public class Main {

	
	public static void main(String[] args) throws SkalaNePostojiException {

		Integer INTERVAL;
		String NOTA1="C", NOTA2="F";
		
		Skala skala = new Skala(NOTA1);

		INTERVAL = skala.findIntervalAsInt(NOTA2);
		skala.printInterval(INTERVAL);
		
		
	}

}
