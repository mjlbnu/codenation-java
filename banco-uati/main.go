package main

import (
	"database/sql"
	"encoding/csv"
	"fmt"
	"log"
	"os"

	_ "github.com/lib/pq"
)

const arquivo = "clientes.csv"

type Clientes struct {
	Id   int
	Nome string
}

func main() {
	/*
		connStr := "user=postgres dbname=uatidb sslmode=disable"
		db, err := sql.Open("postgres", connStr)
		defer db.Close()
		if err != nil {
			log.Fatal(err)
		}

		rows, err := db.Query(`SELECT id, nome FROM clientes`)
		if err != nil {
			panic(err)
		}
		defer rows.Close()
		for rows.Next() {
			clientes := Clientes{}
			err = rows.Scan(&clientes.Id, &clientes.Nome)
			if err != nil {
				panic(err)
			}
			fmt.Println(clientes)
		}
		err = rows.Err()
		if err != nil {
			panic(err)
		}
	*/
	ReadCSV(0)
}

func ReadCSV(collumn int) {

	connStr := "user=postgres dbname=uatidb sslmode=disable"
	db, err := sql.Open("postgres", connStr)
	defer db.Close()
	if err != nil {
		log.Fatal(err)
	}

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

	sqlStatement := `
	INSERT INTO clientes (nome)
	VALUES ($1)
	RETURNING id`
	id := 0

	/*
		err = db.QueryRow(sqlStatement, "jon@calhoun.io").Scan(&id)
		if err != nil {
			panic(err)
		}
		fmt.Println("New record ID is:", id)
	*/

	// Loop nas linhas populando slice partindo da linha 0
	for _, linha := range linhas[0:] {
		//data = append(data, linha[collumn])
		err = db.QueryRow(sqlStatement, linha[collumn]).Scan(&id)
		if err != nil {
			panic(err)
		}
		fmt.Println("New record ID is:", id)

	}
	fmt.Println("acabou")
}
