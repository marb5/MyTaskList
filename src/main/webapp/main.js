const API_URL = "http://localhost:8080/api";
const form = document.querySelector("form");
const div = document.querySelector("div");
const langsField = document.querySelector("fieldset .langs");

//pobieramy jezyki z bazy danych
fetch(`${API_URL}/langs`)
      .then(response => response.json()) //czekamy na jsona z danymi
      .then((langArr) => {               //beda one umieszczone w tablicy,
                                         //opisujacej kazdy rekord
        const langs = langArr.map(lang => `<input type="radio" name="lang" 
                                            value="${lang.id}">
                                           <label>${lang.code}</label><br>`).join('\n');
        //mapujemy nasza tablice, nastepnie zapisujemy dane w postaci html
        //laczymy je joinem w calosc przy pomocy '\n'
        langsField.innerHTML = langs; //przypisujemy kod html w odpowiednie pole
      });

form.addEventListener("submit", function(event) {
    event.preventDefault();
    const name = form.elements.name.value;
    const lang = form.elements.lang.value;
    //wysyla zapytanie do serwera o przywitanie
    fetch(`${API_URL}?name=${name}&lang=${lang}`)
      .then(response => response.text()) //oczekuje na odpowiedz z tekstem
      .then((text) => {                  //jezeli dostanie tekst
        div.innerHTML = `
            <h1>${text}</h1>
        `;
      });
})