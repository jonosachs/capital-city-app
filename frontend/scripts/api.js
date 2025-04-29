async function getCountry(country) {
  const encodedCountry = encodeURIComponent(country.trim());
  const response = await fetch(
    `http://localhost:8080/countries/${encodedCountry}`
  );

  const jsonResponse = await response.json();

  if (!response.ok) {
    throw new Error(jsonResponse.message);
  }

  return jsonResponse;
}

async function getCountries() {
  const response = await fetch(`http://localhost:8080/countries`);
  const jsonResponse = await response.json();

  if (!response.ok) {
    throw new Error(jsonResponse.message);
  }

  return jsonResponse;
}

export { getCountry, getCountries };
