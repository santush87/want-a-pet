export type Pet = {
  id: number;
  name: string;
  breed: string;
};

export type User = {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
};

export type UserRegisterDto = {
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  type: "PRIVATE" | "BUSINESS";
  address: Address;
};

export type Address = {
  country:
    | "BULGARIA"
    | "ROMANIA"
    | "GREECE"
    | "GERMANY"
    | "SPAIN"
    | "SWITZERLAND";
  city: string;
  street: string;
  number: number;
};
