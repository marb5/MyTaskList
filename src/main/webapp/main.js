(function() {
    //const API_URL = "127.0.0.1:8080/api";
    const API_URL = "/api";

    initWelcomeForm();

    function initWelcomeForm() {
        //pobieramy jezyki z bazy danych
        fetch(`${API_URL}/langs`)
            .then(processOkResponse) //czekamy na jsona z danymi
            .then((langArr) => {               //beda one umieszczone w tablicy,
                                               //opisujacej kazdy rekord
              const langs = langArr.map(lang => `<input type="radio" name="lang" 
                                                  value="${lang.id}">
                                                 <label>${lang.code}</label><br>`).join('\n');
              //mapujemy nasza tablice, nastepnie zapisujemy dane w postaci html
              //laczymy je joinem w calosc przy pomocy '\n'
              document.querySelector("fieldset .langs").innerHTML = langs; //przypisujemy kod html w odpowiednie pole
              initWelcomeForm2();
            });
      }

    function initWelcomeForm2() {
        const form = document.querySelector("form.welcomeForm");

        form.addEventListener("submit", function(event) {
            event.preventDefault();
            const name = form.elements.name.value;
            const lang = form.elements.lang.value;
            //wysyla zapytanie do serwera o przywitanie
            fetch(`${API_URL}?name=${name}&lang=${lang}`)
                .then(response => response.text()) //oczekuje na odpowiedz z tekstem
                .then((text) => {                  //jezeli dostanie tekst
                  document.getElementById('welcome').innerHTML = `
                      <h1>${text}</h1>
                  `;
                  form.remove();
                  initTaskForm();
                });
        });
    }
    
    function initTaskForm() {
        document.querySelector('form.taskForm').style.display = 'block';
    }
    
    function processOkResponse(response = {}) {
        if (response.ok) {
          return response.json();
        }
        throw new Error(`Something got wrong: ${response.status}`);
    }
    
})();
