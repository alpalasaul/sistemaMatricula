const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const contenedor = document.querySelector(".contenedor");
const exp = document.querySelector("#exp");
const regresarInicio = document.querySelector("#regresarInicio");




sign_up_btn.addEventListener("click", () => {
  contenedor.classList.add("registro-mode");
});

sign_in_btn.addEventListener("click", () => {
  contenedor.classList.remove("registro-mode");
});



exp.addEventListener("click", () => {
  contenedor.classList.add("curso-mode");
});


regresarInicio.addEventListener("click", () => {
  contenedor.classList.remove("curso-mode");
});
function pulsar() {
alert("Usuario registrado con éxito");
location.reload();
}




