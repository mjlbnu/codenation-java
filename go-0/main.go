package main

import (
	"fmt"
	"sort"
)

type Estados struct {
	Nome string
	Area float64
}

type ByArea []Estados

func (a ByArea) Len() int {
	return len(a)
}
func (a ByArea) Swap(i, j int) {
	a[i], a[j] = a[j], a[i]
}
func (a ByArea) Less(i, j int) bool {
 return a[i].Area > a[j].Area
}

func main() {

	data, err := os10maioresEstadosDoBrasil()
	if err != nil {
		panic(err)
	}

	fmt.Println(data)

}

func os10maioresEstadosDoBrasil() ([]string, error) {

	var data []string

	estados := []Estados{
		{"Acre", 164123.04},
		{"Ceará", 148920.472},
		{"Amapá", 142828.521},
		{"Pernambuco", 98311.616},
		{"Santa Catarina", 95736.165},
		{"Paraíba", 56585},
		{"Rio Grande do Norte", 52811.047},
		{"Espírito Santo", 46095.583},
		{"Rio de Janeiro", 43780.172},
		{"Alagoas", 27778.506},
		{"Sergipe", 21915.116},
		{"Distrito Federal", 5779.999},
		{"Amazonas", 1559159.148},
		{"Pará", 1247954.666},
		{"Mato Grosso", 903366.192},
		{"Minas Gerais", 586522.122},
		{"Bahia", 564733.177},
		{"Mato Grosso do Sul", 357145.532},
		{"Goiás", 340111.783},
		{"Maranhão", 331937.45},
		{"Rio Grande do Sul", 281730.223},
		{"Tocantins", 277720.52},
		{"Piauí", 251577.738},
		{"São Paulo", 248222.362},
		{"Rondônia", 237590.547},
		{"Roraima", 224300.506},
		{"Paraná", 199307.922},
	}

	sort.Sort(ByArea(estados))

	for _, uf := range estados[0:10] {
		data = append(data, uf.Nome)	
	}

	return data, nil
}