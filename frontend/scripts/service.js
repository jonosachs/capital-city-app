import { getAll, getCountryFromInput } from "./api.js";

export async function getCountry(inputValue) {
  try {
    const data = await getCountryFromInput(inputValue);
    const capital = data?.country?.capital ?? data?.capital ?? "";
    if (!capital) {
      return "Could not find capital";
    }
    return capital;
  } catch (error) {
    throw new Error("Error getting country: ", error.message);
  }
}

export async function getSuggestions(inputValue) {
  try {
    const all = await getAll();
    const filtered = all.filter((c) =>
      c.country.toLowerCase().startsWith(inputValue.toLowerCase())
    );

    //make suggestions clickable
    const container = document.createElement("div");
    filtered.forEach((f) => {
      const field = document.createElement("div");
      field.textContent = f.country;
      field.className = "suggestions";

      field.addEventListener("click", (event) => {
        const target = event.currentTarget;
        if (target && target.textContent) {
          document.getElementById("input").value = target.textContent;
        }
      });

      container.appendChild(field);
    });

    return container;
  } catch (error) {
    throw new Error("Error getting suggestions: " + error.message);
  }
}
