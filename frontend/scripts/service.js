import { getAll, getCountry } from "./api.js";

export async function getCountryFromInput(inputValue) {
  try {
    const data = await getCountry(inputValue);
    const capital = data?.country?.capital ?? data?.capital ?? "";
    if (!capital) {
      return "Could not find capital";
    }
    return capital;
  } catch (error) {
    return "Error getting country: ", error.message;
  }
}

export async function getSuggestions(inputValue) {
  try {
    const all = await getAll();

    const filtered = all.filter((c) =>
      c.country.toLowerCase().startsWith(inputValue.toLowerCase())
    );
    if (filtered.length == 0) return "No matches";

    const container = document.createElement("div");

    filtered.forEach((f) => {
      const field = document.createElement("div");
      field.textContent = f.country;
      field.className = "suggestions";

      //make suggestions clickable
      field.addEventListener("click", async (event) => {
        const target = event.currentTarget;

        if (target && target.textContent) {
          document.getElementById("input").value = target.textContent;
          container.replaceChildren("");

          return container;
        }
      });

      container.appendChild(field);
    });

    return container;
  } catch (error) {
    return "Error getting suggestions: " + error.message;
  }
}
