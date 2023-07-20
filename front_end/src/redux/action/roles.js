import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fetchRoles = createAsyncThunk("roles/fetchRoles", async (_, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.get("/roles");
    return response.data;
  } catch (e) {
    return rejectWithValue("");
  }
});
