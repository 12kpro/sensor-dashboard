import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fetchUm = createAsyncThunk("um/fetchUm", async (accessToken, { rejectWithValue }) => {
  try {
    api.defaults.headers.Authorization = `Bearer ${accessToken}`;
    const response = await api.get("/um");
    return { ...response.data };
  } catch (e) {
    return rejectWithValue("");
  }
});
