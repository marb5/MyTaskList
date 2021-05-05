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
              document.querySelector("fieldset.langs").innerHTML = langs; //przypisujemy kod html w odpowiednie pole
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
        const form = document.querySelector('form.taskForm');
        form.style.display = 'block';
        form.querySelector('input').value = "";
        fetch(`${API_URL}/tasks`)
                .then(processOkResponse)
                .then((taskArr) => {
                    const tasksHTML = taskArr.map(task => `<input type="checkbox" name="task" 
                                                  value="${task.id}" ${task.done ? "checked" : ""}>
                                                 <label>${task.name}</label><br>`).join('\n');
                    document.querySelector("fieldset.tasks").innerHTML = tasksHTML;
                    initTaskForm2();
                });
    }
    
    function initTaskForm2() {
        const form = document.querySelector('form.taskForm');
        const tasksListeners = form.querySelectorAll('input[type="checkbox"]');
        tasksListeners.forEach(function(task) {
            task.addEventListener("click", (e) => {
                e.preventDefault();
                fetch(`${API_URL}/tasks/${task.value}`, { method: 'PUT' })
                    .then(processOkResponse)
                    .then(updatedTask => task.checked = updatedTask.done)
                    .catch(console.warn);
            });
        });
    }
    
    function processOkResponse(response = {}) {
        if (response.ok) {
          return response.json();
        }
        throw new Error(`Something got wrong: ${response.status}`);
    }
    
})();
