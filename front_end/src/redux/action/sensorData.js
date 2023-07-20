import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

export const fillSensorData = createAsyncThunk(
  "sensorData/fillSensorData",
  async (id, { getState, rejectWithValue }) => {
    try {
      const state = getState();
      api.defaults.headers.Authorization = `Bearer ${state.auth.token}`;
      const response = await api.get(`/sensor/${id}/data`);
      return { sensorId: id, data: response.data.map(({ time, value }) => ({ time, value })) };
    } catch (e) {
      return rejectWithValue(e.response.data);
    }
  }
);
