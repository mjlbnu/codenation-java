package main

import "fmt"
import "os"
import "encoding/csv"
import "sort"
import "strconv"

const(
	arquivo = "data.csv"

	// colunas
	nationality = 14
	club        = 3
	full_name   = 2
	age         = 6
	eur_wage    = 17
)

func main() {
	
	resQ1, errQ1 := q1()
	fmt.Println(resQ1, errQ1)

	resQ2, errQ2 := q2()
	fmt.Println(resQ2, errQ2)

	resQ3, errQ3 := q3()
	fmt.Println(resQ3, errQ3)

	resQ4, errQ4 := q4()
	fmt.Println(resQ4, errQ4)

	resQ5, errQ5 := q5()
	fmt.Println(resQ5, errQ5)

	resQ6, errQ6 := q6()
	fmt.Println(resQ6, errQ6)
}

func ReadCSV(collumn int) []string {

	var data []string

	// Abre o arquivo
	arq, err := os.Open(arquivo)
	if err != nil {
		panic(err)
	}
	defer arq.Close()

	// joga o conteúdo em uma variável
	linhas, err := csv.NewReader(arq).ReadAll()
	if err != nil {
		panic(err)
	}

	// Loop nas linhas populando slice partindo da linha 1
	for _, linha := range linhas[1:] {
		data = append(data, linha[collumn])
	}

	return data
}

// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
func q1() (int, error) {

	var nacionalidades = ReadCSV(nationality)

	// cria map removendo duplicados
	unicos := make(map[string]bool)
		
	for _, value := range nacionalidades {
		if _, ok := unicos[value]; ok {
			continue
		}
		unicos[value] = true
	}

	return len(unicos), nil
}

//Quantos clubes (coluna `club`) diferentes existem no arquivo?
func q2() (int, error) {

	var clubes = ReadCSV(club)

	// cria map removendo duplicados
	unicos := make(map[string]bool)
		
	for _, value := range clubes {
		if _, ok := unicos[value]; ok {
			continue
		}
		unicos[value] = true
	}

	var vazio = 0
	for key := range unicos {
		if key == "" {
		//fmt.Println("Key:", key, "Value:", value)
		vazio ++
		}
	}

	return len(unicos) - vazio, nil
}

//Liste o primeiro nome dos 20 primeiros jogadores de acordo com a coluna `full_name`.
func q3() ([]string, error) {

	var nomes = ReadCSV(full_name)[0:20]

	var lista20 []string

	for _, nome := range nomes {

		lista20 = append(lista20, nome)	
	}

	return lista20, nil
}

//Quem são os top 10 jogadores que ganham mais dinheiro (utilize as colunas `full_name` e `eur_wage`)?
func q4() ([]string, error) {

	type Jogador struct{
		name string
		eurwage float64
	}

	var nomes = ReadCSV(full_name)
	var wages = ReadCSV(eur_wage)
	var data []Jogador
	var convert = 0.0

	for i, n := range nomes {
		convert, _ =  strconv.ParseFloat(wages[i], 64)

		data = append(data, Jogador{n, convert})
	}

	// ordenação desc
	sort.SliceStable(data, func(i, j int) bool {
		return data[i].eurwage > data[j].eurwage
	})

	var top10 []string

	for _, jogador := range data[0:10] {
		top10 = append(top10, jogador.name)
	}

	return top10, nil
}

//Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
func q5() ([]string, error) {

	type Jogador struct{
		name string
		age int
		eurwage float64
	}

	var nomes  = ReadCSV(full_name)
	var idades = ReadCSV(age)
	var wages  = ReadCSV(eur_wage)

	var data []Jogador

	var convert  = 0.0
	var convert2 = 0

	for i, n := range nomes {
		convert, _  = strconv.ParseFloat(wages[i], 64)
		convert2, _ = strconv.Atoi(idades[i])

		data = append(data, Jogador{n, convert2, convert})
	}

	// ordenação desc
	sort.SliceStable(data, func(i, j int) bool {

		if data[i].age > data[j].age {  
			return true
		} else if data[i].age == data[j].age {
			return data[i].eurwage > data[j].eurwage
		} else {
			return false
		}
	})

	var top10 []string

	for _, jogador := range data[0:10] {
		top10 = append(top10, jogador.name)
	}

	return top10, nil
}

//Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
func q6() (map[int]int, error) {
	idades := make(map[int]int)

	var ages = ReadCSV(age)
	var convert = 0

	for _, age := range ages {
		convert, _ = strconv.Atoi(age) 
		idades[convert]++
	}

	return idades, nil
}
