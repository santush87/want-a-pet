import { ZodType, z } from "zod";

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

export type LoginForm = {
  email: string;
  password: string;
};

export enum Country {
  BULGARIA = "Bulgaria",
  GERMANY = "Germany",
}

export enum TypeOfCustomer {
  PRIVATE = "Private",
  ORGANIZATION = "Organization",
}

export type UserRegisterDto = {
  email: string;
  firstName: string;
  lastName: string;
  password: string;
  confirmPassword: string;
  phoneNumber: string;
  userType: string;
  country: string;
  city: string;
  street: string;
  streetNumber: string;
};

// export type Address = {
//   country: "BULGARIA" | "GERMANY";
//   city: string;
//   street: string;
//   number: number;
// };

const nameLength = "Length must be between 2 and 15 characters!";

export const signUpSchema: ZodType<UserRegisterDto> = z
  .object({
    email: z.string().email(),
    firstName: z.string().min(2, nameLength).max(15, nameLength),
    lastName: z.string().min(2, nameLength).max(15, nameLength),
    phoneNumber: z
      .string()
      .min(10, "Phone number must be at least 10 digits!")
      .regex(/^[0-9]+$/, "Only numbers are allowed!"),
    country: z.string(),
    city: z.string().min(2),
    street: z.string(),
    streetNumber: z.string(),
    // country: z.nativeEnum(Country),
    userType: z.string(),
    password: z.string().min(8, "Password must be at least 8 characters!"),
    confirmPassword: z.string(),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: "Passwords must match!",
    path: ["confirmPassword"],
  });
