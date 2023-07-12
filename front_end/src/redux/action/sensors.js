import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../utils/api";

// export const getSensor = (accessToken) => {
//   return async (dispatch) => {
//     try {
//       api.defaults.headers.Authorization = `Bearer ${accessToken}`;
//       const resp = await api.get("/sensor");

//       if (resp.ok) {
//         let { data } = await resp.json();
//         dispatch({ type: JOB_SEARCH, payload: data });
//       } else {
//         console.log("error");
//       }
//     } catch (error) {
//       console.log(error);
//     } finally {
//       console.log("fetch loading finish");
//     }
//   };
// };
export const fetchSensors = createAsyncThunk("sensors/fetchSensors", async (accessToken, { rejectWithValue }) => {
  try {
    api.defaults.headers.Authorization = `Bearer ${accessToken}`;
    const response = await api.get("/sensor");
    console.log(response);
    return { ...response.data };
  } catch (e) {
    return rejectWithValue("");
  }
});
