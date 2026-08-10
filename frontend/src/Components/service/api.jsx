import axios from 'axios';

const getAuthHeaders = () => {
    const token = localStorage.getItem('token');
    return token ? { Authorization: `Bearer ${token}` } : {};
};

const base_Url = "https://crm-system-production-9f24.up.railway.app/api/users";

export const addUser = async (users) =>
    await axios.post(base_Url, users, { headers: getAuthHeaders() });

export const getAllUsers = async () =>
    await axios.get(base_Url, { headers: getAuthHeaders() });

export const getUserById = async (id) =>
    await axios.get(`${base_Url}/${id}`, { headers: getAuthHeaders() });

export const deleteUserById = async (id) =>
    await axios.delete(`${base_Url}/${id}`, { headers: getAuthHeaders() });

export const updateUser = async (id, users) =>
    await axios.put(`${base_Url}/${id}`, users, { headers: getAuthHeaders() });

const base_UrlCustomer = "https://crm-system-production-9f24.up.railway.app/api/users";

export const addCustomer = async (customers) =>
    await axios.post(base_UrlCustomer, customers, { headers: getAuthHeaders() });

export const getAllCustomers = async () =>
    await axios.get(base_UrlCustomer, { headers: getAuthHeaders() });

export const getCustomerById = async (id) =>
    await axios.get(`${base_UrlCustomer}/${id}`, { headers: getAuthHeaders() });

export const updateCustomer = async (id, customers) =>
    await axios.put(`${base_UrlCustomer}/${id}`, customers, { headers: getAuthHeaders() });

export const deleteCustomer = async (id) =>
    await axios.delete(`${base_UrlCustomer}/${id}`, { headers: getAuthHeaders() });

const Base_URLLead = "https://crm-system-production-9f24.up.railway.app/api/leads";

export const getAlllead = async () =>
    await axios.get(Base_URLLead, { headers: getAuthHeaders() });

export const createLead = async (leads) =>
    await axios.post(Base_URLLead, leads, { headers: getAuthHeaders() });

export const deleteLead = async (id) =>
    await axios.delete(`${Base_URLLead}/${id}`, { headers: getAuthHeaders() });

export const updateLead = async (id, leads) =>
    await axios.put(`${Base_URLLead}/${id}`, leads, { headers: getAuthHeaders() });