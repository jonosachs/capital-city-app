import { getCountryFromInput } from "./api.js";
import { getSuggestions } from "./service.js";

export function setupListeners() {
  const submit = document.getElementById("submit-btn");
  const input = document.getElementById("input");
  const output = document.getElementById("output");

  submit.addEventListener("click", async () => {
    clear();

    if (!input.value) {
      show("Please enter country");
      return;
    }

    show("Loading...");
    const country = await getCountryFromInput(input.value);
    show(country);
  });

  input.addEventListener("input", async () => {
    clear();
    if (!input.value) return;
    const suggestions = await getSuggestions(input.value);
    show(suggestions);
  });

  function clear() {
    output.textContent = "";
  }

  function show(msg) {
    if (msg instanceof Node) {
      output.replaceChildren(msg);
      return;
    }

    output.textContent = msg;
  }
}
