import { getCountry } from "../service.js";
import { jest, test, expect } from "@jest/globals";

import * as api from "../api.js";

test("returns valid country capital from input", async () => {
  jest.spyOn(api, "getCountryFromInput").mockResolvedValue({ capital: "Canberra" });

  await expect(getCountry("Australia")).resolves.toBe("Canberra");
  expect(api.getCountryFromInput).toHaveBeenCalledWith("Australia");
});

test("invalid country throws", async () => {
  jest.spyOn(api, "getCountryFromInput").mockRejectedValue(new Error());

  await expect(getCountry("NotACountry")).rejects.toThrow();
  expect(api.getCountryFromInput).toHaveBeenCalledWith("NotACountry");
});


