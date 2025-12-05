export async function getCountryFromInput(country) {
  const encodedCountry = encodeURIComponent(country.trim());
  const response = await fetch(`http://localhost:8080/countries/${encodedCountry}`);
  const jsonResponse = await response.json();

  if (!response.ok) {
    throw new Error(jsonResponse.message || "Unable to fetch country");
  }

  return jsonResponse;
}

export async function getAll() {
  const response = await fetch(`http://localhost:8080/countries`);
  const jsonResponse = await response.json();

  if (!response.ok) {
    throw new Error(jsonResponse.message || "Unable to fetch country list");
  }

  return jsonResponse;
}
