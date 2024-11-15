import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import DataCenters from  '@/pages/DataCenters.vue';
import DataCenterDetail from  '@/pages/DataCenterDetail.vue';
import Racks from  '@/pages/Racks.vue';
import RackDetail from  '@/pages/RackDetail.vue';
import PowerSupplys from  '@/pages/PowerSupplys.vue';
import PowerSupplyDetail from  '@/pages/PowerSupplyDetail.vue';
import PowerStrips from  '@/pages/PowerStrips.vue';
import PowerStripDetail from  '@/pages/PowerStripDetail.vue';
import CoolingUnits from  '@/pages/CoolingUnits.vue';
import CoolingUnitDetail from  '@/pages/CoolingUnitDetail.vue';
import Generators from  '@/pages/Generators.vue';
import GeneratorDetail from  '@/pages/GeneratorDetail.vue';
import TemperatureSensors from  '@/pages/TemperatureSensors.vue';
import TemperatureSensorDetail from  '@/pages/TemperatureSensorDetail.vue';
import CurrentSensors from  '@/pages/CurrentSensors.vue';
import CurrentSensorDetail from  '@/pages/CurrentSensorDetail.vue';
import NetworkSwitchs from  '@/pages/NetworkSwitchs.vue';
import NetworkSwitchDetail from  '@/pages/NetworkSwitchDetail.vue';
import Servers from  '@/pages/Servers.vue';
import ServerDetail from  '@/pages/ServerDetail.vue';
import MonitoringPoints from  '@/pages/MonitoringPoints.vue';
import MonitoringPointDetail from  '@/pages/MonitoringPointDetail.vue';
import Alerts from  '@/pages/Alerts.vue';
import AlertDetail from  '@/pages/AlertDetail.vue';
import MaintenanceSchedules from  '@/pages/MaintenanceSchedules.vue';
import MaintenanceScheduleDetail from  '@/pages/MaintenanceScheduleDetail.vue';
import Technicians from  '@/pages/Technicians.vue';
import TechnicianDetail from  '@/pages/TechnicianDetail.vue';
import Assignments from  '@/pages/Assignments.vue';
import AssignmentDetail from  '@/pages/AssignmentDetail.vue';
import RackAssignments from  '@/pages/RackAssignments.vue';
import RackAssignmentDetail from  '@/pages/RackAssignmentDetail.vue';
import PowerSupplyAssignments from  '@/pages/PowerSupplyAssignments.vue';
import PowerSupplyAssignmentDetail from  '@/pages/PowerSupplyAssignmentDetail.vue';
import CoolingUnitAssignments from  '@/pages/CoolingUnitAssignments.vue';
import CoolingUnitAssignmentDetail from  '@/pages/CoolingUnitAssignmentDetail.vue';
import NetworkSwitchAssignments from  '@/pages/NetworkSwitchAssignments.vue';
import NetworkSwitchAssignmentDetail from  '@/pages/NetworkSwitchAssignmentDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/dataCenters',
																			  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/dataCenters',
		name: 'DataCenters',
		layout: DefaultLayout,
		component: DataCenters,
	},
	{
	    path: '/dataCenter/:dataCenterId', 
	    name: 'DataCenterDetail',
		layout: DefaultLayout,
	    component: DataCenterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/racks',
		name: 'Racks',
		layout: DefaultLayout,
		component: Racks,
	},
	{
	    path: '/rack/:rackId', 
	    name: 'RackDetail',
		layout: DefaultLayout,
	    component: RackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSupplys',
		name: 'PowerSupplys',
		layout: DefaultLayout,
		component: PowerSupplys,
	},
	{
	    path: '/powerSupply/:powerSupplyId', 
	    name: 'PowerSupplyDetail',
		layout: DefaultLayout,
	    component: PowerSupplyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerStrips',
		name: 'PowerStrips',
		layout: DefaultLayout,
		component: PowerStrips,
	},
	{
	    path: '/powerStrip/:powerStripId', 
	    name: 'PowerStripDetail',
		layout: DefaultLayout,
	    component: PowerStripDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingUnits',
		name: 'CoolingUnits',
		layout: DefaultLayout,
		component: CoolingUnits,
	},
	{
	    path: '/coolingUnit/:coolingUnitId', 
	    name: 'CoolingUnitDetail',
		layout: DefaultLayout,
	    component: CoolingUnitDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/generators',
		name: 'Generators',
		layout: DefaultLayout,
		component: Generators,
	},
	{
	    path: '/generator/:generatorId', 
	    name: 'GeneratorDetail',
		layout: DefaultLayout,
	    component: GeneratorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/temperatureSensors',
		name: 'TemperatureSensors',
		layout: DefaultLayout,
		component: TemperatureSensors,
	},
	{
	    path: '/temperatureSensor/:temperatureSensorId', 
	    name: 'TemperatureSensorDetail',
		layout: DefaultLayout,
	    component: TemperatureSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/currentSensors',
		name: 'CurrentSensors',
		layout: DefaultLayout,
		component: CurrentSensors,
	},
	{
	    path: '/currentSensor/:currentSensorId', 
	    name: 'CurrentSensorDetail',
		layout: DefaultLayout,
	    component: CurrentSensorDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/networkSwitchs',
		name: 'NetworkSwitchs',
		layout: DefaultLayout,
		component: NetworkSwitchs,
	},
	{
	    path: '/networkSwitch/:networkSwitchId', 
	    name: 'NetworkSwitchDetail',
		layout: DefaultLayout,
	    component: NetworkSwitchDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/servers',
		name: 'Servers',
		layout: DefaultLayout,
		component: Servers,
	},
	{
	    path: '/server/:serverId', 
	    name: 'ServerDetail',
		layout: DefaultLayout,
	    component: ServerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/monitoringPoints',
		name: 'MonitoringPoints',
		layout: DefaultLayout,
		component: MonitoringPoints,
	},
	{
	    path: '/monitoringPoint/:monitoringPointId', 
	    name: 'MonitoringPointDetail',
		layout: DefaultLayout,
	    component: MonitoringPointDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/alerts',
		name: 'Alerts',
		layout: DefaultLayout,
		component: Alerts,
	},
	{
	    path: '/alert/:alertId', 
	    name: 'AlertDetail',
		layout: DefaultLayout,
	    component: AlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/maintenanceSchedules',
		name: 'MaintenanceSchedules',
		layout: DefaultLayout,
		component: MaintenanceSchedules,
	},
	{
	    path: '/maintenanceSchedule/:maintenanceScheduleId', 
	    name: 'MaintenanceScheduleDetail',
		layout: DefaultLayout,
	    component: MaintenanceScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/technicians',
		name: 'Technicians',
		layout: DefaultLayout,
		component: Technicians,
	},
	{
	    path: '/technician/:technicianId', 
	    name: 'TechnicianDetail',
		layout: DefaultLayout,
	    component: TechnicianDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/assignments',
		name: 'Assignments',
		layout: DefaultLayout,
		component: Assignments,
	},
	{
	    path: '/assignment/:assignmentId', 
	    name: 'AssignmentDetail',
		layout: DefaultLayout,
	    component: AssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/rackAssignments',
		name: 'RackAssignments',
		layout: DefaultLayout,
		component: RackAssignments,
	},
	{
	    path: '/rackAssignment/:rackAssignmentId', 
	    name: 'RackAssignmentDetail',
		layout: DefaultLayout,
	    component: RackAssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/powerSupplyAssignments',
		name: 'PowerSupplyAssignments',
		layout: DefaultLayout,
		component: PowerSupplyAssignments,
	},
	{
	    path: '/powerSupplyAssignment/:powerSupplyAssignmentId', 
	    name: 'PowerSupplyAssignmentDetail',
		layout: DefaultLayout,
	    component: PowerSupplyAssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/coolingUnitAssignments',
		name: 'CoolingUnitAssignments',
		layout: DefaultLayout,
		component: CoolingUnitAssignments,
	},
	{
	    path: '/coolingUnitAssignment/:coolingUnitAssignmentId', 
	    name: 'CoolingUnitAssignmentDetail',
		layout: DefaultLayout,
	    component: CoolingUnitAssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/networkSwitchAssignments',
		name: 'NetworkSwitchAssignments',
		layout: DefaultLayout,
		component: NetworkSwitchAssignments,
	},
	{
	    path: '/networkSwitchAssignment/:networkSwitchAssignmentId', 
	    name: 'NetworkSwitchAssignmentDetail',
		layout: DefaultLayout,
	    component: NetworkSwitchAssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
