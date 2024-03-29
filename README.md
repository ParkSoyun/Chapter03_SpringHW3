# 항해99_Chapter3_Spring과제
## 📌 과제 안내
- [Spring 심화 주차 개인 과제](https://www.notion.so/Spring-0ec78304350e40bc915b05f09eedc394)

## 📌 목표
```
💡 Goal:  " 요구사항에 맞춰 배달서비스 API를 구성해보기"
```

## 📌 요구사항
- `1) 음식점 등록 및 조회`,  `2) 음식 등록 및 메뉴판 조회`, `3) 주문요청하기 및 주문 조회`를 모두 완수해야 합니다.
- 스프링 서버를 통해  아래 요구사항에 맞춰 배달앱 API 를 구현합니다.
- 크게 3개의 요구사항으로 나뉘어져 있고, 제공되는 테스트 코드가 모두 성공적으로 작동하게 되면 과제 완료입니다!

### 1) 음식점 등록 및 조회
1. 음식점 정보 입력받아 등록
    - 음식점 이름 (name)
    - 최소주문 가격 (minOrderPrice)
        - 허용값: 1,000원 ~ 100,000원 입력
        - 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
        - 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
    - 기본 배달비 (deliveryFee)
        - 허용값: 0원 ~ 10,000원 (예. 11,000원 입력 시 에러발생.)
        - 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능)
        
2. 음식점 조회
    - 등록된 모든 음식점 정보 조회 가능
        - 등록 시 입력한 음식점 정보 (name, minOrderPrice, deliveryFee)
        - DB 테이블 ID (id)
        
![image](https://user-images.githubusercontent.com/50862493/179973273-99e9b8ff-68f1-44fc-a575-e19fc85c639f.png)

### 2) 음식 등록 및 메뉴판 조회
1. 음식점 ID 및 음식 정보 입력받아 등록
    - 음식점 ID (restaurantId)
        - 음식점 DB 테이블 ID
    - 음식명 (name)
        - 같은 음식점 내에서는 음식 이름이 중복될 수 없음 (예. '자담치킨 강남점'에 '후라이드치킨' 이 이미 등록되어 있다면 중복하여 등록할 수 없지만, 다른 음식점인 '맘스터치 강남점'에는 '후라이드치킨' 을 등록 가능)
    - 가격 (price)
        - 허용값: 100원 ~ 1,000,000원
        - 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
        - 허용값이 아니거나 100원 단위 입력이 아닌 경우 에러 발생시킴
        
2. 메뉴판 조회
    - 하나의 음식점에 등록된 모든 음식 정보 조회
        - 등록 시 입력한 음식 정보 (name, price)
        - DB 테이블 ID (id)
        
![image](https://user-images.githubusercontent.com/50862493/179974157-91a2658d-e2a8-4b0a-8c5c-744489d6b044.png)

### 3) 주문 요청하기 및 주문 조회
1. 주문 요청 시 배달 음식점 및 음식 정보 입력받음
    - 음식점 ID (restaurantId)
    - 음식 주문 정보 (foods)
        - 음식 ID (id)
        - 음식을 주문할 수량 (quantity)
            - 허용값: 1 ~ 100
            - 허용값이 아니면 에러 발생시킴
            
2. 주문 요청에 대한 응답으로 다음 정보를 포함시킴 
    - 주문 음식점 이름 (restaurantName)
    - 주문 음식 정보 (foods)
        - 주문 음식명 (name)
        - 주문 수량 (quantity)
        - 주문 음식의 가격 (price)
            - 계산방법
                - 주문 음식 1개의 가격 * 주문 수량
    1. 배달비 (deliveryFee)
        - 주문 음식점의 기본 배달비
    2. 최종 결제 금액 (totalPrice)
        - 계산방법
            - 주문 음식 가격들의 총 합 + 배달비
        - "주문 음식 가격들의 총 합" 이 주문 음식점의 "최소주문 가격" 을 넘지 않을 시 에러 발생시킴
        
3. 주문 조회
    - 그동안 성공한 모든 주문 요청을 조회 가능
    
![image](https://user-images.githubusercontent.com/50862493/179974378-a89f982c-2f77-45f4-877d-63c1f7bb750a.png)

## 📌 데이터베이스 설계
### 테이블
![image](https://user-images.githubusercontent.com/50862493/179980065-98e804b9-4210-4e8f-a613-f3a7072b8650.png)
![image](https://user-images.githubusercontent.com/50862493/179980152-1db24319-8fc8-48db-a684-c9071621bbf1.png)
![image](https://user-images.githubusercontent.com/50862493/179980223-49c011be-0eea-4367-af93-a271c5fc7b4d.png)
![image](https://user-images.githubusercontent.com/50862493/179980289-afd38119-41b0-422b-a55d-a54c27d06453.png)

### ERD
![spring-hw3-ERD](https://user-images.githubusercontent.com/50862493/180258494-e66f2318-dd53-47f5-8759-7e229d5dd879.png)



