window.addEventListener("load", function () {
  /* ---------------------- obtenemos variables globales ---------------------- */
  const form = document.forms[0];
  const odontologos = this.document.getElementById("listar");
   const urlOdontologos = "https://localhost:8080/odontologos/listar";
    
  // console.log(listar);

  form.addEventListener("submit", function (event) {
    event.preventDefault();
    consultarPacientes();
   

    //Creamos el cuerpo de la request (petición al servidor)
    function consultarPacientes() {
      const settings = {
        method: "GET",
        headers: {
          authorization: token,
        },
      };
      console.log("consultando odontologos");
      fetch(urlOdontologos, settings)
        .then((response) => response.json())
        .then((odontologo) => {
          console.log("Estos son todos los odontologos");
          console.log(odontologo);

          renderizarOdontologos(odontologo);
         
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
            console.warn("Tarea inexistente");
            alert("Tarea inexistente");
          } else {
            console.error("Error del servidor");
            alert("Error del servidor");
          }
        });
    }
    function renderizarOdontologos(odontologos) {
      const odontologoCreado = document.querySelector(".odontologo-creado");
      odontologoCreado .innerHTML = " ";
      const cantOdontologos = document.getElementById("cantidad-odontologos");
  
      let contador = 0;
      cantOdontologos.textContent = contador;
  
      odontologos.forEach((odontologo) => {
          contador++;
        odontologoCreado.innerHTML += `
          <li class="odontologo">
          <div class="creado">
            <i class="fas fa-check-circle"></i> <!-- Cambiado de fa-regular fa-cicle-check a fas fa-check-circle -->
          </div>
          <div class="datosOdontologo">
            <p class="nombre completo">${odontologo.nombre} ${odontologo.apellido} ${odontologo.matricula}</p> <!-- Cambiado de competo a completo -->
          </div>
        </li>`;
    });
    }

  
     

});

  });

