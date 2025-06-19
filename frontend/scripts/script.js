document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formCadastro");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        // Função para formatar a data
        function formatarData(dataIso) {
            const partes = dataIso.split("-");
            return `${partes[2]}/${partes[1]}/${partes[0]}`;
        }

        // Pegando os valores do curso
        const cursoSelect = document.getElementById("curso");
        const cursoId = parseInt(cursoSelect.value);
        const cursoNome = cursoSelect.options[cursoSelect.selectedIndex].text;

        // Criando o objeto aluno
        const aluno = {
            nome: document.getElementById("nome").value,
            idade: parseInt(document.getElementById("idade").value),
            descricao: document.getElementById("descricao").value,
            telefone: document.getElementById("telefone").value,
            email: document.getElementById("email").value,
            github: document.getElementById("github").value,
            habilidade1: document.getElementById("habilidade1").value,
            habilidade2: document.getElementById("habilidade2").value,
            habilidade3: document.getElementById("habilidade3").value,
            imagem: document.getElementById("imagem").value,
            dataFormatura: formatarData(document.getElementById("formatura").value),
            cursoId: cursoId,
            cursoNome: cursoNome
        };

        console.log("Dados enviados:", aluno);

        try {
            const response = await fetch("https://api-sistemaalunos.onrender.com/api/aluno", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(aluno)
            });

            if (response.ok) {
                alert("Aluno cadastrado com sucesso!");
                form.reset();
            } else {
                const erro = await response.text();
                alert("Erro ao cadastrar aluno: " + erro);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao conectar com o servidor.");
        }
    });
});
