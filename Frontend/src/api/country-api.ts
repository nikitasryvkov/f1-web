import { Country } from "../entities/country";
import { http } from "./common";

const BASE_PATH = "/api/country";

export const findAllCountries = async (): Promise<Country[]> => {
  const data = await http.get(`${BASE_PATH}/all`);

  return data.data;
};

export const fetchCountry = async (countryId: string): Promise<Country> => {
  const response = await http.get(`${BASE_PATH}/id/${countryId}`);
  return response.data;
};
