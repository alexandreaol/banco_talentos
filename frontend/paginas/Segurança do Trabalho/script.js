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
const url = "https://api-sistemaalunos.onrender.com/api/aluno";

const nomeModal = modal.querySelector('.nome');
const cursoModal = modal.querySelector('.curso');
const descricaoModal = modal.querySelector('.descricao');
const infos = modal.querySelector('.infos');

function abrirModal(aluno) {
  modal.style.display = "flex";
  document.body.classList.add("modal-aberto");

  nomeModal.textContent = `${aluno.nome}`;
  cursoModal.textContent = `Curso: ${aluno.nomeCurso || "Não informado"}`;
  descricaoModal.textContent = aluno.descricao || "Sem descrição disponível";

  infos.innerHTML = ""

  const itens = [
    { label: "Email", value: aluno.email || "Não informado" },
    { label: "GitHub", value: aluno.github || "Não informado", isLink: true },
    { label: "LinkedIn", value: aluno.linkedin || "Não informado", isLink: true }
  ];

  itens.forEach(item => {
    const li = document.createElement('li');

    const strong = document.createElement('strong');
    strong.textContent = `${item.label}: `;
    li.appendChild(strong);

    if (item.isLink && item.value !== "Não informado") {
      const a = document.createElement('a');
      a.href = item.value;
      a.textContent = item.value;
      a.target = "_blank";
      li.appendChild(a);
    } else {
      li.appendChild(document.createTextNode(item.value));
    }

    infos.appendChild(li);
  });
}

function fecharModal() {
  modal.style.display = "none";
  document.body.classList.remove("modal-aberto");
}


fetch(url)
  .then(res => res.json())
  .then(alunos => {
    const container = document.getElementById('grid-portfolios');
    const modelo = document.querySelector('.cartao');

    alunos.forEach(aluno => {
      const cartao = modelo.cloneNode(true);
      cartao.classList.remove('modelo');
      cartao.style.display = 'block';

      cartao.querySelector('.foto').src = `${aluno.id}`;
      cartao.querySelector('.foto').alt = `Foto de ${aluno.nome} ${aluno.sobrenome}`;
      cartao.querySelector('.nome').textContent = `${aluno.nome}`;

      cartao.querySelector(".tag1").textContent = aluno.habilidade1;

      cartao.querySelector(".tag2").textContent = aluno.habilidade2;

      cartao.querySelector(".tag3").textContent = aluno.habilidade3;

      const botao = cartao.querySelector(".btn-portfolio");
      botao.addEventListener("click", () => abrirModal(aluno));

      container.appendChild(cartao);
    });

    if (alunos.length === 0) {
      container.innerHTML = '<p>Nenhum aluno encontrado.</p>';
    }
  })
  .catch(e => console.error('Erro:', e));
