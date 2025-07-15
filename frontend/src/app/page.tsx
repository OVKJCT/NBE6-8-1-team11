"use client";

import 'bootstrap/dist/css/bootstrap.min.css';
import Image from 'next/image';
import { useState } from 'react';

export default function Home() {
  const [items] = useState([
    { id: 1, title: "Columbia Nariñó1", category: "커피콩1", price: 5000, image: "https://i.imgur.com/HKOFQYa.jpeg" },
    { id: 2, title: "Columbia Nariñó2", category: "커피콩2", price: 6000, image: "https://i.imgur.com/HKOFQYa.jpeg" },
    { id: 3, title: "Columbia Nariñó3", category: "커피콩3", price: 7000, image: "https://i.imgur.com/HKOFQYa.jpeg" },
    { id: 4, title: "Columbia Nariñó4", category: "커피콩4", price: 8000, image: "https://i.imgur.com/HKOFQYa.jpeg" },
  ]);

  const [cart, setCart] = useState<Record<number, number>>({});

  const handleAdd = (id: number) => {
    setCart(prev => ({ ...prev, [id]: (prev[id] || 0) + 1 }));
  };

  const handleRemove = (id: number) => {
    setCart(prev => {
      const qty = (prev[id] || 0) - 1;
      if (qty <= 0) {
        const { [id]: _, ...rest } = prev;
        return rest;
      }
      return { ...prev, [id]: qty };
    });
  };

  const totalPrice = Object.entries(cart).reduce((sum, [id, qty]) => {
    const item = items.find(i => i.id === +id);
    return sum + (item ? item.price * qty : 0);
  }, 0);

  return (
    <div className="container-fluid">
      <div className="row justify-content-center m-4">
        <h1 className="text-center">Grids & Circle</h1>
      </div>
      <div className="card">
        <div className="row">
          {/* 상품 목록 */}
          <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <h5><b>상품 목록</b></h5>
            <ul className="list-group products w-100">
              {items.map(item => (
                <li key={item.id} className="list-group-item d-flex mt-3">
                  <div className="col-2">
                    <Image className="img-fluid" src={item.image} alt="img" width={56} height={56} />
                  </div>
                  <div className="col">
                    <div className="row text-muted">{item.category}</div>
                    <div className="row">{item.title}</div>
                  </div>
                  <div className="col text-center price">{item.price.toLocaleString()}원</div>
                  <div className="col text-end action">
                    <button className="btn btn-outline-dark btn-sm" onClick={() => handleAdd(item.id)}>추가</button>
                  </div>
                </li>
              ))}
            </ul>
          </div>

          {/* 요약 카드 */}
          <div className="col-md-4 summary p-4">
            <h5><b>Summary</b></h5>
            <hr />
            <div className="row mb-3">
              {Object.entries(cart).map(([id, qty]) => {
                const item = items.find(i => i.id === +id);
                if (!item) return null;
                return (
                  <div key={id} className="d-flex align-items-center mb-2">
                    <span className="me-2">{item.title}</span>
                    <span className="badge bg-dark me-1">{qty}개</span>
                    <button
                      className="btn btn-outline-dark btn-sm me-1"
                      onClick={() => handleAdd(item.id)}
                    >+</button>
                    <button
                      className="btn btn-outline-danger btn-sm me-1"
                      onClick={() => handleRemove(item.id)}
                    >-</button>
                  </div>
                );
              })}
            </div>

            

            <form>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">이메일</label>
                <input type="email" className="form-control mb-1" id="email" />
              </div>
              <div className="mb-3">
                <label htmlFor="address" className="form-label">주소</label>
                <input type="text" className="form-control mb-1" id="address" />
              </div>
              <div className="mb-3">
                <label htmlFor="postcode" className="form-label">우편번호</label>
                <input type="text" className="form-control" id="postcode" />
              </div>
              <div>※ 당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
            </form>

            <div className="row pt-2 pb-2 border-top">
              <h5 className="col">총금액</h5>
              <h5 className="col text-end">{totalPrice.toLocaleString()}원</h5>
            </div>

            <button className="btn btn-dark col-12">결제하기</button>
          </div>
        </div>
      </div>

      <style jsx>{`
        body { background: #ddd; }
        .card {
          margin: auto;
          max-width: 950px;
          width: 90%;
          box-shadow: 0 6px 20px rgba(0,0,0,0.19);
          border-radius: 1rem;
          border: transparent;
        }
        .summary {
          background-color: #ddd;
          border-top-right-radius: 1rem;
          border-bottom-right-radius: 1rem;
          padding: 4vh;
          color: #414141;
        }
        @media (max-width: 767px) {
          .summary {
            border-top-right-radius: unset;
            border-bottom-left-radius: 1rem;
          }
        }
        .products .price, .products .action {
          line-height: 38px;
        }
      `}</style>
    </div>
  );
}
