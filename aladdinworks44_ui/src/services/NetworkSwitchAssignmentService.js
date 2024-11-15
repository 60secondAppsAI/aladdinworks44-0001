import http from "../http-common"; 

class NetworkSwitchAssignmentService {
  getAllNetworkSwitchAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/networkSwitchAssignment/networkSwitchAssignments`, searchDTO);
  }

  get(networkSwitchAssignmentId) {
    return this.getRequest(`/networkSwitchAssignment/${networkSwitchAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/networkSwitchAssignment?field=${matchData}`, null);
  }

  addNetworkSwitchAssignment(data) {
    return http.post("/networkSwitchAssignment/addNetworkSwitchAssignment", data);
  }

  update(data) {
  	return http.post("/networkSwitchAssignment/updateNetworkSwitchAssignment", data);
  }
  
  uploadImage(data,networkSwitchAssignmentId) {
  	return http.postForm("/networkSwitchAssignment/uploadImage/"+networkSwitchAssignmentId, data);
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

export default new NetworkSwitchAssignmentService();
