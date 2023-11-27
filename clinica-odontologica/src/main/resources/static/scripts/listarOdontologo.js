window.addEventListener("load", function () {
  window.addEventListener("load", function () {
    /* ---------------------- obtenemos variables globales ---------------------- */

    const form = document.forms[0];
     const urlOdontologos = "http://localhost:8081/odontologos/listar";
     const odontologoCreado = document.querySelector(".odontologos");

    form.addEventListener("submit", function (event) {
      event.preventDefault();
      consultarOdontologos();
    })

      function consultarOdontologos() {

        const settings = {
          method: "GET",
        };
        console.log("consultando odontologos");
        fetch(urlOdontologos, settings)
          .then((response) => response.json())
          .then((odontologo) => {
            console.log("Estos son todos los odontologos");
            console.log(odontologo);
            renderizarOdontologos(odontologo)

          })

          .catch((err) => {
            console.warn("Promesa rechazada");
            console.log(err);
            if (err.status == 400) {
              console.warn("id inválido");
              alert("id inválido");
            } else if (err.status == 401) {
              console.warn("Requiere Autorización");
              alert("Requiere Autorización");
            } else if (err.status == 404) {
              console.warn("odontologo inexistente");
              alert("odontologo inexistente");
            } else {
              console.error("Error del servidor");
              alert("Error del servidor");
            }
          });
      }
      function renderizarOdontologos(odontologo) {
        console.log(odontologoCreado);
        odontologoCreado.innerHTML = "";

        const listaOdontologos = document.createElement("ul");

        odontologo.forEach((odontolog) => {
          const elementoOdontologo = document.createElement("li")
          elementoOdontologo.classList.add('skeleton-container')
          elementoOdontologo.innerHTML = `
            <span >Nombre: ${odontolog.nombre}</span>
            <span>Apellido: ${odontolog.apellido}</span>
            <span>Número de Matrícula: ${odontolog.numeroMatricula}</span>`;

          listaOdontologos.appendChild(elementoOdontologo);
        });

        odontologoCreado.appendChild(listaOdontologos);
      }



    })


