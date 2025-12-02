import { getCountries, getCountry } from "./api.js";

export function setupListeners() {
  const btn = document.getElementById("submit-btn");
  const input = document.getElementById("input");
  const output = document.getElementById("output");
  const suggestions = document.getElementById("suggestions");

  btn.addEventListener("click", async () => {
    suggestions.innerHTML = "";

    if (!input.value) {
      output.textContent = "Please enter country";
      return;
    }

    output.textContent = "Loading...";

    try {
      const country = await getCountry(input.value);
      if ((country.city === "") || (country.city == null)) {
        output.textContent = "Could not find capital";
      } else {
        output.textContent = country.city;
      }
    } catch (error) {
      output.textContent = error.message;
    }
  });

  input.addEventListener("input", async () => {
    if (!input.value) {
      suggestions.innerHTML = "";
      output.textContent = "";
      return;
    }

    output.textContent = "";
    suggestions.innerHTML = "";

    const countries = await getCountries();

    const filtered = countries.filter((country) =>
      country.country.toLowerCase().startsWith(input.value.toLowerCase())
    );

    filtered.forEach((country) => {
      const item = document.createElement("div");
      item.textContent = country.country;
      item.className = "suggestions";
      item.addEventListener("click", () => {
        input.value = country.country;
        suggestions.innerHTML = "";
      });

      suggestions.appendChild(item);
    });
  });
}
