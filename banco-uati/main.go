package main

import (
	"database/sql"
	"encoding/csv"
	"fmt"
	"io/ioutil"
	"log"
	"mime/multipart"
	"net/http"
	"os"

	_ "github.com/lib/pq"
)

const arquivo = "clientes.csv"

type Clientes struct {
	Id   int
	Nome string
}

func main() {
	setupRoutes()
}

func uploadFile(w http.ResponseWriter, r *http.Request) {
	fmt.Println("File Upload Endpoint Hit")

	// Parse our multipart form, 10 << 20 specifies a maximum
	// upload of 10 MB files.
	r.ParseMultipartForm(10 << 20)
	// FormFile returns the first file for the given key `myFile`
	// it also returns the FileHeader so we can get the Filename,
	// the Header and the size of the file
	file, handler, err := r.FormFile("myFile")
	if err != nil {
		fmt.Println("Error Retrieving the File")
		fmt.Println(err)
		return
	}
	defer file.Close()
	fmt.Printf("Uploaded File: %+v\n", handler.Filename)
	fmt.Printf("File Size: %+v\n", handler.Size)
	fmt.Printf("MIME Header: %+v\n", handler.Header)

	// Create a temporary file within our temp-csv directory that follows
	// a particular naming pattern
	tempFile, err := ioutil.TempFile("temp-csv", "upload-*.csv")
	if err != nil {
		fmt.Println(err)
	}
	defer tempFile.Close()

	// read all of the contents of our uploaded file into a
	// byte array
	fileBytes, err := ioutil.ReadAll(file)
	if err != nil {
		fmt.Println(err)
	}
	// write this byte array to our temporary file
	tempFile.Write(fileBytes)
	// return that we have successfully uploaded our file!
	fmt.Fprintf(w, "Upload realizado com sucesso!\n")

	// save data to database
	ProccessCSV(0, file, r.FormValue("clear"), w)
}

func setupRoutes() {
	http.HandleFunc("/upload", uploadFile)
	http.ListenAndServe(":8080", nil)
}

func ProccessCSV(collumn int, f multipart.File, checkbox string, w http.ResponseWriter) {

	connStr := "user=postgres dbname=uatidb sslmode=disable"
	db, err := sql.Open("postgres", connStr)
	defer db.Close()
	if err != nil {
		log.Fatal(err)
	}

	// clear the clients table before import?
	if checkbox == "clear" {
		sqlStatement := `DELETE FROM clientes;`
		res, err := db.Exec(sqlStatement)
		if err != nil {
			panic(err)
		}
		count, err := res.RowsAffected()
		if err != nil {
			panic(err)
		}
		fmt.Println("Clientes excluídos: ", count)
		fmt.Fprintln(w, "Clientes excluídos: ", count)
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

	i := 0
	for _, linha := range linhas[0:] {
		err = db.QueryRow(sqlStatement, linha[collumn]).Scan(&id)
		if err != nil {
			panic(err)
		}
		fmt.Println("New record ID is:", id)
		i++
	}
	fmt.Println("Clientes importados: ", i)
	fmt.Fprintln(w, "Clientes importados: ", i)
}
