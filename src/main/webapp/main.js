const form = document.querySelector("form");
const div = document.querySelector("div");

form.addEventListener("submit", function(event) {
    event.preventDefault();
    const name = form.elements.name.value;
    const lang = form.elements.lang.value;
    fetch(`http://localhost:8080/api?name=${name}&lang=${lang}`)
      .then(response => response.text())
      .then((text) => {
        div.innerHTML = `
            <h1>${text}</h1>
        `;
      });
})