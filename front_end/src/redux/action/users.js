import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fetchUsers = createAsyncThunk("users/fetchUsers", async (_, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.get("/users");
    return { ...response.data };
  } catch (e) {
    return rejectWithValue("");
  }
});

export const updateUser = createAsyncThunk(
  "users/updateUser",
  async ({ id, payload }, { getState, rejectWithValue }) => {
    try {
      const state = getState();
      api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
      const response = await api.put(`/users/${id}`, payload);
      console.log(response);
      return { user: { ...response.data } };
    } catch (e) {
      return rejectWithValue("");
    }
  }
);
export const createUser = createAsyncThunk(
  "users/updateUserData",
  async ({ id, payload }, { getState, rejectWithValue }) => {
    try {
      const state = getState();
      api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
      const response = await api.put(`/users/${id}`, payload);
      console.log(response);
      return { user: { ...response.data } };
    } catch (e) {
      return rejectWithValue("");
    }
  }
);

export const deleteUser = createAsyncThunk("users/deleteUser", async ({ id }, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;

    const response = await api.delete(`/users/${id}`);
    console.log(response);
    return {};
  } catch (e) {
    return rejectWithValue("");
  }
});
