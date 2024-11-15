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
            <assignment-table
            v-if="assignments && assignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:assignments="assignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-assignments="getAllAssignments"
             >

            </assignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AssignmentTable from "@/components/AssignmentTable";
import AssignmentService from "../services/AssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AssignmentTable,
  },
  data() {
    return {
      assignments: [],
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
    async getAllAssignments(sortBy='assignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AssignmentService.getAllAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.assignments.length) {
					this.assignments = response.data.assignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching assignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching assignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllAssignments();
  },
  created() {
    this.$root.$on('searchQueryForAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAssignments();
    })
  }
};
</script>
<style></style>
