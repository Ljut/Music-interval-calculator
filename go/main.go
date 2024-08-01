package main

import (
	"fmt"
)

func map_init_note(note map[string]int, NOTA1 string) {
	const note_lista_size int = 12
	var C1 int = 24
	note_lista := [note_lista_size]string{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "H"}
	var i int = 0
	for note_lista[i] != NOTA1 && i < note_lista_size {
		if note_lista[i] != NOTA1 {
			note[note_lista[i]] = C1 + i + note_lista_size
			i++
		} else {
			break
		}
	}
	for i < note_lista_size {
		note[note_lista[i]] = C1 + i
		i++
	}
}

func map_init_intervali(intervali map[int]string) {
	intervali[0] = "Perfect prime"
	intervali[1] = "Minor second"
	intervali[2] = "Major second"
	intervali[3] = "Minor third"
	intervali[4] = "Major third"
	intervali[5] = "Perfect fourth"
	intervali[6] = "Tritone"
	intervali[7] = "Perfect fifth"
	intervali[8] = "Minor sixth"
	intervali[9] = "Major sixth"
	intervali[10] = "Minor seventh"
	intervali[11] = "Major seventh"
	intervali[12] = "Perfect octave"
}

func doesNoteExist(NOTA1 string) bool {
	const note_lista_size int = 12
	note_lista := [note_lista_size]string{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "H"}
	for _, nota := range note_lista {
		if nota == NOTA1 {
			return true
		}
	}
	return false
}

func main() {

	var note = make(map[string]int)
	var intervali = make(map[int]string)

	var NOTA1, NOTA2 string = "C", "E"
	var INTERVAL int

	if !doesNoteExist(NOTA1) {
		fmt.Println("NOTA1 ne postoji: " + NOTA1)
		return
	} else if !doesNoteExist(NOTA2) {
		fmt.Println("NOTA2 ne postoji: " + NOTA2)
		return
	}

	map_init_intervali(intervali)

	map_init_note(note, NOTA1)

	INTERVAL = note[NOTA2] - note[NOTA1]

	fmt.Println(intervali[INTERVAL])

}
