# CRM Frontend 🖥️

A modern, responsive frontend for a full-stack CRM system built with **React.js**. Provides role-based dashboards and UI for managing leads, tickets, feedback, and email communication.

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| UI Library | React.js |
| Routing | React Router DOM |
| State Management | React Hooks (useState, useEffect) |
| HTTP Client | Axios |
| Styling | CSS |
| Build Tool | Create React App (npm) |

---

## ✨ Features

- **Role-Based Dashboards** — Distinct views for Admin, User, and Customer roles
- **Lead Management** — View, create, update, and track leads through the pipeline
- **Ticket System** — Submit and monitor support tickets
- **Feedback Module** — Submit and review customer feedback
- **Email Composer** — Send emails directly from the CRM interface
- **JWT Auth Integration** — Secure login with token-based session handling
- **Protected Routes** — Route guards redirect unauthorized users

---

## 📁 Project Structure

```
src/
├── components/          # Reusable UI components (Navbar, Sidebar, etc.)
├── pages/               # Page-level components per role/feature
│   ├── admin/           # Admin dashboard & management pages
│   ├── user/            # User dashboard pages
│   └── customer/        # Customer portal pages
├── services/            # Axios API call functions
├── context/             # Auth context / global state
├── utils/               # Helper functions, auth helpers
├── App.js               # Root component with routing
└── index.js             # Entry point
```

---

## ⚙️ Prerequisites

- Node.js 16+
- npm 8+
- [crm-backend](https://github.com/sanket9322/crm-backend) running on `https://crm-system-production-9f24.up.railway.app`

---

## 🔧 Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/sanket9322/crm-frontend.git
cd crm-frontend
```

### 2. Install dependencies

```bash
npm install
```

### 3. Configure the API base URL

If your backend runs on a different port or host, update the base URL in `src/services/` or create a `.env` file:

```env
REACT_APP_API_URL=https://crm-system-production-9f24.up.railway.app
```

### 4. Start the development server

```bash
npm start
```

App runs at `http://localhost:3000`.

---

## 📜 Available Scripts

| Script | Description |
|---|---|
| `npm start` | Run app in development mode |
| `npm test` | Launch test runner in watch mode |
| `npm run build` | Build optimized production bundle |
| `npm run eject` | Eject from Create React App (irreversible) |

---

## 🔐 Authentication Flow

1. User submits credentials on the Login page
2. Backend returns a JWT token
3. Token is stored (e.g. `localStorage`) and attached to all subsequent API requests via Axios interceptors
4. Protected routes check for valid token; unauthorized users are redirected to `/login`
5. Role is decoded from the token to render the correct dashboard

---

## 👤 Roles & Dashboards

| Role | Access |
|---|---|
| **Admin** | Full access — manage users, leads, tickets, feedback, and email |
| **User** | Manage assigned leads and tickets |
| **Customer** | Submit tickets and feedback; view own records |

---

## 🌐 Connecting to Backend

Make sure the [crm-backend](https://github.com/sanket9322/crm-backend) is running before starting the frontend. CORS is configured on the backend to accept requests from `http://localhost:3000`.

---

## 🏗️ Production Build

```bash
npm run build
```

Outputs a minified, optimized build in the `/build` folder, ready to serve via Nginx, Apache, or any static hosting provider (Netlify, Vercel, etc.).

---

## 🤝 Related Repository

Backend: [crm-backend](https://github.com/sanket9322/crm-backend) — Spring Boot REST API for this frontend.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
