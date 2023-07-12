import { createSlice } from "@reduxjs/toolkit";
import { fetchUm } from "../action/um";
const initialState = {
  loading: false,
  available: []
};

const Um = createSlice({
  name: "um",
  initialState,
  reducers: {},
  extraReducers: {
    [fetchUm.pending]: (state, action) => {
      state.loading = true;
    },
    [fetchUm.fulfilled]: (state, action) => {
      state.available = action.payload;
      state.loading = false;
    },
    [fetchUm.rejected]: (state, action) => {
      state.loading = false;
      state.available = [];
    }
  }
});

export const {} = Um.actions;

export default Um.reducer;
