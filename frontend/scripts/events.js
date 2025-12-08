import { getCountryFromInput, getSuggestions } from "./service.js";

export function setupListeners() {
  const submit = document.getElementById("submit-btn");
  const input = document.getElementById("input");
  const output = document.getElementById("output");

  input.addEventListener("input", async () => {
    clearOutput();
    if (!input.value) return;
    var sugggestions = await getSuggestions(input.value);
    show(sugggestions);
  });

  submit.addEventListener("click", async () => {
    clearOutput();
    if (!input.value) return;
    var capital = await getCountryFromInput(input.value);
    show(capital);
  });
}

function clearOutput() {
  output.textContent = "";
}

function show(msg) {
  if (msg instanceof Node) {
    output.replaceChildren(msg);
    return;
  }

  output.textContent = msg;
}
