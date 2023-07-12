import { createSlice } from "@reduxjs/toolkit";
import { fetchRoles } from "../action/roles";
const initialState = {
  loading: false,
  available: []
};

const Roles = createSlice({
  name: "roles",
  initialState,
  reducers: {},
  extraReducers: {
    [fetchRoles.pending]: (state, action) => {
      state.loading = true;
    },
    [fetchRoles.fulfilled]: (state, action) => {
      state.available = action.payload;
      state.loading = false;
    },
    [fetchRoles.rejected]: (state, action) => {
      state.loading = false;
      state.available = [];
    }
  }
});

export const {} = Roles.actions;

export default Roles.reducer;
