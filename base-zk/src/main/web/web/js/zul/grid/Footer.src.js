

zul.grid.Footer = zk.$extends(zul.mesh.FooterWidget, {
	
	
	getGrid: function () {
		return this.getMeshWidget();
	},
	
	getColumn: function () {
		return this.getHeaderWidget();
	}
});