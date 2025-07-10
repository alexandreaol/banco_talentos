const searchInput = document.querySelector(".search-wrapper input");
const filterButtons = document.querySelectorAll(".btn-filtro[data-filter]");
const modal = document.getElementById("modal-portfolio");
let activeFilter = "all";
const btnToggleFiltros = document.getElementById("btn-toggle-filtros");
const divFiltros = document.getElementById("filtros");

btnToggleFiltros.addEventListener("click", () => {
  divFiltros.classList.toggle("hidden");
});


searchInput.addEventListener("input", aplicarFiltros);

filterButtons.forEach(btn => {
  btn.addEventListener("click", () => {
    activeFilter = btn.getAttribute("data-filter");
    aplicarFiltros();
  });
});

function normalizar(texto) {
  return texto.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "");
}

function aplicarFiltros() {
  const termo = normalizar(searchInput.value);
  const todosCartoes = document.querySelectorAll(".cartao:not(.cartaomodelo)");

  todosCartoes.forEach(card => {
    const nome = normalizar(card.querySelector("h3").textContent);
    const tags = Array.from(card.querySelectorAll(".tag")).map(t => normalizar(t.textContent));

    const combinaComBusca = nome.includes(termo);
    const combinaComFiltro = activeFilter === "all" || tags.includes(normalizar(activeFilter));

    card.style.display = (combinaComBusca && combinaComFiltro) ? "block" : "none";
  });
}


function abrirModal(aluno) {
  modal.style.display = "flex";
  document.body.classList.add("modal-aberto");

  modal.querySelector('.nome').textContent = aluno.nome;
  modal.querySelector('.curso').textContent = `Curso: ${aluno.nomeCurso || "Não informado"}`;
  modal.querySelector('.descricao').textContent = aluno.descricao || "Sem descrição disponível";

  const infos = modal.querySelector('.infos');
  infos.innerHTML = "";

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

fetch("https://api-sistemaalunos.onrender.com/api/aluno")
  .then(res => res.json())
  .then(alunos => {
    const container = document.getElementById('grid-portfolios');
    const modelo = document.querySelector('.cartao.cartaomodelo');

    // 👉 Filtra somente alunos com cursoId === 1
    const alunosFiltrados = alunos.filter(aluno => aluno.cursoId === 1);

    alunosFiltrados.forEach(aluno => {
      const cartao = modelo.cloneNode(true);
      cartao.classList.remove('cartaomodelo');
      cartao.style.display = 'block';

      const foto = cartao.querySelector('.foto');
      foto.src = aluno.imagem || "https://i.pravatar.cc/100";
      foto.alt = `Foto de ${aluno.nome}`;

      cartao.querySelector('.nome').textContent = aluno.nome;
      cartao.querySelector(".tag1").textContent = aluno.habilidade1 || "";
      cartao.querySelector(".tag2").textContent = aluno.habilidade2 || "";
      cartao.querySelector(".tag3").textContent = aluno.habilidade3 || "";

      cartao.querySelector(".btn-portfolio").addEventListener("click", () => abrirModal(aluno));
      container.appendChild(cartao);
    });

    // Mensagem se nenhum aluno foi encontrado com cursoId = 1
    if (alunosFiltrados.length === 0) {
      container.innerHTML = '<p>Nenhum aluno encontrado para esse curso.</p>';
    }
  })
  .catch(e => {
    console.error('Erro:', e);
    document.getElementById('grid-portfolios').innerHTML = '<p>Erro ao carregar os alunos.</p>';
  });

