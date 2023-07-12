import { createSlice } from "@reduxjs/toolkit";
import { fetchSensors } from "../action/sensors";
const initialState = {
  //bookmarks: [],
  available: {
    loading: false,
    sensors: []
  }
};

const Sensors = createSlice({
  name: "sensor",
  initialState,
  reducers: {},
  extraReducers: {
    [fetchSensors.pending]: (state, action) => {
      state.available.loading = true;
    },
    [fetchSensors.fulfilled]: (state, action) => {
      state.available.sensors = action.payload;
      state.available.loading = false;
    },
    [fetchSensors.rejected]: (state, action) => {
      state.available.loading = false;
      state.available.sensors = [];
    }
  }
});

export const {} = Sensors.actions;

export default Sensors.reducer;
