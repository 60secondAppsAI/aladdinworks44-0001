import http from "../http-common"; 

class AssignmentService {
  getAllAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/assignment/assignments`, searchDTO);
  }

  get(assignmentId) {
    return this.getRequest(`/assignment/${assignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/assignment?field=${matchData}`, null);
  }

  addAssignment(data) {
    return http.post("/assignment/addAssignment", data);
  }

  update(data) {
  	return http.post("/assignment/updateAssignment", data);
  }
  
  uploadImage(data,assignmentId) {
  	return http.postForm("/assignment/uploadImage/"+assignmentId, data);
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

export default new AssignmentService();
