var intervali = {}
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

var note_lista = ["C","C#","D","D#","E","F","F#","G","G#","A","A#","H"]

function fun() {
    alert("test")
}

function izracunajInterval() {
    let INTERVAL
    let NOTA1 = document.getElementById("NOTA1").value
    let NOTA2 = document.getElementById("NOTA2").value
    console.log(NOTA1,NOTA2)
    
    let note_lista_size = 12;
    let note = {}
    let C1 = 24;
    let i=0;
    while (note_lista[i]!=NOTA1 && i < note_lista_size)
    {
            if(note_lista[i]!=NOTA1) {
                note[note_lista[i]]=C1+i+note_lista_size;
                i++;
            } else {
                //note[note_lista[i]]=C1+i+1;
                break;
            }
    }
    for(;i<note_lista_size;i++) {
            note[note_lista[i]]=C1+i;
            
    }

    INTERVAL = note[NOTA2] - note[NOTA1];

    console.log(intervali[INTERVAL]);
    document.getElementById("Interval-val").innerHTML = intervali[INTERVAL]
}