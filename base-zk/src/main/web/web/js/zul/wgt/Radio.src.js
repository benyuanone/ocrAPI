

zul.wgt.Radio = zk.$extends(zul.wgt.Checkbox, {
	_attachExternal: null,
	
	getRadiogroup: function (parent) {
		if (!parent && this._group)
			return this._group;
		var wgt = parent || this.parent;
		for (; wgt; wgt = wgt.parent)
			if (wgt.$instanceof(zul.wgt.Radiogroup)) return wgt;
		return null;
	},
	
	setRadiogroup: function (group) {
		var old = this._group;
		if (old !== group) {
			if (old && this._attachExternal) old._rmExtern(this);
			this._group = group;
			if (group && this.desktop) {
				group._addExtern(this);
				this._attachExternal = true;
			}
			this._fixName();
		}
	},
	bind_: function(){
		this.$supers(zul.wgt.Radio, 'bind_', arguments);
		if(this._group && this.desktop && !this._attachExternal){
			this._group._addExtern(this);
			this._attachExternal = true;
		}
	},
	unbind_: function(){
		this.$supers(zul.wgt.Radio, 'unbind_', arguments);
		if(this._group && this._attachExternal){
			this._group._rmExtern(this);
			this._attachExternal = false;
		}
	},	
	
	setChecked: _zkf = function (checked) {
		if (checked != this._checked) {
			this._checked = checked;
			var n = this.$n('real');
			if (n) {
				n.checked = checked || false;
				checked ? jq(n).attr('checked','checked') : jq(n).removeAttr('checked');
				
				if (!n.checked)
					jq(n).removeAttr('checked');
				
				var group = this.getRadiogroup();
				if (group) {
					
					if (checked) {
						for (var items = group.getItems(), i = items.length; i--;) {
							if (items[i] != this) {
								var item = items[i].$n('real');
								if (item) {
									item.checked = false;
									jq(item).removeAttr('checked');
								}
								items[i]._checked = false;
							}
						}
					}
					group._fixSelectedIndex();
				}
			}
		}
		return this;
	},
	
	setSelected: _zkf,
	
	isSelected: function () {
		return this.isChecked();
	},
	
	getName: function () {
		var group = this.getRadiogroup();
		return group != null ? group.getName(): this.uuid;
	},
	_fixName: function () {
		var n = this.$n('real');
		if (n)
			n.name = this.getName();
	},
	beforeParentChanged_: function (newParent) {
		var oldParent = this.parentNode,
			oldGroup = this.getRadiogroup(),
			newGroup = newParent ? this.getRadiogroup(newParent) : null;
		if (oldGroup != newGroup || !newParent) {
			if (oldGroup && oldGroup.$instanceof(zul.wgt.Radiogroup)){
				oldGroup._fixOnRemove(this);
				if (this._attachExternal) {
					oldGroup._rmExtern(this);
					this._attachExternal = false;
				}
			}
			if (newGroup && newGroup.$instanceof(zul.wgt.Radiogroup)) {
				if (!this._attachExternal && newGroup == this._group ) {
					newGroup._addExtern(this);
					this._attachExternal = true;
				}
				newGroup._fixOnAdd(this); 
			}
		}
		this.$supers('beforeParentChanged_', arguments);
	},
	fireOnCheck_: function (checked) {
		
		var group = this.getRadiogroup();
		this.fire('onCheck', checked, {toServer: group && group.isListen('onCheck')} );
	}
});
