package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
)

type JSONIn struct {
	Items []Item `json:"items"`
}

type Item struct {
	Name        string `json:"name"`
	Description string `json:"description"`
	URL         string `json:"html_url"`
	Stars       int64  `json:"stargazers_count"`
}

type JSONOut struct {
	Name        string `json:"name"`
	Description string `json:"description"`
	Url         string `json:"url"`
	Stars       int64  `json:"stars"`
}

func main() {
	err := githubStars("go")
	if err != nil {
		log.Fatal(err)
	}
}

func githubStars(lang string) error {

	res, err := http.Get("https://api.github.com/search/repositories?q=language:" + lang + "&sort=stars&order=desc&page=1&per_page=10")
	if err != nil {
		log.Fatal(err)
	}

	body, err := ioutil.ReadAll(res.Body)
	res.Body.Close()
	if err != nil {
		log.Fatal(err)
	}
	if res.StatusCode != 200 {
		log.Fatal("Unexpected status code", res.StatusCode)
	}

	data := JSONIn{}
	err = json.Unmarshal(body, &data)
	if err != nil {
		log.Fatal(err)
	}

	alter := []JSONOut{}
	for _, item := range data.Items {
		alter = append(alter, JSONOut{
			Name:        item.Name,
			Description: item.Description,
			Url:         item.URL,
			Stars:       item.Stars,
		})
	}

	out, err := json.Marshal(alter)
	if err != nil {
		log.Fatal(err)
	}

	err = ioutil.WriteFile("stars.json", out, 0644)
	if err != nil {
		log.Fatal(err)
	}

	return err
}
