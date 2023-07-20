import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

// export const fetchUm = createAsyncThunk("um/fetchUm", async (accessToken, { rejectWithValue }) => {
//   try {
//     api.defaults.headers.Authorization = `Bearer ${accessToken}`;
//     const response = await api.get("/um");
//     return response.data;
//   } catch (e) {
//     return rejectWithValue("");
//   }
// });

export const fetchUms = createAsyncThunk("um/fetchUms", async (_, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.get("/um");
    return response.data;
  } catch (e) {
    return rejectWithValue("");
  }
});

export const updateUm = createAsyncThunk("um/updateUm", async ({ id, payload }, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.put(`/um/${id}`, payload);
    console.log(response);
    return { newUm: response.data };
  } catch (e) {
    return rejectWithValue("");
  }
});
export const createUm = createAsyncThunk("um/createUm", async ({ payload }, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.post(`/um`, payload);
    console.log(response);
    return { newUm: response.data };
  } catch (e) {
    return rejectWithValue("");
  }
});

export const deleteUm = createAsyncThunk("um/deleteUm", async ({ id }, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.delete(`/um/${id}`);
    console.log(response);
    return { id: id };
  } catch (e) {
    return rejectWithValue("");
  }
});
