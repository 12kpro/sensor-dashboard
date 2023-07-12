import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fetchRoles = createAsyncThunk("roles/fetchRoles", async (accessToken, { rejectWithValue }) => {
  try {
    api.defaults.headers.Authorization = `Bearer ${accessToken}`;
    const response = await api.get("/roles");
    return { ...response.data };
  } catch (e) {
    return rejectWithValue("");
  }
});
