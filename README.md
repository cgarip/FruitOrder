# 訂單管理系統 (Order Management System)

## 📌 介紹
本專案是一個基於 **Java Swing** 和 **JDBC** 開發的簡單訂單管理系統，支援 **CRUD 操作**（新增、更新、刪除、查詢訂單），並提供 **即時時間顯示** 和 **使用者登入資訊**。  

---

## 🔧 主要功能
✅ **使用者登入**：顯示當前登入者資訊（姓名、電話、Email）  
✅ **新增訂單**：輸入水果名稱、數量、價格，點擊「新增」按鈕儲存至資料庫  
✅ **更新訂單**：選擇現有訂單，修改後點擊「更新」按鈕  
✅ **刪除訂單**：點擊 **每筆訂單最左側的刪除按鈕** 直接刪除訂單  
✅ **清空輸入欄位**：點擊「清空」按鈕可快速清除所有輸入欄位  
✅ **列印訂單列表**：支援列印整個訂單表格  
✅ **即時時間顯示**：右上角顯示當前時間，每秒自動更新  
✅ **登出功能**：點擊「登出」返回登入畫面  

---

## 🛠 技術棧
- **程式語言**：Java  
- **GUI 框架**：Swing  
- **資料庫**：MySQL（可更改為其他 SQL 資料庫）  
- **JDBC 連接**：使用 `OrderDAO` 進行資料庫操作  

---

## 💻 環境設定
### 1️⃣ 安裝 Java & MySQL
請確保已安裝 **Java 8+** 以及 **MySQL**。

### 2️⃣ 設定資料庫
在 MySQL 建立資料表：
```sql
CREATE DATABASE order_management;
USE order_management;

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fruit_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    user_id INT NOT NULL
);
