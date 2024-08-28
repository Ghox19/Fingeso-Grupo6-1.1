import api from "../../api";

export const getProperties = async () => {
  try {
    const response = await api.get("/inmueble");
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, data: error };
  }
};

export const postProperty = async (property) => {
  try {
    const response = await api.post("/owner", property);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, data: error };
  }
};

export const getAllPropertysByOwner = async (userId) => {
  try {
    const response = await api.get(`/inmueble/testing/${userId}`);
    console.log(response.data);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, data: error };
  }
};

export const getOwnerProfile = async (inmuebleId) => {
  try {
    const response = await api.get(`/inmueble/profileO/${inmuebleId}`);
    return { success: true, data: response.data };
  } catch (error) {
    return { success: false, data: error };
  }
};