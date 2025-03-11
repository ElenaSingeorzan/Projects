const api = axios.create({
    baseURL: 'http://localhost:8081', 
});


export const registerUser = (userData) => {
    return api.post('/users', userData);
};
export const loginUser = (userData) => {
    return api.post('/login', userData);
};

import axios from 'axios';
const API_URL = 'http://localhost:8081';


const token = localStorage.getItem('jwtToken');

console.log(token);

export const getPosts = (token) => {
    return axios.get(`${API_URL}/posts`, {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

export const getPostsforUser = (token) => {
  return axios.get(`${API_URL}/users/posts`, {
      headers: {
          Authorization: `Bearer ${token}`, 
      },
  });
};

export const createPost = (postData, token) => {
    return axios.post(`${API_URL}/posts`, postData, {
        headers: {
            Authorization: `Bearer ${token}`, 
        },
    });
};
export const updatePost = (postId, postData, token) => {
  return axios.put(`${API_URL}/posts/${postId}`, postData, {
    headers: {
      Authorization: `Bearer ${token}`, 
    },
  });
};

export const deletePost = (postId, token) => {
  return axios.delete(`${API_URL}/posts/${postId}`, {
    headers: {
      Authorization: `Bearer ${token}`, 
    },
  });
};

export const addComment = (postId, commentData, token) => {
  return axios.post(`${API_URL}/posts/${postId}/comments`,
    commentData, 
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );
};

