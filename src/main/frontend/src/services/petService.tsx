import * as request from "./requester";

const baseUrl = "http://localhost:8080";

export const getAllPets = async () => {
  const result = await request.get(baseUrl);
  const pets = Object.values(result);

  return pets;
};
