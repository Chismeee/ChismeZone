import { getDatabase, ref, push, onValue } from "https://www.gstatic.com/firebasejs/12.6.0/firebase-database.js";

// referência ao database
const database = getDatabase();

const icones = {
    romance: "💋",
    treta: "⚡",
    alerta: "❗",
    fofoca: "💬"
};

// Enviar post para Firebase
document.getElementById("postar").onclick = function() {
    let categoria = document.getElementById("categoria").value;
    let nome = document.getElementById("nome").value;
    let msg = document.getElementById("mensagem").value;

    if (msg.trim() === "" || nome.trim() === "") return;

    push(ref(database, 'chismes'), {
        categoria: categoria,
        nome: nome,
        msg: msg,
        timestamp: Date.now()
    });

    document.getElementById("mensagem").value = "";
};

// Carregar posts em tempo real
const lista = document.getElementById("listaChismes");

onValue(ref(database, 'chismes'), snapshot => {
    lista.innerHTML = '';
    const data = snapshot.val();
    if (!data) return;

    const posts = Object.values(data).sort((a, b) => b.timestamp - a.timestamp);
    posts.forEach(p => {
        let card = document.createElement("div");
        card.className = "chisme-card";

        card.innerHTML = `
            <div class="chisme-icon">${icones[p.categoria]}</div>
            <div class="chisme-texto">
                <strong style="color:#ff2ea6">${p.nome}</strong><br>
                ${p.msg}
            </div>
            <button class="btn-ver">Visualizar</button>
        `;
        lista.appendChild(card);
    });

    lista.scrollTop = lista.scrollHeight; // mantém o scroll sempre no final
});
