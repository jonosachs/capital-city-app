import { getCountryFromInput } from "../service.js";
import { jest, test, expect, afterEach } from "@jest/globals";

import * as api from "../api.js";

afterEach(() => {
  jest.restoreAllMocks();
});

test("valid country returns capital", async () => {
  jest.spyOn(api, "getCountry").mockResolvedValue({ capital: "Canberra" });

  await expect(getCountryFromInput("Australia")).resolves.toBe("Canberra");
  expect(api.getCountry).toHaveBeenCalledWith("Australia");
});

test("invalid country throws", async () => {
  expect(getCountryFromInput("NotACountry")).resolves.toMatch(/Error/);
});
