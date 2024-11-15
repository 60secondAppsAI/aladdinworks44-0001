import http from "../http-common"; 

class PowerSupplyAssignmentService {
  getAllPowerSupplyAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/powerSupplyAssignment/powerSupplyAssignments`, searchDTO);
  }

  get(powerSupplyAssignmentId) {
    return this.getRequest(`/powerSupplyAssignment/${powerSupplyAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/powerSupplyAssignment?field=${matchData}`, null);
  }

  addPowerSupplyAssignment(data) {
    return http.post("/powerSupplyAssignment/addPowerSupplyAssignment", data);
  }

  update(data) {
  	return http.post("/powerSupplyAssignment/updatePowerSupplyAssignment", data);
  }
  
  uploadImage(data,powerSupplyAssignmentId) {
  	return http.postForm("/powerSupplyAssignment/uploadImage/"+powerSupplyAssignmentId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new PowerSupplyAssignmentService();
