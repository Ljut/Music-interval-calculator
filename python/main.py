NOTA1="C"
NOTA2="F"
INTERVAL=0

error = 0

note_lista = ["C","C#","D","D#","E","F","F#","G","G#","A","A#","H"]
note_lista_size = 12
C1 = 24

intervali = {
    0: "Perfect prime",
    1: "Minor second",
    2: "Major second",
    3: "Minor third",
    4: "Major third",
    5: "Perfect fourth",
    6: "Tritone",
    7: "Perfect fifth",
    8: "Minor sixth",
    9: "Major sixth",
    10: "Minor seventh",
    11: "Major seventh",
    12: "Perfect octave"
}
note = {}

def doesNoteExist(NOTA):
    for nota in note_lista:
        if nota == NOTA:
            return False
    return True


def map_init_note(NOTA1):
    note = {}
    i=0
    while (note_lista[i]!=NOTA1 and i < note_lista_size):
        if(note_lista[i]!=NOTA1):
            note[note_lista[i]]=C1+i+note_lista_size
            i+=1
        else:
            break
    for p in range(i,note_lista_size):
        note[note_lista[p]]=C1+p
    return note

error = doesNoteExist(NOTA1)

if error:
    print("Nota1: "+NOTA1+" ne postoji.")
else:
    error = doesNoteExist(NOTA2)
if error:
    print("Nota2: "+NOTA2+" ne postoji.")
else:
    note=map_init_note(NOTA1)
    print(note)
    INTERVAL = note[NOTA2]-note[NOTA1]

    print(intervali[INTERVAL])
