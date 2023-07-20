import { createSlice } from "@reduxjs/toolkit";
import { fetchSensors, createSensor, updateSensor, deleteSensor } from "../action/sensors";
const initialState = {
  loading: false,
  available: null,
  errorMsg: false
};
//TODO: rivedere per la paginazione
const Sensors = createSlice({
  name: "sensors",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchSensors.pending, (state, action) => {
        state.loading = true;
        state.errorMsg = false;
      })
      .addCase(fetchSensors.fulfilled, (state, action) => {
        state.available = action.payload;
        state.loading = false;
        state.errorMsg = false;
      })
      .addCase(fetchSensors.rejected, (state, action) => {
        state.loading = false;
        state.errorMsg = action.payload.message;
      })
      .addCase(createSensor.pending, (state, action) => {
        state.loading = true;
        state.errorMsg = false;
      })
      .addCase(createSensor.fulfilled, (state, action) => {
        const { sensorData } = action.payload;
        state.available.content = [sensorData, ...state.available.content];
        state.loading = false;
        state.errorMsg = false;
      })
      .addCase(createSensor.rejected, (state, action) => {
        state.loading = false;
        state.errorMsg = action.payload.message;
      })
      .addCase(updateSensor.pending, (state, action) => {
        state.loading = true;
        state.errorMsg = false;
      })
      .addCase(updateSensor.fulfilled, (state, action) => {
        const { sensorData } = action.payload;
        state.available.content = state.available.content.map((sensor) => {
          if (sensor.id !== sensorData.id) {
            return sensor;
          }
          return {
            ...sensorData
          };
        });
        state.loading = false;
        state.errorMsg = false;
      })
      .addCase(updateSensor.rejected, (state, action) => {
        state.loading = false;
        state.errorMsg = action.payload.message;
      })
      .addCase(deleteSensor.pending, (state, action) => {
        state.loading = true;
        state.errorMsg = false;
      })
      .addCase(deleteSensor.fulfilled, (state, action) => {
        const { id } = action.payload;
        state.available.content = state.available.content.filter((sensor) => sensor.id !== id);
        state.loading = false;
        state.errorMsg = false;
      })
      .addCase(deleteSensor.rejected, (state, action) => {
        state.loading = false;
        state.errorMsg = action.payload.message;
      });
  }
});

export const {} = Sensors.actions;

export default Sensors.reducer;
