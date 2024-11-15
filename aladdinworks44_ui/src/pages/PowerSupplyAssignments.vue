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
            <powerSupplyAssignment-table
            v-if="powerSupplyAssignments && powerSupplyAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:powerSupplyAssignments="powerSupplyAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-power-supply-assignments="getAllPowerSupplyAssignments"
             >

            </powerSupplyAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PowerSupplyAssignmentTable from "@/components/PowerSupplyAssignmentTable";
import PowerSupplyAssignmentService from "../services/PowerSupplyAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PowerSupplyAssignmentTable,
  },
  data() {
    return {
      powerSupplyAssignments: [],
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
    async getAllPowerSupplyAssignments(sortBy='powerSupplyAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PowerSupplyAssignmentService.getAllPowerSupplyAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.powerSupplyAssignments.length) {
					this.powerSupplyAssignments = response.data.powerSupplyAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching powerSupplyAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching powerSupplyAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllPowerSupplyAssignments();
  },
  created() {
    this.$root.$on('searchQueryForPowerSupplyAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPowerSupplyAssignments();
    })
  }
};
</script>
<style></style>
