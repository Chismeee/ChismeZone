document.getElementById("postar").onclick = function() {

    let categoria = document.getElementById("categoria").value;
    let nome = document.getElementById("nome").value;
    let msg = document.getElementById("mensagem").value;

    if (msg.trim() === "" || nome.trim() === "") return;

    const icones = {
        romance: "💋",
        treta: "⚡",
        alerta: "❗",
        fofoca: "💬"
    };

    let card = document.createElement("div");
    card.className = "chisme-card";

    card.innerHTML = `
        <div class="chisme-icon">${icones[categoria]}</div>

        <div class="chisme-texto">
            <strong style="color:#ff2ea6">${nome}</strong><br>
            ${msg}
        </div>

        <button class="btn-ver">Visualizar</button>
    `;

    document.getElementById("listaChismes").appendChild(card);

    document.getElementById("mensagem").value = "";
};
