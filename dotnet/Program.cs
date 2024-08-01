// See https://aka.ms/new-console-template for more information
//static string[] note_lista = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
Dictionary<string,int>  map_init_note(string NOTA1) {
    Dictionary<string,int> note = new Dictionary<string, int>();
    int note_lista_size = 12;
    int C1 = 24;
    string[] note_lista = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
    int i=0;
    while (note_lista[i]!=NOTA1 && i < note_lista_size)
    {
            if(note_lista[i]!=NOTA1) {
                note.Add(note_lista[i], C1+i+note_lista_size-1);
                i++;
            } else {
                //note[note_lista[i]]=C1+i+1;
                break;
            }
    }
    for(;i<note_lista_size;i++) {
            note.Add(note_lista[i], C1+i);
            
    }
    return note;
}

Dictionary<int, string> map_init_intervali() {
    Dictionary<int, string> intervali = new Dictionary<int, string>
    {
        { 0, "Perfect prime" },
        { 1, "Minor second" },
        { 2, "Major second" },
        { 3, "Minor third" },
        { 4, "Major third" },
        { 5, "Perfect fourth" },
        { 6, "Tritone" },
        { 7, "Perfect fifth" },
        { 8, "Minor sixth" },
        { 9, "Major sixth" },
        { 10, "Minor seventh" },
        { 11, "Major seventh" },
        { 12, "Perfect octave" }
    };
    return intervali;
}


bool doesNoteExist(String NOTA) {
	string[] note_lista = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
    foreach(String nota in note_lista) {
        if(nota==NOTA) {
            return false;
        }
    }
    return true;
}

Dictionary<string,int> note;
Dictionary<int, string> intervali = map_init_intervali();
string NOTA1, NOTA2;
int INTERVAL;



Console.Write("Unesi NOTA1: ");
NOTA1 = Console.ReadLine();

Console.Write("Unesi NOTA2: ");
NOTA2 = Console.ReadLine();

if(NOTA1 == null || NOTA2 == null) {
    return;
}
if(doesNoteExist(NOTA1)) {
    Console.WriteLine("Nota: "+NOTA1+" ne postoji.");
    return;
}
if(doesNoteExist(NOTA2)) {
    Console.WriteLine("Nota: "+NOTA2+" ne postoji.");
    return;
}

note = map_init_note(NOTA1);
foreach (KeyValuePair<string, int> nota in note)
{
    Console.WriteLine("{0} {1}", nota.Key, nota.Value);
}

INTERVAL = note[NOTA2] - note[NOTA1];

Console.WriteLine(intervali[INTERVAL]);