import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fetchUserData = createAsyncThunk("auth/fetchUserData", async (_, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    //api.defaults.headers.Authorization = `Bearer ${accessToken}`;
    const response = await api.get("/users/me");
    console.log(response);
    return { user: { ...response.data }, accessToken: state.auth.token };
  } catch (e) {
    return rejectWithValue("");
  }
});
export const updateUserCredential = createAsyncThunk(
  "auth/updateUserCredential",
  async ({ id, payload }, { getState, rejectWithValue }) => {
    try {
      const state = getState();
      api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
      const response = await api.put(`/users/${id}/change`, payload);
      console.log(response);
      return response.data;
    } catch (e) {
      return rejectWithValue("");
    }
  }
);

export const updateAuthUser = createAsyncThunk(
  "auth/updateAuthUser",
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

export const deleteAuthUser = createAsyncThunk("auth/deleteAuthUser", async ({ id }, { getState, rejectWithValue }) => {
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

export const login = createAsyncThunk("auth/login", async (payload) => {
  const response = await api.post("/auth/login", payload);
  return response.data;
});

export const signOut = createAsyncThunk("auth/signOut", async () => {});
