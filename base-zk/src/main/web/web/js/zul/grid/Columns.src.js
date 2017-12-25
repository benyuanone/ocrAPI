

zul.grid.Columns = zk.$extends(zul.mesh.ColumnMenuWidget, {
	
	
	getGrid: function () {
		return this.parent;
	},
	rerender: function () {
		if (this.desktop) {
			if (this.parent)
				this.parent.rerender();
			else 
				this.$supers('rerender', arguments);
		}
		return this;
	},
	getGroupPackage_: function () {
		return 'zkex.grid';
	}
});