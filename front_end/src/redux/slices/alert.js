import { createSlice } from "@reduxjs/toolkit";

const initialState = [];

export const alertSlice = createSlice({
  name: "alerts",
  initialState,
  reducers: {
    addAlert: (state, action) => {
      console.log(action);
      //state = [...state, action.payload];
      state.push(action.payload);
    },
    removeAlert: (state, action) => {
      const alertToRemove = state.find((alert) => alert.id === action.payload);
      if (alertToRemove) {
        state.splice(state.indexOf(alertToRemove), 1);
      }
    }
  }
});

export const { addAlert, removeAlert } = alertSlice.actions;

export default alertSlice.reducer;
