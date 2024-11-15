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
            <rackAssignment-table
            v-if="rackAssignments && rackAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:rackAssignments="rackAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-rack-assignments="getAllRackAssignments"
             >

            </rackAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RackAssignmentTable from "@/components/RackAssignmentTable";
import RackAssignmentService from "../services/RackAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RackAssignmentTable,
  },
  data() {
    return {
      rackAssignments: [],
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
    async getAllRackAssignments(sortBy='rackAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RackAssignmentService.getAllRackAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.rackAssignments.length) {
					this.rackAssignments = response.data.rackAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching rackAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching rackAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllRackAssignments();
  },
  created() {
    this.$root.$on('searchQueryForRackAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRackAssignments();
    })
  }
};
</script>
<style></style>
