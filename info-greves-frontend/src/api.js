import axios from 'axios';


const API_URL = 'http://localhost:9080'; 

export const getAllGreves = () => axios.get(`${API_URL}/buscar/greves`);
export const getGreveById = (id) => axios.get(`${API_URL}/buscar/greve/${id}`);
export const createGreve = (data) => axios.post(`${API_URL}/cadastrar/greve`, data);
export const updateGreve = (id, data) => axios.put(`${API_URL}/atualizar/greve/${id}`, data);
export const deleteGreve = (id) => axios.delete(`${API_URL}/deletar/greve/${id}`);
export const updateGreveFinal = (id, data) => axios.patch(`${API_URL}/atualizar/fim/greve/${id}`, data);
