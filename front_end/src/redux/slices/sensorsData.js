import { createSlice } from "@reduxjs/toolkit";
import { fillSensorData } from "../action/sensorData";
const initialState = {
  sensorId: "",
  loading: true,
  data: [],
  errorMsg: false
};

export const sensorData = createSlice({
  name: "sensorData",
  initialState,
  reducers: {
    addSensorData: (state, action) => {
      const { time, value } = action.payload;
      const cutoffDate = new Date(time).getTime() - 60 * 60 * 1000;

      state.data = state.data.filter((data) => new Date(data.time).getTime() > cutoffDate);
      state.data = [...state.data, { time, value: +value.toFixed(2) }];
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(fillSensorData.fulfilled, (state, action) => {
        const { data } = action.payload;
        state.loading = false;
        state.data = [...data, ...state.data];
        state.errorMsg = false;
      })
      .addCase(fillSensorData.pending, (state, action) => {
        state.sensorId = action.meta.arg;
        state.data = [];
        state.loading = true;
        state.errorMsg = false;
      })
      .addCase(fillSensorData.rejected, (state, action) => {
        state.loading = false;
        state.errorMsg = action.payload.message;
      });
  }
});
export const { addSensorData } = sensorData.actions;

export default sensorData.reducer;
