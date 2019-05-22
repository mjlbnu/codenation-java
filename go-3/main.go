package main

import (
	"database/sql"
	"encoding/json"
	"log"
	"net/http"
	"os"
	"strings"
	"time"

	"github.com/gorilla/context"
	"github.com/gorilla/mux"
	_ "github.com/mattn/go-sqlite3"
)

//PORT port to be used
const PORT = "8080"

func main() {
	r := mux.NewRouter()
	http.Handle("/", r)
	r.Handle("/v1/quote", quote()).Methods("GET", "OPTIONS")
	r.Handle("/v1/quote/{actor}", quoteByActor()).Methods("GET", "OPTIONS")
	logger := log.New(os.Stderr, "logger: ", log.Lshortfile)
	srv := &http.Server{
		ReadTimeout:  30 * time.Second,
		WriteTimeout: 30 * time.Second,
		Addr:         ":" + PORT,
		Handler:      context.ClearHandler(http.DefaultServeMux),
		ErrorLog:     logger,
	}
	err := srv.ListenAndServe()
	if err != nil {
		log.Fatal(err)
	}
}

type Phrase struct {
	Actor string `json:"actor"`
	Quote string `json:"quote"`
}

func query(act string) Phrase {

	database, err := sql.Open("sqlite3", "./database.sqlite")

	if err != nil {
		log.Fatal(err)
	}

	var actor string
	var detail string

	if act == "" {
		database.QueryRow("SELECT ACTOR, DETAIL FROM SCRIPTS WHERE ACTOR <> '' AND DETAIL <> '' ORDER BY RANDOM() LIMIT 1").Scan(&actor, &detail)
	} else {
		database.QueryRow("SELECT ACTOR, DETAIL FROM SCRIPTS WHERE ACTOR = ? AND DETAIL <> '' ORDER BY RANDOM() LIMIT 1", act).Scan(&actor, &detail)
	}

	phrase := Phrase{actor, detail}

	return phrase
}

func quote() http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {

		js, err := json.Marshal(query(""))

		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}

		w.Header().Set("Content-Type", "application/json")
		w.Write(js)
	})
}

func quoteByActor() http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {

		if actor, ok := mux.Vars(r)["actor"]; ok {

			actor = strings.ReplaceAll(actor, "+", " ")

			js, err := json.Marshal(query(actor))

			if err != nil {
				http.Error(w, err.Error(), http.StatusInternalServerError)
				return
			}

			w.Header().Set("Content-Type", "application/json")
			w.Write(js)
		}
	})
}
