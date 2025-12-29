/* 
<div id="poke-input-container">
    <h3>Pokemon ID:</h3>
    <input id="poke-input" type="number">
    <br>
    <button id="poke-input-button">Search</button> 
</div>

<div id="poke-display-container">
</div>

<script src="pokemon.js"></script> 
*/

const pokeInputContainer = document.getElementById("poke-input-container");
const pokeDisplayContainer = document.getElementById("poke-display-container");


const pokeInput = pokeInputContainer.children[1];
const pokeButton = pokeInput.nextElementSibling.nextElementSibling;

console.log(pokeInput);
console.log(pokeButton);

pokeButton.addEventListener("click", async () => {
    const pokeId = pokeInput.value;
    console.log(pokeId);

    const pokeData = await fetchPoke(pokeId);

    displayPoke(pokeData);
});


async function fetchPoke(id) {
    try {
        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${id}`);
        const data = await response.json();
        console.log(data);
        return data;
    }catch(error){
        console.error(error);
    }
}

function displayPoke(data){
    pokeDisplayContainer.innerHTML = "";
    const pokeName = document.createElement('h3');
    pokeName.textContent = data.name;
    pokeDisplayContainer.appendChild(pokeName);

    const pokeImage = document.createElement('img');
    pokeImage.src = data.sprites.front_default;
    pokeDisplayContainer.appendChild(pokeImage);
}