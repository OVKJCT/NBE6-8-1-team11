import { apiFetch } from "@/lib/backend/client";

type Coffee = {
  id: number;
  coffeeName: string;
  coffeePrice: number;
};

export const getCoffeeList = async (): Promise<Coffee[]> => {
  return apiFetch("/api/coffees");
};

export interface CreateOrderRequest {
  orderId: string;
  quantity: number;
  email: string;
  address: string;
  zipCode: string;
}

export interface CreateOrderResponse {
  orderId: string;
  quantity: number;
  email: string;
  address: string;
  zipCode: string;
}

export const createOrder = async (
  data: CreateOrderRequest
): Promise<CreateOrderResponse> => {
  return await apiFetch("/api/order", {
    method: "POST",
    body: JSON.stringify(data),
  });
};