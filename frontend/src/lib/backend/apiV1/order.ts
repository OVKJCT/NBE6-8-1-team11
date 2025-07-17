// src/lib/backend/apiV1/order.ts

import { apiFetch } from "@/lib/backend/client";

export interface OrderItem {
  productId: number;
  quantity: number;
}

export interface CreateOrderRequest {
  email: string;
  address: string;
  zipcode: string;
  items: OrderItem[];
}

export const createOrder = async (
    data: CreateOrderRequest
  ): Promise<any> => {
    const response = await fetch(`${process.env.NEXT_PUBLIC_API_BASE_URL}/api/orders/new`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
      credentials: "include",
    });
  
    const contentType = response.headers.get("Content-Type") || "";
  
    if (!response.ok) {
      if (contentType.includes("application/json")) {
        const errorData = await response.json();
        throw errorData;
      } else {
        const errorText = await response.text();
        throw new Error(errorText);
      }
    }
  
    if (contentType.includes("application/json")) {
      return await response.json();
    } else {
      return await response.text();
    }
  };
  