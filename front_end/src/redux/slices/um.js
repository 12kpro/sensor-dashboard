import { createSlice } from "@reduxjs/toolkit";
import { fetchUms, createUm, updateUm, deleteUm } from "../action/um";
const initialState = {
  loading: false,
  available: []
};

const Um = createSlice({
  name: "um",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUms.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(fetchUms.fulfilled, (state, action) => {
        state.available = action.payload;
        state.loading = false;
      })
      .addCase(fetchUms.rejected, (state, action) => {
        state.loading = false;
        state.available = [];
      })
      .addCase(createUm.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(createUm.fulfilled, (state, action) => {
        const { newUm } = action.payload;
        state.available = [newUm, ...state.available];
        state.loading = false;
      })
      .addCase(createUm.rejected, (state, action) => {
        state.loading = false;
      })
      .addCase(updateUm.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(updateUm.fulfilled, (state, action) => {
        const { newUm } = action.payload;
        state.available = state.available.map((um) => {
          if (um.id !== newUm.id) {
            return um;
          }
          return {
            ...newUm
          };
        });
        state.loading = false;
      })
      .addCase(updateUm.rejected, (state, action) => {
        state.loading = false;
      })
      .addCase(deleteUm.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(deleteUm.fulfilled, (state, action) => {
        const { id } = action.payload;
        state.available = state.available.filter((um) => um.id !== id);
        state.loading = false;
      })
      .addCase(deleteUm.rejected, (state, action) => {
        state.loading = false;
      });
  }
  // extraReducers: {
  //   [fetchUm.pending]: (state, action) => {
  //     state.loading = true;
  //   },
  //   [fetchUm.fulfilled]: (state, action) => {
  //     state.available = action.payload;
  //     state.loading = false;
  //   },
  //   [fetchUm.rejected]: (state, action) => {
  //     state.loading = false;
  //     state.available = [];
  //   }
  // }
});

export const {} = Um.actions;

export default Um.reducer;
