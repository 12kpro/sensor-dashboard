import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fetchSensors = createAsyncThunk("sensor/fetchSensors", async (_, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.get("/sensor");
    return { ...response.data };
  } catch (e) {
    return rejectWithValue(e.response.data);
  }
});

export const updateSensor = createAsyncThunk(
  "sensor/updateSensor",
  async ({ id, payload }, { getState, rejectWithValue }) => {
    try {
      const state = getState();
      api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
      const response = await api.put(`/sensor/${id}`, payload);
      return { sensorData: { ...response.data } };
    } catch (e) {
      return rejectWithValue(e.response.data);
    }
  }
);
export const createSensor = createAsyncThunk(
  "sensor/createSensor",
  async ({ payload }, { getState, rejectWithValue }) => {
    try {
      const state = getState();
      api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
      const response = await api.post(`/sensor`, payload);
      return { sensorData: { ...response.data } };
    } catch (e) {
      return rejectWithValue(e.response.data);
    }
  }
);

export const deleteSensor = createAsyncThunk("sensor/deleteSensor", async ({ id }, { getState, rejectWithValue }) => {
  try {
    const state = getState();
    api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
    const response = await api.delete(`/sensor/${id}`);
    return { id: id };
  } catch (e) {
    return rejectWithValue(e.response.data);
  }
});
