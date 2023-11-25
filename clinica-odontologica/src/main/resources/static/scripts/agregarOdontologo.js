window.addEventListener("load", function () {
  const nombre = document.querySelector("#nombre");
  const apellido = document.querySelector("#apellido");
  const matricula = document.querySelector("#matricula");
  const urlAgregar = "https://localhost:8080/odontologos/registrar";
  const formCrearOdontologo = document.forms[0];

  formCrearOdontologo.addEventListener("submit", function (event) {
    event.preventDefault();
    
    const payload = {
      firstName: nombre.value,
      lastName: apellido.value,
      matricula: matricula.value,      
      }
      
    console.log(payload);

    const settings = {
      method: "POST",
      body: JSON.stringify(payload),
      headers: {
        "Content-Type": "application/json",
      },
    };
    console.log("creo un nuevo odontologo");

    fetch(urlAgregar, settings)
      .then((response) => response.json())
      .then((odontologo) => {
        console.log(odontologo);
        //consultarodontologos();

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
      
      
      });
  });


