import http from "../http-common"; 

class RackAssignmentService {
  getAllRackAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/rackAssignment/rackAssignments`, searchDTO);
  }

  get(rackAssignmentId) {
    return this.getRequest(`/rackAssignment/${rackAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/rackAssignment?field=${matchData}`, null);
  }

  addRackAssignment(data) {
    return http.post("/rackAssignment/addRackAssignment", data);
  }

  update(data) {
  	return http.post("/rackAssignment/updateRackAssignment", data);
  }
  
  uploadImage(data,rackAssignmentId) {
  	return http.postForm("/rackAssignment/uploadImage/"+rackAssignmentId, data);
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

export default new RackAssignmentService();
