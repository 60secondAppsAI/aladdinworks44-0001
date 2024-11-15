<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <networkSwitchAssignment-table
            v-if="networkSwitchAssignments && networkSwitchAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:networkSwitchAssignments="networkSwitchAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-network-switch-assignments="getAllNetworkSwitchAssignments"
             >

            </networkSwitchAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import NetworkSwitchAssignmentTable from "@/components/NetworkSwitchAssignmentTable";
import NetworkSwitchAssignmentService from "../services/NetworkSwitchAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    NetworkSwitchAssignmentTable,
  },
  data() {
    return {
      networkSwitchAssignments: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllNetworkSwitchAssignments(sortBy='networkSwitchAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await NetworkSwitchAssignmentService.getAllNetworkSwitchAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.networkSwitchAssignments.length) {
					this.networkSwitchAssignments = response.data.networkSwitchAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching networkSwitchAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching networkSwitchAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllNetworkSwitchAssignments();
  },
  created() {
    this.$root.$on('searchQueryForNetworkSwitchAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllNetworkSwitchAssignments();
    })
  }
};
</script>
<style></style>
