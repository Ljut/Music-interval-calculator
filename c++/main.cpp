#include <iostream>
#include <string>
#include <map>

void map_init_note(std::map<std::string,int> &note, std::string NOTA1) {
    
    int note_lista_size = 12;
    int C1 = 24;
    std::string note_lista[] = {"C","C#","D","D#","E","F","F#","G","G#","A","A#","H"};
    /*for(int i=0;i<note_lista_size;i++) {//std::cout << note_lista[i]<<"\n";
        if(note_lista[i]!=NOTA1) {
            note[note_lista[i]]=C1+i+1+note_lista_size;
            std::cout << note_lista[i] << "="<<C1+i+1+note_lista_size<<"\n";
        } else {
            note[note_lista[i]]=C1+i+1;
        }
    }*/
   int i=0;
   while (note_lista[i]!=NOTA1 && i < note_lista_size)
   {
        if(note_lista[i]!=NOTA1) {
            note[note_lista[i]]=C1+i+note_lista_size-1;
            std::cout << note_lista[i] << "="<<note[note_lista[i]]<<"\n";
            i++;
        } else {
            //note[note_lista[i]]=C1+i+1;
            break;
        }
   }
   for(;i<note_lista_size;i++) {
        note[note_lista[i]]=C1+i;
        std::cout << note_lista[i] << "="<<note[note_lista[i]]<<"\n";
   }

    
   
    /*note["C"]=24;
    note["C#"]=25;
    note["D"]=26;
    note["D#"]=27;
    note["E"]=28;
    note["F"]=29;
    note["F#"]=30;
    note["G"]=31;
    note["G#"]=32;
    note["A"]=33;
    note["A#"]=34;
    note["H"]=35;
    note["H#"]=36;*/
}

void map_init_intervali(std::map<int, std::string> &intervali) {

    intervali[0]="Perfect prime";
    intervali[1]="Minor second";
    intervali[2]="Major second";
    intervali[3]="Minor third";
    intervali[4]="Major third";
    intervali[5]="Perfect fourth";
    intervali[6]="Tritone";
    intervali[7]="Perfect fifth";
    intervali[8]="Minor sixth";
    intervali[9]="Major sixth";
    intervali[10]="Minor seventh";
    intervali[11]="Major seventh";
    intervali[12]="Perfect octave";

}

int main() {

    //Decleration
    std::map<std::string,int> note;
    std::map<int,std::string> intervali;
    std::string NOTA1, NOTA2;
    int INTERVAL;
    
    //Initialisation
    map_init_intervali(intervali);

    //Input
    std::cin>>NOTA1;
    std::cin>>NOTA2;
    map_init_note(note, NOTA1);

    //Checking if input is valid
    if(note.count(NOTA1) == 0 || note.count(NOTA2) == 0)
        return 1;

    //Calculating INTERVAL
    INTERVAL = note[NOTA2] - note[NOTA1] + 1;

    //Output
    std::cout << note[NOTA1] << " " <<  note[NOTA2] << "\n";
    std::cout << intervali[INTERVAL] << "\n";
    
    return 0;
}