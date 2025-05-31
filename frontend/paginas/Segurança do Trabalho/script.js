const searchInput = document.querySelector(".search-wrapper input");
const cards = document.querySelectorAll(".cartao");
const filterButtons = document.querySelectorAll(".btn-filtro[data-filter]");
const modal = document.getElementById("modal-portfolio");
const btnToggleFiltros = document.getElementById("btn-toggle-filtros");
const filtros = document.getElementById("filtros");
const botoes = document.querySelectorAll(".btn-portfolio");

let activeFilter = "all";

// Filtra por texto e categoria
searchInput.addEventListener("input", aplicarFiltros);

filterButtons.forEach(btn => {
  btn.addEventListener("click", () => {
    activeFilter = btn.getAttribute("data-filter");
    aplicarFiltros();
  });
});

function aplicarFiltros() {
  const termo = searchInput.value.toLowerCase();

  cards.forEach(card => {
    const nome = card.querySelector("h3").textContent.toLowerCase();
    const tags = Array.from(card.querySelectorAll(".tag")).map(t => t.classList[1]);

    const combinaComBusca = nome.includes(termo);
    const combinaComFiltro = activeFilter === "all" || tags.includes(activeFilter);

    card.style.display = (combinaComBusca && combinaComFiltro) ? "block" : "none";
  });
}

// Modal: abrir
function abrirModal() {
  modal.style.display = "flex";
  document.body.classList.add("modal-aberto"); // trava scroll do fundo
}

// Modal: fechar
function fecharModal() {
  modal.style.display = "none";
  document.body.classList.remove("modal-aberto");
}

// Botões do portfólio abrem modal
botoes.forEach(botao => {
  botao.addEventListener("click", abrirModal);
});

// Toggle exibir/ocultar filtros
btnToggleFiltros.addEventListener("click", () => {
  filtros.classList.toggle("hidden");
});
