import http from "../http-common"; 

class CoolingUnitAssignmentService {
  getAllCoolingUnitAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/coolingUnitAssignment/coolingUnitAssignments`, searchDTO);
  }

  get(coolingUnitAssignmentId) {
    return this.getRequest(`/coolingUnitAssignment/${coolingUnitAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/coolingUnitAssignment?field=${matchData}`, null);
  }

  addCoolingUnitAssignment(data) {
    return http.post("/coolingUnitAssignment/addCoolingUnitAssignment", data);
  }

  update(data) {
  	return http.post("/coolingUnitAssignment/updateCoolingUnitAssignment", data);
  }
  
  uploadImage(data,coolingUnitAssignmentId) {
  	return http.postForm("/coolingUnitAssignment/uploadImage/"+coolingUnitAssignmentId, data);
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

export default new CoolingUnitAssignmentService();
