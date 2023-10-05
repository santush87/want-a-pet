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
  phoneNumber: string;
  type: "PRIVATE" | "BUSINESS";
  country: "BULGARIA" | "GERMANY";
  city: string;
  street: string;
  number: number;
};

// export type Address = {
//   country: "BULGARIA" | "GERMANY";
//   city: string;
//   street: string;
//   number: number;
// };
