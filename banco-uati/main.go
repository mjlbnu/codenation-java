package main

import (
	"database/sql"
	"fmt"
	"log"

	_ "github.com/lib/pq"
)

type Clientes struct {
	Id   int
	Nome string
}

func main() {
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

}
