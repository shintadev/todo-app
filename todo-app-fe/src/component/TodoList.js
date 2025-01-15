import React, { useState, useEffect, useCallback, useMemo } from 'react';
import { getTodos, createTodo, updateTodo, deleteTodo } from '../service/todoService.js';

const TodoList = () => {
  const [todos, setTodos] = useState([]);
  const [newTodo, setNewTodo] = useState('');
  const [filter, setFilter] = useState('all'); // 'all', 'pending', 'completed'
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchTodos = useCallback(async () => {
    try {
      setLoading(true);
      const { data } = await getTodos();
      setTodos(data.body || []);
    } catch (err) {
      console.error(err);
      setError('Failed to fetch todos');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchTodos();
  }, [fetchTodos]);

  const handleCreateTodo = async () => {
    if (!newTodo.trim()) return;
    try {
      const { data } = await createTodo({ title: newTodo, completed: false });
      setTodos((prev) => [...prev, data.body]);
      setNewTodo('');
    } catch (err) {
      console.error(err);
      setError('Failed to create todo');
    }
  };

  const handleToggleComplete = async (id) => {
    const todo = todos.find((t) => t.id === id);
    if (!todo) return;
    try {
      const { data } = await updateTodo(id, { completed: !todo.completed });
      setTodos((prev) =>
        prev.map((t) => (t.id === id ? { ...t, completed: data.body.completed } : t))
      );
    } catch (err) {
      console.error(err);
      setError('Failed to toggle completion');
    }
  };

  const handleDeleteTodo = async (id) => {
    try {
      await deleteTodo(id);
      setTodos((prev) => prev.filter((t) => t.id !== id));
    } catch (err) {
      console.error(err);
      setError('Failed to delete todo');
    }
  };

  const filteredTodos = useMemo(() => {
    if (filter === 'pending') return todos.filter((todo) => !todo.completed);
    if (filter === 'completed') return todos.filter((todo) => todo.completed);
    return todos;
  }, [todos, filter]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='todo-container'>
      <h1 className='todo-title'>Todo List</h1>
      {/* Filter buttons */}
      <div className='filter-buttons'>
        {['all', 'pending', 'completed'].map((status) => (
          <button
            key={status}
            className={`filter-btn ${filter === status ? 'active' : ''}`}
            onClick={() => setFilter(status)}
          >
            {status.charAt(0).toUpperCase() + status.slice(1)}
          </button>
        ))}
      </div>

      {/* Todo input */}
      <div className='todo-input'>
        <input
          type='text'
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
          placeholder='Add a new todo'
        />
        <button onClick={handleCreateTodo}>Add</button>
      </div>

      {/* Todo list */}
      <ul className='todo-list'>
        {filteredTodos.length === 0 ? (
          <li className='no-todos'>No todos available</li>
        ) : (
          filteredTodos.map((todo) => (
            <li key={todo.id} className='todo-item'>
              <span
                className={`todo-title ${todo.completed ? 'completed' : ''}`}
                onClick={() => handleToggleComplete(todo.id)}
              >
                {todo.title}
              </span>
              <button onClick={() => handleDeleteTodo(todo.id)} className='delete-btn'>
                ✕
              </button>
            </li>
          ))
        )}
      </ul>
    </div>
  );
};

export default TodoList;
