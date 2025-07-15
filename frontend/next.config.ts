import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  images: {
    domains: ["i.imgur.com"], // 외부 이미지 허용
  },
};

export default nextConfig;
