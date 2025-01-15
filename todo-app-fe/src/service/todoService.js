// filepath: /todo-app-fe/src/services/todoService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/todos';

const getTodos = () => axios.get(API_URL);
const getPendingTodos = () => axios.get(`${API_URL}/pending`);
const getCompletedTodos = () => axios.get(`${API_URL}/completed`);
const createTodo = (todo) => axios.post(API_URL, todo);
const updateTodo = (id, todo) => axios.put(`${API_URL}/${id}`, todo);
const deleteTodo = (id) => axios.delete(`${API_URL}/${id}`);
const deleteMultipleTodos = (ids) => axios.delete(`${API_URL}/delete-multi`, { data: ids });

export {
  getTodos,
  getPendingTodos,
  getCompletedTodos,
  createTodo,
  updateTodo,
  deleteTodo,
  deleteMultipleTodos,
};
