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
            <coolingUnitAssignment-table
            v-if="coolingUnitAssignments && coolingUnitAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:coolingUnitAssignments="coolingUnitAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-cooling-unit-assignments="getAllCoolingUnitAssignments"
             >

            </coolingUnitAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CoolingUnitAssignmentTable from "@/components/CoolingUnitAssignmentTable";
import CoolingUnitAssignmentService from "../services/CoolingUnitAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CoolingUnitAssignmentTable,
  },
  data() {
    return {
      coolingUnitAssignments: [],
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
    async getAllCoolingUnitAssignments(sortBy='coolingUnitAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CoolingUnitAssignmentService.getAllCoolingUnitAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.coolingUnitAssignments.length) {
					this.coolingUnitAssignments = response.data.coolingUnitAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching coolingUnitAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching coolingUnitAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllCoolingUnitAssignments();
  },
  created() {
    this.$root.$on('searchQueryForCoolingUnitAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCoolingUnitAssignments();
    })
  }
};
</script>
<style></style>
